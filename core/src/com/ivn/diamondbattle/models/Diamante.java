package com.ivn.diamondbattle.models;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import static com.ivn.diamondbattle.managers.ResourceManager.diamante;
import static com.ivn.diamondbattle.managers.ResourceManager.numeroDiamante;

public class Diamante extends Sprite {
    public int cantidad = 1;

    public Diamante(Vector2 pos){
        super(diamante);
        super.setPosition(pos.x,pos.y);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);

        numeroDiamante.draw(batch,cantidad+"",getX() + getWidth(),getY());
    }
}