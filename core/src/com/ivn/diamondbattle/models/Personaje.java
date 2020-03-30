package com.ivn.diamondbattle.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.SnapshotArray;

import static com.ivn.diamondbattle.managers.ResourceManager.*;

public class Personaje extends Sprite {

    public String nombre;
    public int vida;
    public boolean miPersonaje;
    public Laser laser;
    public float tamañoLaser;
    public int diamantes;

    public SnapshotArray<Bala> balas = new SnapshotArray<>();

    public Personaje(TextureRegion texture, String nombre, boolean miPersonaje){
        super(texture);
        super.setPosition(0,0);

        this.nombre = nombre;
        this.miPersonaje = miPersonaje;
        this.vida = 100;

        // LASER
        this.laser = new Laser();
        this.tamañoLaser = 500;
        this.laser.color = Color.BLUE;
        this.laser.rayColor = Color.BLUE;

    }

    @Override
    public void draw(Batch batch){

        super.draw(batch);

        // LASER
        laser.distance = tamañoLaser;
        laser.position = new Vector2(getX() , getY()) ;
        laser.degrees = getRotation() -90;
        laser.render(Gdx.graphics.getDeltaTime());


        // NOMBRE PANTALLA
        if(miPersonaje)
            miNombrePantalla.draw(batch,nombre,super.getX(),super.getY());
        else
            nombrePantalla.draw(batch,nombre,super.getX(),super.getY());


        // VIDA PANTALLA
        container.draw(batch, super.getX(), super.getY() + super.getHeight(), 100   +10, 20);
        health.draw(batch, super.getX() + 5, super.getY() + super.getHeight() + 5, vida , 10);


        // TODO ARREGLAR NULL POINTER EXCEPTION
        // TODO ARREGLAR NOSUCH
        Object[] items = balas.begin();
        for (int i = 0, n = balas.size; i < n; i++) {
            Object item = items[i];
            Bala bala = (Bala)item;

            if(bala != null)
                bala.draw(batch);
        }
        balas.end();

    }

    public void disparar(){
        // TODO revisar si quitar

        Bala bala = new Bala( new Vector2(super.getX(),super.getY()));
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