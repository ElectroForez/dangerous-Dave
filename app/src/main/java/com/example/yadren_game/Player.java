package com.example.yadren_game;

import android.graphics.Bitmap;

public class Player extends GameObject{
    enum State {
        IDLE_LEFT,
        IDLE_RIGHT,
        RUNNING_LEFT,
        RUNNING_RIGHT
    }
    private State state = State.RUNNING_RIGHT;
    public Player (Bitmap[] image, int x, int y){
        super(image, x, y);
        imageIndex = 1;
    }

    public void move(int dx, int dy) {
        super.move(dx, dy);

        switch (state) {
            case RUNNING_RIGHT:
                if (imageIndex == 4) {
                    imageIndex++;
                    imageIndex = 1;
                }

                if (dx < 0) {
                    state = State.RUNNING_LEFT;
                    imageIndex = 8;
                }
                break;
            case RUNNING_LEFT:
                if (imageIndex == 12) {
                    imageIndex++;
                    imageIndex = 8;
                }
                if (dx > 0) {
                    state = State.RUNNING_RIGHT;
                    imageIndex = 1;
                }
                break;
            case IDLE_LEFT:
            case IDLE_RIGHT:
                if (dx > 0) {
                    state = State.RUNNING_RIGHT;
                    imageIndex = 1;
                }
                else if (dx < 0) {
                    state = State.RUNNING_LEFT;
                    imageIndex = 8;
                }
        }
    }

    public void idle() {
        switch (state){
            case RUNNING_RIGHT:
                imageIndex = 0;
                state = State.IDLE_RIGHT;
                break;
            case RUNNING_LEFT:
                imageIndex = 7;
                state = State.IDLE_LEFT;
                break;
        }
    }

    public Bitmap getImage(){
        return image[imageIndex];
    }
}
