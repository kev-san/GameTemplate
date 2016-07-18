package com.mygdx.game;

import java.util.ArrayList;

/**
 * Created by Ryan on 7/14/2016.
 */
public class Level {
    private int level;
    public Level(int lvl) {
        setLevel(lvl);
    }

    public void setLevel(int lvl) {level = lvl;}

    public int getLevel() {return level;}

    /*
        put enemies inside arraylist here
        number of enemies depends on how many you want per level
        number of levels is controlled by an int inside MyGdxGame.java
     */
    public ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        if (getLevel() == 1) {
            for (int i = 0; i < 12; i++) {
                enemies.add(new Enemy((float) Math.random() * MyGdxGame.scrWidth, ((float) Math.random() * MyGdxGame.scrHeight) + MyGdxGame.scrHeight));
            }
        } else if (getLevel() == 2) {
            for (int i = 0; i < 20; i++) {
                enemies.add(new Enemy((float) Math.random() * MyGdxGame.scrWidth, ((float) Math.random() * MyGdxGame.scrHeight) + MyGdxGame.scrHeight));
            }
        } else if (getLevel() == 3) {
            for (int i = 0; i < 26; i++) {
                enemies.add(new Enemy((float) Math.random() * MyGdxGame.scrWidth, ((float) Math.random() * MyGdxGame.scrHeight) + MyGdxGame.scrHeight));
            }

        } else if (getLevel() == 4) {
            for (int i = 0; i < 32; i++) {
                enemies.add(new Enemy((float) Math.random() * MyGdxGame.scrWidth, ((float) Math.random() * MyGdxGame.scrHeight) + MyGdxGame.scrHeight));
            }
        }
        else if (getLevel() == 5) {
            for (int i = 0; i < 40; i++) {
                enemies.add(new Enemy((float) Math.random() * MyGdxGame.scrWidth, ((float) Math.random() * MyGdxGame.scrHeight) + MyGdxGame.scrHeight));
            }
        }
        else if (getLevel() == 6) {
            for (int i = 0; i < 47; i++) {
                enemies.add(new Enemy((float) Math.random() * MyGdxGame.scrWidth, ((float) Math.random() * MyGdxGame.scrHeight) + MyGdxGame.scrHeight));
            }
        } else if (getLevel() == 7) {
            for (int i = 0; i < 55; i++) {
                enemies.add(new Enemy((float) Math.random() * MyGdxGame.scrWidth, ((float) Math.random() * MyGdxGame.scrHeight) + MyGdxGame.scrHeight));
            }
        } else if (getLevel() == 8) {
            for (int i = 0; i < 61; i++) {
                enemies.add(new Enemy((float) Math.random() * MyGdxGame.scrWidth, ((float) Math.random() * MyGdxGame.scrHeight) + MyGdxGame.scrHeight));
            }
        } else if (getLevel() == 9) {
            for (int i = 0; i < 70; i++) {
                enemies.add(new Enemy((float) Math.random() * MyGdxGame.scrWidth, ((float) Math.random() * MyGdxGame.scrHeight) + MyGdxGame.scrHeight));
            }
        } else if (getLevel() == 10) {
            for (int i = 0; i < 78; i++) {
                enemies.add(new Enemy((float) Math.random() * MyGdxGame.scrWidth, ((float) Math.random() * MyGdxGame.scrHeight) + MyGdxGame.scrHeight));
            }
        }
            else if (getLevel() == 11) {
                for (int i = 0; i < 88; i++) {
                    enemies.add(new Enemy((float) Math.random() * MyGdxGame.scrWidth, ((float) Math.random() * MyGdxGame.scrHeight) + MyGdxGame.scrHeight));
                }
            }
            else if (getLevel() == 12) {
                for (int i = 0; i < 150; i++) {
                    enemies.add(new Enemy((float) Math.random() * MyGdxGame.scrWidth, ((float) Math.random() * MyGdxGame.scrHeight) + MyGdxGame.scrHeight));
                }

        }




                return enemies;
        }
    }

