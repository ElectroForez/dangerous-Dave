package com.example.yadren_game;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class GameObject {
    protected Bitmap[] image;
    protected int x, y;
    protected int imageIndex;
    protected Rect hitbox;

    public GameObject(Bitmap image[], int x, int y, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.hitbox = new Rect(x, y, x + width, y + height);
    }

    public GameObject(Bitmap image, int x, int y, int width, int height) {
        this(new Bitmap[] { image}, x, y, width, height);
    }

    public Rect getHitbox() {
        return hitbox;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(int dx, int dy){
        this.x += dx;
        this.y += dy;
        this.hitbox.offset(dx, dy);
    }

    public Bitmap getImage() {
        return this.image[0];
    }

}
