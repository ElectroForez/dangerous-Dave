package com.example.yadren_game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class GameWorld {
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Bitmap background;
    private Player player;
    public GameWorld(Player player, Bitmap background, Bitmap floor){
        this.background = background;
        this.player = player;
        addObject(player);
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public Player getPlayer() {
        return this.player;
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
        }

        canvas.drawBitmap(player.getImage(), player.getX(), player.getY(), p);
    }
}
