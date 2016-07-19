package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by missionbit on 7/13/16.
 */
public class ChooseShip2 extends Button {
    public ChooseShip2(float x, float y) {
        super(x, y);
        sprite = new Sprite(new Texture("images/blue spaceship.png"));
        setBounds();
    }

    @Override
    public void action() {
        MyGdxGame.yourShip = MyGdxGame.Ship.SHIP2;
        MyGdxGame.state = MyGdxGame.GameState.LEVEL_SELECT;
    }
}