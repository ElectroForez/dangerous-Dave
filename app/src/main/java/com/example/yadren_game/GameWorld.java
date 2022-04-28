package com.example.yadren_game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class GameWorld {
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Bitmap background;
    private Player player;
    public GameWorld(Bitmap background){
        this.background = background;
    }

    public void addObject(GameObject object) {
        if (object instanceof Player) {
            this.player = (Player) object;
            objects.add(0, object);
        } else {
            objects.add(object);
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public ArrayList<GameObject> getHits(GameObject object){
        Rect objHitbox = object.getHitbox();
        ArrayList<GameObject> result = new ArrayList<>();
        for (GameObject obj : objects) {
            Rect hitbox = obj.getHitbox();
            if ((object != obj) && Rect.intersects(hitbox, objHitbox)) {
                result.add(obj);
            }
        }
        return result;
    }

    public void movePlayer(int dx, int dy) {
        player.move(dx, dy);
        ArrayList<GameObject> hits = getHits(player);
        for (GameObject hit : hits){
            if ((hit instanceof Floor) || (hit instanceof Wall)) {
                player.offsetPosition(-dx, -dy);
                break;
            }
        }


    }

    public void draw(Canvas canvas) {
        Paint p = new Paint();
        int canvasHeight = canvas.getHeight();
        int canvasWidth = canvas.getWidth();
        int titleSize = background.getHeight();

        for (int y = 0; y < canvasHeight; y+= titleSize) {
            for (int x = 0; x < canvasWidth; x += titleSize) {
                canvas.drawBitmap(background, x, y, p);
            }
        }

        for (int i = 1; i < objects.size(); i++) {
            GameObject obj = objects.get(i);
            canvas.drawBitmap(obj.getImage(), obj.getX(), obj.getY(), p);
            Paint paintHitbox = new Paint();
            paintHitbox.setColor(Color.GREEN);
            paintHitbox.setStyle(Paint.Style.STROKE);
            paintHitbox.setStrokeWidth(3);
            canvas.drawRect(obj.getHitbox(), paintHitbox);
        }

        canvas.drawBitmap(player.getImage(), player.getX(), player.getY(), p);
        Paint paintHitbox = new Paint();
        paintHitbox.setColor(Color.GREEN);
        paintHitbox.setStyle(Paint.Style.STROKE);
        paintHitbox.setStrokeWidth(3);
        canvas.drawRect(player.getHitbox(), paintHitbox);
    }
}
