package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ryan on 7/5/2016.
 */
public class StateChanger extends Button {
    Sprite startButton;
    Sprite resetButton;

    public StateChanger(float x, float y) {
        super(x, y);
        resetButton = new Sprite(new Texture("images/ResetButton.png"));
        startButton = new Sprite(new Texture("images/Start-Button.png"));
        resetButton.setScale(resetButton.getWidth(), resetButton.getHeight());
        startButton.setScale(startButton.getWidth(), startButton.getHeight());

        //sprite.setSize(YOUR WIDTH, YOUR HEIGHT);
        setBounds();

        sprite.setSize(MyGdxGame.scrWidth / 2 - 20, MyGdxGame.scrHeight / 10);
        setBounds();
    }

    public void action() {
        if (MyGdxGame.state == MyGdxGame.GameState.START) MyGdxGame.state = MyGdxGame.GameState.IN_GAME;
        else if (MyGdxGame.state == MyGdxGame.GameState.IN_GAME) MyGdxGame.state = MyGdxGame.GameState.GAME_OVER;
        //else MyGdxGame.state = MyGdxGame.GameState.START;
    }

    public void draw(SpriteBatch batch) {
        if (MyGdxGame.state == MyGdxGame.GameState.START) {
            batch.draw(startButton, position.x, position.y, sprite.getWidth(), sprite.getHeight());

        }
        else if (MyGdxGame.state == MyGdxGame.GameState.IN_GAME) {
            batch.draw(resetButton, position.x, position.y, sprite.getWidth(), sprite.getHeight());
        }
    }

}
