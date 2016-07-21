package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by missionbit on 7/18/16.
 */
public class ChooseShip8 extends Button {
    public ChooseShip8(float x, float y) {
        super(x, y);
        sprite = new Sprite(new Texture("images/spaceship3.png"));
        sprite.setSize(MyGdxGame.scrWidth/8, MyGdxGame.scrWidth/8);
        setBounds();
    }

    @Override
    public void action() {
        MyGdxGame.yourShip = MyGdxGame.Ship.SHIP8;
        MyGdxGame.state = MyGdxGame.GameState.LEVEL_SELECT;
    }

}