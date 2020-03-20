package com.ivn.diamondbattle.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bala extends Sprite {

    public Bala(Vector2 position, Texture texture){
        super(texture);
        super.setPosition(position.x,position.y);
    }

    public void pintar(Batch batch) {
        super.draw(batch);
    }

}