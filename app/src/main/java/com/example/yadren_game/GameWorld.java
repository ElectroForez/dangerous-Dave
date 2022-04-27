package com.example.yadren_game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class GameWorld {
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Bitmap background;
    private Bitmap floor;

    public GameWorld(Bitmap background, Bitmap floor){
        this.background = background;
        this.floor = floor;
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public void draw(Canvas canvas) {
        Paint p = new Paint();
        int canvasHeight = canvas.getHeight();
        int canvasWidth = canvas.getWidth();
        int titleSize = background.getHeight();

        for (int y = 0; y < canvasHeight; y+= titleSize) {
            Bitmap tile;
            if (y % 48 == 0) {
                System.out.println(y);
                tile = floor;
            } else {
                tile = background;
            }

            for (int x = 0; x < canvasWidth; x += titleSize) {
                canvas.drawBitmap(tile, x, y, p);
            }
        }

        for (GameObject obj : objects) {
            canvas.drawBitmap(obj.getImage(), obj.getX(), obj.getY(), p);
        }
    }
}
