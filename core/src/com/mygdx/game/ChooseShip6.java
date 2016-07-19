package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by missionbit on 7/18/16.
 */
public class ChooseShip6 extends Button {
    public ChooseShip6(float x, float y) {
        super(x, y);
        sprite = new Sprite(new Texture("images/zero lines.png"));
        setBounds();
    }

    @Override
    public void action() {
        MyGdxGame.yourShip = MyGdxGame.Ship.SHIP6;
        MyGdxGame.state = MyGdxGame.GameState.LEVEL_SELECT;
    }

}