package com.ivn.diamondbattle.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.ivn.diamondbattle.managers.ResourceManager;

public class Bala extends Sprite {

    public Bala(Vector2 position){
        super((Texture) ResourceManager.assets.get("balas/bala.png"));
        super.setPosition(position.x,position.y);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }

}