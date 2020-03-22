package com.ivn.diamondbattle.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import static com.ivn.diamondbattle.managers.ResourceManager.*;

public class Personaje extends Sprite {

    public String nombre;
    public int vida;
    public boolean miPersonaje;

    public Array<Bala> balas = new Array<>();

    public Personaje(TextureRegion texture, String nombre, boolean miPersonaje){
        super(texture);
        this.nombre = nombre;
        this.miPersonaje = miPersonaje;
        this.vida = 100;
    }

    public void pintar(Batch batch){

        super.draw(batch);


        // NOMBRE PANTALLA
        if(miPersonaje)
            miNombrePantalla.draw(batch,nombre,super.getX(),super.getY());
        else
            nombrePantalla.draw(batch,nombre,super.getX(),super.getY());

        // VIDA PANTALLA
        container.draw(batch, super.getX(), super.getY() + super.getHeight(), 100 * 2  +10, 30);
        health.draw(batch, super.getX() + 5, super.getY() + super.getHeight() + 5, vida * 2, 20);

        for (Bala bala : balas)
            bala.pintar(batch);
    }

    public void disparar(){
        // TODO VARIOS DISPAROS.  DISPARO PISTOLA, DISPARO ESCOPETA, ETC

        Bala bala = new Bala( new Vector2(super.getX(),super.getY()),new Texture(""));
        bala.setRotation(super.getRotation());
        balas.add(bala);
    }

    public void setVida(int vida){

        // TODO ANIMACION QUITARVIDA Y ANIMACION SUBIR VIDA
        if(vida > this.vida) {
            // ANIMACION ++VIDA
            this.vida = vida;
        }else if(vida < this.vida) {
            // ANIMACION --VIDA
            this.vida = vida;
        }
    }
}