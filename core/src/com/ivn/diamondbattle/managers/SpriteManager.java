package com.ivn.diamondbattle.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ArrayMap;
import com.ivn.diamondbattle.Aplication;
import com.ivn.diamondbattle.models.Arma;
import com.ivn.diamondbattle.models.Diamante;
import com.ivn.diamondbattle.models.Personaje;

import static com.ivn.diamondbattle.managers.HUD.drawHUD;
import static com.ivn.diamondbattle.managers.NetworkManager.miId;
import static com.ivn.diamondbattle.util.Util.getMousePosInGameWorld;
import static com.ivn.diamondbattle.util.Util.getRotation;

public class SpriteManager {

    private Aplication game;

    public static String miTextura;
    public static String miNombre;

    static public ArrayMap<Integer, Personaje> personajes;
    static public ArrayMap<Integer, Diamante> diamantes;
    static public ArrayMap<Integer, Arma> armas;


    public SpriteManager(Aplication game) {
        this.game = game;

        personajes = new ArrayMap<>();
        diamantes = new ArrayMap<>();
        armas = new ArrayMap<>();
    }

    /**
     * Controla la entrada por teclado del usuario
     */
    public void handleInput(){
        NetworkManager.MovePersonaje movePersonaje = new NetworkManager.MovePersonaje(miId);

        movePersonaje.dir = new Vector2(0,0);

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            NetworkManager.client.sendTCP(new NetworkManager.Disparar());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
           movePersonaje.dir = movePersonaje.dir.add(new Vector2(-5,0));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            movePersonaje.dir = movePersonaje.dir.add(new Vector2(5,0));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            movePersonaje.dir = movePersonaje.dir.add(new Vector2(0,5));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            movePersonaje.dir = movePersonaje.dir.add(new Vector2(0,-5));
        }



        // DIAGONALES
        if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.W)){
            movePersonaje.dir = movePersonaje.dir.add(new Vector2(-1,-1));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.S)){
            movePersonaje.dir = movePersonaje.dir.add(new Vector2(-1,1));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.S)){
            movePersonaje.dir = movePersonaje.dir.add(new Vector2(1,1));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.W)){
            movePersonaje.dir = movePersonaje.dir.add(new Vector2(1,-1));
        }



        if(miId != 0)
            personajes.get(miId).setRotation(getRotation());


        movePersonaje.poscionCursor =  new Vector2(getMousePosInGameWorld().x,getMousePosInGameWorld().y);


        movePersonaje.rotation = getRotation();


        movePersonaje.dir.scl(Gdx.graphics.getDeltaTime()*50f);
        NetworkManager.client.sendTCP(movePersonaje);
    }

    /**
     * Renderizado de cada frame
     */
    public void drawFrame() {

        CameraManager.renderer.render();

        CameraManager.batch.begin();


        ArrayMap.Keys<Integer> idsPersonajes = personajes.keys();
        while(idsPersonajes.hasNext()) {
            int id = idsPersonajes.next();
            personajes.get(id).draw(CameraManager.batch);
        }

        ArrayMap.Keys<Integer> idsDiamantes = diamantes.keys();
        while(idsDiamantes.hasNext()) {
            int id = idsDiamantes.next();
            diamantes.get(id).draw(CameraManager.batch);
        }

        ArrayMap.Keys<Integer> idsArmas = armas.keys();
        while(idsArmas.hasNext()) {
            int id = idsArmas.next();
            armas.get(id).draw(CameraManager.batch);
        }

        CameraManager.batch.end();

        drawHUD();
    }
}
