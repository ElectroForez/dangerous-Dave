package com.example.yadren_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class GameWorldLoader {
    private Bitmap tiles;
    private Bitmap level1Background;
    private Bitmap level1Floor;
    private Bitmap doorImage;
    private Bitmap level1WallLeft;
    private Bitmap level1WallRight;
    private Context context;

    private final char FLOOR = '=';
    private final char PLAYER = 'P';
    private final char WALL = '|';
    private final char DOOR = 'D';
    private final char WALL_LEFT = '[';
    private final char WALL_RIGHT = ']';

    public int pxFromDp(int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    public GameWorldLoader(Context context) {
        this.context = context;
        tiles = BitmapFactory.decodeResource(context.getResources(), R.drawable.egatiles);
        int x = pxFromDp(0);
        int y = pxFromDp(179);
        int s = pxFromDp(16);

        level1Background = Bitmap.createBitmap(tiles, x, y, s, s);

        level1Floor = Bitmap.createBitmap(tiles,
                pxFromDp(192),
                pxFromDp(48),
                s,
                s
        );

        doorImage = Bitmap.createBitmap(tiles,
                pxFromDp(64),
                pxFromDp(304),
                pxFromDp(31),
                pxFromDp(48)
        );

        level1WallLeft = Bitmap.createBitmap(tiles,
                pxFromDp(146),
                pxFromDp(83),
                pxFromDp(16),
                pxFromDp(16));

        level1WallRight = createFlippedBitmap(level1WallLeft, true, false);

    }

    private Bitmap[] loadPlayerTiles() {
        Bitmap playerTiles = BitmapFactory.decodeResource(context.getResources(), R.drawable.s_dave_fixed);
        Bitmap[] playerImages = new Bitmap[17];
        for (int i = 0; i < playerImages.length; i++) {
            playerImages[i] = Bitmap.createBitmap(playerTiles,
                    pxFromDp(32 * i),
                    pxFromDp(32),
                    pxFromDp(32),
                    pxFromDp(32)
            );
        }
        return playerImages;
    }

    public void loadLevel(String[] level, GameWorld world) {
        int y = 0;
        for (String s : level) {
            int x = 0;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                switch (ch) {
                    case FLOOR:
                        world.addObject(new Floor(level1Floor, x, y,
                                pxFromDp(16), pxFromDp(16)));
                        break;
                    case DOOR:
                        world.addObject(new Door(doorImage, x, y - 92,
                                pxFromDp(32), pxFromDp(32)));
                        break;
                    case PLAYER:
                        world.addObject(new Player(loadPlayerTiles(), x, y - 48,
                                pxFromDp(32), pxFromDp(32)));
                        break;
                    case WALL_LEFT:
                        world.addObject(new Wall(level1WallLeft, x, y,
                                pxFromDp(16), pxFromDp(16)));
                        break;
                    case WALL_RIGHT:
                        world.addObject(new Wall(level1WallRight, x, y,
                                pxFromDp(16), pxFromDp(16)));
                        break;
                }
                x += 32;
            }
            y += 32;
        }
    }

    public static Bitmap createFlippedBitmap(Bitmap source, boolean xFlip, boolean yFlip) {
        Matrix matrix = new Matrix();
        matrix.postScale(xFlip ? -1 : 1, yFlip ? -1 : 1, source.getWidth() / 2f, source.getHeight() / 2f);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public GameWorld load(String[] level) {
        GameWorld world = new GameWorld(level1Background);
        loadLevel(level, world);
        return world;
    }
}
