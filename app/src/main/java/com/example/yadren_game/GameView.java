package com.example.yadren_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {
    Bitmap tiles;
    Bitmap level1Background, level1Floor;

    public int pxFromDp(final Context context, int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        tiles = BitmapFactory.decodeResource(getResources(), R.drawable.egatiles);
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
    }

    public void onDraw(Canvas canvas) {
        Paint p = new Paint();
        int canvasHeight = canvas.getHeight();
        int canvasWidth = canvas.getWidth();
        int titleSize = level1Background.getHeight();
        for (int y = 0; y < canvasHeight; y+= titleSize) {
            Bitmap tile;
            if (y % 48 == 0) {
                tile = level1Floor;
            } else {
                tile = level1Background;
            }

            for (int x = 0; x < canvasWidth; x += titleSize) {
                canvas.drawBitmap(tile, x, y, p);
            }

        }
    }
}
