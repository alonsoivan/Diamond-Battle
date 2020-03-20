package com.ivn.diamondbattle.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ArrayMap;
import com.ivn.diamondbattle.Aplication;
import com.ivn.diamondbattle.models.Personaje;

import static com.ivn.diamondbattle.managers.NetworkManager.miId;
import static com.ivn.diamondbattle.util.Util.getMousePosInGameWorld;
import static com.ivn.diamondbattle.util.Util.getRotation;

public class SpriteManager {

    private Aplication game;

    private Batch batch;

    public static String miTextura;

    static public ArrayMap<Integer, Personaje> personajes;
    //public Array<Item> items;


    /*Array<SpriteAnimation> animations;
    private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject () {
            return new Rectangle();
        }
    };
    public Array<Rectangle> tiles;
    */

    public SpriteManager(Aplication game) {
        this.game = game;

        personajes = new ArrayMap<>();
        batch = CameraManager.renderer.getBatch();
    }

    /**
     * Controla la entrada por teclado del usuario
     */
    public void handleInput(){
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector3 pos = getMousePosInGameWorld();
            //client.sendTCP(new Disparar(new Vector2(pos.x,pos.y)));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            //client.sendTCP(new Vector2(-5,0));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            //client.sendTCP(new Vector2(5,0));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            //client.sendTCP(new Vector2(0,5));
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            //client.sendTCP(new Vector2(0,-5));
        }

        if(miId != 0)
            personajes.get(miId).setRotation(getRotation());
    }

    /**
     * Renderizado de cada frame
     */
    public void drawFrame() {

        CameraManager.renderer.render();


        batch.begin();

        //drawHud();

        ArrayMap.Keys<Integer> ids = personajes.keys();
        while(ids.hasNext()) {
            personajes.get(ids.next()).pintar(batch);
        }

        batch.end();
    }
}
