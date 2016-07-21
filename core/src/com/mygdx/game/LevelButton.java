package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Ryan on 7/14/2016.
 */
public class LevelButton extends Button {
    private int level;

    public LevelButton(float x, float y, int number) {
        super(x, y);
        level = number;
        sprite = new Sprite(new Texture("images/mario blank space.png"));
        sprite.setSize(MyGdxGame.scrWidth / 6, MyGdxGame.scrHeight / 9);
        sprite.setScale(sprite.getWidth(), sprite.getHeight());
        setBounds();
    }

    public void pressedAction() {
        MyGdxGame.state = MyGdxGame.GameState.IN_GAME;
    }

    public void draw(SpriteBatch batch, BitmapFont font) {
        batch.draw(sprite, position.x, position.y, sprite.getWidth(), sprite.getHeight());
        font.setColor(Color.DARK_GRAY);
        font.draw(batch, "" + level, getBounds().x +35, getBounds().y+55);

    }

}
