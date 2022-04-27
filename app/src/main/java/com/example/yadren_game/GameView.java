package com.example.yadren_game;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class GameView extends View implements View.OnTouchListener {
    Bitmap tiles;
    Bitmap playerTiles;
    Bitmap level1Background;
    Bitmap level1Floor;
    Bitmap playerImage;
    GameObject player;
    GameWorld world;
    Timer timer;

    public int pxFromDp(final Context context, int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int height = this.getHeight();
        int width = this.getWidth();
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        int dist = 10;

        if (y < (height / 3)) {
            player.move(0, -dist);
        } else if (y > (height - (height / 3))) {
            player.move(0, dist);
        }

        if (x < (width / 3)) {
            player.move(-dist, 0);
        } else if ( x > (width - (width / 3))) {
            player.move(dist, 0);
        }

        return true;

    }

    class UpdateGameViewTask extends TimerTask {
        private Context context;

        public UpdateGameViewTask(Context context) {
            this.context = context;
        }

        public void run() {
            Activity a = (Activity) context;
            a.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    GameView.this.invalidate();
                }
            });
        }
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        tiles = BitmapFactory.decodeResource(getResources(), R.drawable.egatiles);
        playerTiles = BitmapFactory.decodeResource(getResources(), R.drawable.s_dave_fixed);
        int x = pxFromDp(context, 0);
        int y = pxFromDp(context, 179);
        int s = pxFromDp(context, 16);
        level1Background = Bitmap.createBitmap(tiles, x, y, s, s);

        level1Floor = Bitmap.createBitmap(tiles,
                pxFromDp(context, 192),
                pxFromDp(context, 48),
                s,
                s
        );

        playerImage = Bitmap.createBitmap(playerTiles,
                pxFromDp(context, 192),
                pxFromDp(context, 32),
                pxFromDp(context, 32),
                pxFromDp(context, 32)
        );

        player = new GameObject(playerImage, 100, 500);
        world = new GameWorld(level1Background, level1Floor);
        world.addObject(player);

        timer = new Timer();
        UpdateGameViewTask task = new UpdateGameViewTask(context);
        final int FPS = 40;
        timer.scheduleAtFixedRate(task, 0, 1000 / FPS);
        this.setOnTouchListener(this);
    }

    public void onDraw(Canvas canvas) {
        world.draw(canvas);
    }
}
