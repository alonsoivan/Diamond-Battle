package com.ivn.diamondbattle.models;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.ivn.diamondbattle.util.Util;

import static com.ivn.diamondbattle.managers.ResourceManager.*;

public class Arma extends Sprite {
    public Util.Arma tipo;

    public Arma(Vector2 pos, Util.Arma tipo){
        super(gun);
        super.setPosition(pos.x,pos.y);
        this.tipo = tipo;

        if(tipo.equals(Util.Arma.RAFAGA)) // y su apariencia
            super.setTexture(rafagas);
        else if(tipo.equals(Util.Arma.GUN))
            super.setTexture(gun);
        else if(tipo.equals(Util.Arma.SHOTGUN)) {
            super.setTexture(shotgun);
        }

        System.out.println(tipo);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }
}