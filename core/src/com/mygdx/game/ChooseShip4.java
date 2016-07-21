package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by missionbit on 7/18/16.
 */
public class ChooseShip4 extends Button {
    public ChooseShip4(float x, float y) {
        super(x, y);
        sprite = new Sprite(new Texture("images/piqship.png"));
        setBounds();
    }

    @Override
    public void action() {
        MyGdxGame.yourShip = MyGdxGame.Ship.SHIP4;
        MyGdxGame.state = MyGdxGame.GameState.LEVEL_SELECT;
    }
}