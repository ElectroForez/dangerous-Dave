package com.example.yadren_game;

import android.graphics.Bitmap;

public class GameObject {
    protected Bitmap[] image;
    protected int x, y;
    protected int imageIndex;

    public GameObject(Bitmap image[], int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public GameObject(Bitmap image, int x, int y) {
        this(new Bitmap[] { image}, x, y);
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
    }

    public Bitmap getImage() {
        return this.image[0];
    }

}
