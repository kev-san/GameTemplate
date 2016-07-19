package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by missionbit on 7/18/16.
 */
public class ChooseShip3 extends Button {
    public ChooseShip3(float x, float y) {
        super(x, y);
        sprite = new Sprite(new Texture("images/piq_109065.png"));
        setBounds();
    }

    @Override
    public void action() {
        MyGdxGame.yourShip = MyGdxGame.Ship.SHIP3;
        MyGdxGame.state = MyGdxGame.GameState.LEVEL_SELECT;
    }
}