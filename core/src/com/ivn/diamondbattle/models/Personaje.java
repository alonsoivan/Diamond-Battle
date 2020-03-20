package com.ivn.diamondbattle.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Personaje extends Sprite {

    public Array<Bala> balas = new Array<>();

    public Personaje(TextureRegion texture){
        super(texture);
    }

    public void pintar(Batch batch){

        //super.setPosition(position.x,position.y);
        super.draw(batch);

        for (Bala bala : balas)
            bala.pintar(batch);
    }

    public void disparar(){
        // TODO VARIOS DISPAROS.  DISPARO PISTOLA, DISPARO ESCOPETA, ETC

        Bala bala = new Bala( new Vector2(super.getX(),super.getY()),new Texture(""));
        bala.setRotation(super.getRotation());
        balas.add(bala);
    }
}