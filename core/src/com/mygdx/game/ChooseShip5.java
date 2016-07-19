package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by missionbit on 7/18/16.
 */
public class ChooseShip5 extends Button {
    public ChooseShip5(float x, float y) {
        super(x, y);
        sprite = new Sprite(new Texture("images/space shooter small.png"));
        setBounds();
    }

    @Override
    public void action() {
        MyGdxGame.yourShip = MyGdxGame.Ship.SHIP5;
        MyGdxGame.state = MyGdxGame.GameState.IN_GAME;
    }

}