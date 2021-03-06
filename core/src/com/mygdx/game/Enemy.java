package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ryan on 7/5/2016.
 */
public class Enemy {
    protected static final int NUM_ENEMIES = 130;
    private static AssetManager manager;
    private float speed;
    private Vector2 position, velocity;
    private Rectangle bounds;
    public static Sprite sprite;

    static {
        //memory management--loading image into manager
        String image= "image/asteroids.png";
        manager = new AssetManager();
        manager.load("images/asteroids.png", Texture.class);
        manager.finishLoading();
        sprite = new Sprite(new Texture("images/asteroids.png"));
    }

    public Enemy(float x, float y) {
        sprite.setSize(MyGdxGame.scrWidth/8, MyGdxGame.scrWidth/8);
        sprite.setScale(sprite.getWidth(), sprite.getHeight());
        position = new Vector2();
        setPosition(x, y);
        velocity = new Vector2();
        bounds = new Rectangle();
        setSpeed(MyGdxGame.scrHeight/150);
    }

    public void move() {
        setPosition(getPosition().x, getPosition().y-getSpeed());
    }

    public void followPlayer(Player player) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        float deltaX = player.getPosition().x - getPosition().x;
        float deltaY = player.getPosition().y - getPosition().y;
        float rotation = MathUtils.atan2(deltaY, deltaX) / MathUtils.PI * 180;

        setVelocity(MathUtils.cos(rotation / 180 * MathUtils.PI) * getSpeed(),
                MathUtils.sin(rotation / 180 * MathUtils.PI) * getSpeed());
        getPosition().mulAdd(getVelocity(), deltaTime);
    }

    public void update() {
        setBounds();
    }

    public void setSpeed(float spd) {speed = spd;}

    public float getSpeed() {return speed;}

    public void setPosition(float x, float y) {position.set(x, y);}

    public Vector2 getPosition() {return position;}

    public void setVelocity(float x, float y) {velocity.set(x, y);}

    public Vector2 getVelocity() {return velocity;}

    public void setBounds() {bounds.set(getPosition().x, getPosition().y, sprite.getWidth(), sprite.getHeight());}

    public Rectangle getBounds() {return bounds;}

    public void draw(SpriteBatch batch) {batch.draw(sprite, getPosition().x, getPosition().y, sprite.getWidth(), sprite.getHeight());}
}