package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.assets.loaders.BulletLoader;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
    protected static float scrWidth;
    protected static float scrHeight;

    protected enum GameState {START, SPACESHIP_SELECT, IN_GAME, GAME_OVER}
    protected static GameState state;
    protected enum Ship {SHIP1, SHIP2, SHIP3, SHIP4, SHIP5, SHIP6, SHIP7, SHIP8}
    protected static Ship yourShip;
    protected static Vector2 gravity;

    private AssetManager manager; //EXPERIMENTAL S***
    protected static Preferences preferences;
    protected static int highScore;

    private SpriteBatch batch;
    private static Vector3 tap; //holds the position of tap location
    private BitmapFont font;
    private GlyphLayout layout;
    private Player player;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private Music music;
    private Sound shootSound, matchSound;

    public static OrthographicCamera camera; //camera is your game world camera
    public static OrthographicCamera uiCamera; //uiCamera is your heads-up display

    private DebugButton debug;
    private StateChanger stateChanger;
    private ChooseShip1 choose1;
    private ChooseShip2 choose2;
    private ChooseShip3 choose3;
    private ChooseShip4 choose4;
    private ChooseShip5 choose5;
    private ChooseShip6 choose6;
    private ChooseShip7 choose7;
    private ChooseShip8 choose8;
    Texture background;
    private int score;

    @Override
    public void create() {
        scrWidth = Gdx.graphics.getWidth();
        scrHeight = Gdx.graphics.getHeight();
        gravity = new Vector2();

        preferences = new Preferences("Preferences");
        //if there are no high scores, then make one
        if (preferences.getInteger("highScore", 0) == 0) {
            highScore = 0;
            preferences.putInteger("highScore", highScore);
        } else
            highScore = preferences.getInteger("highScore", 0); //set highScore to saved value


        /*
        =====EXPERIMENTAL S***=====
        manager = new AssetManager();
        manager.setLoader(Bullet.class, new BulletLoader(new InternalFileHandleResolver()));
        manager.load("Bullet.java", Bullet.class);
        manager.finishLoading();
        //manager.load(new AssetDescriptor<Bullet>("Bullet.java", Bullet.class, new BulletLoader.BulletParameter()));
        =====EXPERIMENTAL S***=====
        */
        background = new Texture("images/space background.jpeg");
        batch = new SpriteBatch();
        tap = new Vector3(); //location of tap
        font = new BitmapFont(Gdx.files.internal("fonts/arial.fnt"),
                Gdx.files.internal("fonts/arial.png"), false);
        music = Gdx.audio.newMusic(Gdx.files.internal("music/Deep space.mp3"));
        music.setLooping(true);
        music.play();
        matchSound = Gdx.audio.newSound(Gdx.files.internal("sounds/matchStart.wav"));
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Laser Blast Sound Effects.mp3"));
        layout = new GlyphLayout();
        player = new Player();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, scrWidth, scrHeight);
        uiCamera = new OrthographicCamera();
        uiCamera.setToOrtho(false, scrWidth, scrHeight);
        uiCamera.update();

        debug = new DebugButton(10, 10);
        stateChanger = new StateChanger(scrWidth / 2 + 10, 10);
        choose1 = new ChooseShip1(scrWidth/5, 7 * scrHeight/9);
        choose2 = new ChooseShip2(3.5f * scrWidth/5, 7 * scrHeight/9);
        choose3 = new ChooseShip3(scrWidth/5, 5 * scrHeight/9);
        choose4 = new ChooseShip4(3.5f * scrWidth/5, 5 * scrHeight/9);
        choose5 = new ChooseShip5(scrWidth/5, 3 * scrHeight/9);
        choose6 = new ChooseShip6(3.5f * scrWidth/5, 3 * scrHeight/9);
        choose7 = new ChooseShip7(scrWidth/5, scrHeight/9);
        choose8 = new ChooseShip8(3.5f * scrWidth/5, scrHeight/9);

        resetGame();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateGame();
        drawGame();
    }

    private void resetGame() {
        state = GameState.START;
        yourShip = Ship.SHIP1;
        gravity.set(0, -25);
        player.reset();
        bullets.clear();
        enemies.clear();
        score = 0;
    }

    /*
      - gets and translates coordinates of tap to game world coordinates
      - you don't need to touch this at all
    */
    public static Vector3 getTapPosition() { //gets and translates coordinates of tap to game world coordinates
        tap.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        return camera.unproject(tap);
    }

    private void updateGame() {
        player.update();
        for (Enemy enemy : enemies) {
            enemy.update();
        }

        if (state == GameState.START) {
            if (debug.isPressed()) debug.action();
            if (stateChanger.isPressed()) stateChanger.action();
        }

        else if (state == GameState.SPACESHIP_SELECT) {
            if (choose1.isPressed()) {
                choose1.action();
                player.editSprite(1);
                matchSound.play();
            }
            if (choose2.isPressed()) {
                choose2.action();
                player.editSprite(2);
                matchSound.play();
            }
            if (choose3.isPressed()) {
                choose3.action();
                player.editSprite(3);
                matchSound.play();
            }
            if (choose4.isPressed()) {
                choose4.action();
                player.editSprite(4);
                matchSound.play();
            }
            if (choose5.isPressed()) {
                choose5.action();
                player.editSprite(5);
                matchSound.play();
            }
            if (choose6.isPressed()) {
                choose6.action();
                player.editSprite(6);
                matchSound.play();
            }
            if (choose7.isPressed()) {
                choose7.action();
                player.editSprite(7);
                matchSound.play();
            }
            if (choose8.isPressed()) {
                choose8.action();
                player.editSprite(8);
                matchSound.play();
            }

            if (Gdx.input.justTouched()) {
                for (int i = 0; i < Enemy.NUM_ENEMIES; i++)
                    enemies.add(new Enemy((float)Math.random() * scrWidth, (float)Math.random() * scrHeight / 2 + scrHeight /2));
            }
        }

        else if (state == GameState.IN_GAME) {
            for (Enemy enemy : enemies) {enemy.move();}
            if (stateChanger.isPressed()) stateChanger.action();
            if (Gdx.input.justTouched()) {
                /*
                =====EXPERIMENTAL S***=====
                Bullet bullet = manager.get("Bullet.java");
                bullets.add(bullet);
                =====EXPERIMENTAL S***=====
                */
                shootSound.play();
                player.shoot(bullets);
            }

            //bullet-only codes
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).update();
                //removes bullets from memory if they go off screen
                if (bullets.get(i).getPosition().x > scrWidth
                        || bullets.get(i).getPosition().x < 0 - bullets.get(i).getBounds().getWidth()
                        || bullets.get(i).getPosition().y > scrHeight
                        || bullets.get(i).getPosition().y < 0 - bullets.get(i).getBounds().getHeight()) {
                    bullets.remove(i);
                }
            }

            //remove bullet and enemy when they collide
            for (int j = 0; j < enemies.size(); j++) {
                //player die
                if (enemies.get(j).getBounds().overlaps(player.getBounds())) {
                    state = GameState.GAME_OVER;
                }
                //remove bullet and enemy when they collide
                for (int i = 0; i < bullets.size(); i++)  {
                    if (enemies.get(j).getBounds().overlaps(bullets.get(i).getBounds()))  {
                        enemies.remove(j);
                        bullets.remove(i);
                        score = score + 1;
                    }
                }
            }
            for (int i = 0; i < enemies.size(); i++) {
                if(enemies.get(i).getPosition().y < 0) {
                    enemies.remove(i);
                }
            }
            if (enemies.size() == 0) {
                state = GameState.GAME_OVER;
            }
        }

        else { //state is GAME_OVER
            //sets score to high score if you beat highScore
            if (score > highScore) {
                highScore = score;
                preferences.putInteger("highScore", score);
            }
            preferences.flush(); //saves
            if (Gdx.input.justTouched()) {
                resetGame();
            }
        }
    }

	private void drawGame() {
        //game world camera
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, scrWidth, scrHeight);
        font.setColor(Color.WHITE);
        if (state == GameState.START) {
            //start s*** here
        } else if (state == GameState.SPACESHIP_SELECT) {
            choose1.draw(batch);
            choose2.draw(batch);
            choose3.draw(batch);
            choose4.draw(batch);
            choose5.draw(batch);
            choose6.draw(batch);
            choose7.draw(batch);
            choose8.draw(batch);
        } else if (state == GameState.IN_GAME) {
            for (Bullet bullet : bullets) bullet.draw(batch);
            player.draw(batch);
            for (Enemy enemy : enemies) enemy.draw(batch);
        } else {
            //gameover s*** here
        }
        batch.end();

        //game ui camera
        batch.setProjectionMatrix(uiCamera.combined);
        batch.begin();

        if (debug.debug) {
            font.draw(batch, "Game state: " + MyGdxGame.state, 20, MyGdxGame.scrHeight - 20);
            font.draw(batch, "Bullet count: " + bullets.size(), 20, MyGdxGame.scrHeight - 70);
            font.draw(batch, "Number of enemies: " + enemies.size(), 20, MyGdxGame.scrHeight - 120);
        }

        if (state == GameState.START) {
            stateChanger.draw(batch);
            debug.draw(batch);
            layout.setText(font, "Tap to start!");
            font.draw(batch, layout, scrWidth / 2 - layout.width / 2, scrHeight / 2);
        } else if (state == GameState.SPACESHIP_SELECT) {
            layout.setText(font, "Select a spaceship!");
            font.draw(batch, layout, scrWidth / 2 - layout.width / 2, scrHeight / 2);
        } else if (state == GameState.IN_GAME) {
            stateChanger.draw(batch);
            font.draw(batch, "Score: " + score, 20, MyGdxGame.scrHeight - 50);
            font.draw(batch, "High Score: " + highScore , 20, MyGdxGame.scrHeight - 100);
        } else { //state == GameState.GAME_OVER
            layout.setText(font, "Tap to restart!");
            font.draw(batch, layout, scrWidth / 2 - layout.width / 2, Gdx.graphics.getHeight() / 2);
        }
        batch.end();
    }
}