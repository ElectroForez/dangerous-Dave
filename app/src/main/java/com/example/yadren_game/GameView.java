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
    Player player;
    GameWorld world;
    Timer timer;
    long counter = 0;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int height = this.getHeight();
        int width = this.getWidth();
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        int dist = 10;

        if (y < (height / 3)) {
            world.movePlayer(0, -dist);
        } else if (y > (height - (height / 3))) {
            world.movePlayer(0, dist);
        }

        if (x < (width / 3)) {
            world.movePlayer(-dist, 0);
        } else if ( x > (width - (width / 3))) {
            world.movePlayer(dist, 0);
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
                    if (counter++ % 25 == 0)
                        player.idle();
                    GameView.this.invalidate();
                }
            });
        }
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        GameWorldLoader loader = new GameWorldLoader(context);
        String level[] = {
                "===================================",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[             D                  ]",
                "[===  =============    ==========]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[          D            D        ]",
                "[===========================  ===]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[                                ]",
                "[ P                              ]",
                "==================================",
        };
        world = loader.load(level);
        player = world.getPlayer();
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
