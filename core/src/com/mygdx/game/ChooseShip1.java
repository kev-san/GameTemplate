package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by missionbit on 7/13/16.
 */
public class ChooseShip1 extends Button {
    public ChooseShip1(float x, float y) {
        super(x, y);
        sprite = new Sprite(new Texture("images/Spaceship.png"));
        setBounds();
    }

    @Override
    public void action() {
        MyGdxGame.yourShip = MyGdxGame.Ship.SHIP1;
        MyGdxGame.state = MyGdxGame.GameState.LEVEL_SELECT;
    }
}