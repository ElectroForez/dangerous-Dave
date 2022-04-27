package com.example.yadren_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameWorldLoader {
    private Bitmap tiles;
    private Bitmap playerTiles;
    private Bitmap level1Background;
    private Bitmap level1Floor;
    private Bitmap[] playerImages;
    private Bitmap doorImage;
    private Context context;

    private final char FLOOR = '=';
    private final char PLAYER = 'P';
    private final char WALL = '|';
    private final char DOOR = 'D';

    public int pxFromDp(int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    public GameWorldLoader(Context context) {
        this.context = context;
        tiles = BitmapFactory.decodeResource(context.getResources(), R.drawable.egatiles);
        playerTiles = BitmapFactory.decodeResource(context.getResources(), R.drawable.s_dave_fixed);
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
        playerImages = new Bitmap[17];
        for (int i = 0; i < playerImages.length; i ++) {
            playerImages[i] = Bitmap.createBitmap(playerTiles,
                    pxFromDp(32 * i),
                    pxFromDp(32),
                    pxFromDp(32),
                    pxFromDp(32)
            );
        }

        doorImage = Bitmap.createBitmap(tiles,
                pxFromDp(64),
                pxFromDp(304),
                pxFromDp(31),
                pxFromDp(48)
        );
    }

    public void loadLevel(String[] level, GameWorld world) {
        int y = 0;
        for (String s : level) {
            int x = 0;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                switch (ch) {
                    case FLOOR:
                        world.addObject(new Floor(level1Floor, x, y));
                        break;
                    case DOOR:
                        world.addObject(new Door(doorImage, x, y - 92));
                        break;
                }
                x += 32;
            }
            y += 32;
        }
    }

    public GameWorld load(String[] level) {
        Player player = new Player(playerImages, 100, 500);
        GameWorld world = new GameWorld(player, level1Background, level1Floor);
        loadLevel(level, world);
        world.addObject(player);
        return world;
    }
}
