package com.ivn.diamondbattle.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

import static com.ivn.diamondbattle.managers.NetworkManager.miId;
import static com.ivn.diamondbattle.managers.SpriteManager.personajes;
import static com.ivn.diamondbattle.util.Constantes.*;

public class CameraManager {

    private static CameraManager instance = null;


    public static Batch batch;

    public static OrthographicCamera camera;
    public static OrthogonalTiledMapRenderer renderer;
    public static TiledMap map;


    public CameraManager() {
        instance = this;

        init();
    }

    /**
     * Inicializa la cámara de la partida
     */
    public void init() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, TILES_IN_CAMERA_WIDTH * TILE_WIDTH, TILES_IN_CAMERA_HEIGHT * TILE_WIDTH);
        camera.update();

        map = MapsManager.getRandomMap();
        renderer = new OrthogonalTiledMapRenderer(map, 1);
        batch = CameraManager.renderer.getBatch();
    }

    /**
     * Controla la cámara de la partida
     */
    public void handleCamera() {

        float lerp = 4f;
        Vector3 position = camera.position;
        position.x += (personajes.get(miId).getX() - position.x) * lerp * Gdx.graphics.getDeltaTime();
        position.y += (personajes.get(miId).getY() - position.y) * lerp * Gdx.graphics.getDeltaTime();


        // These values likely need to be scaled according to your world coordinates.

        // The top boundary of the map (y + height)
        float mapTop = 0 + TILES_IN_CAMERA_HEIGHT * TILE_WIDTH * 5.33f;

        // The bottom boundary of the map (y)
        int mapBottom = 0;

        // The left boundary of the map (x)
        int mapLeft = 0;

        // The right boundary of the map (x + width)
        float mapRight = TILES_IN_CAMERA_WIDTH * TILE_WIDTH * 5.33f ;

        // The camera dimensions, halved
        float cameraHalfWidth = camera.viewportWidth * .5f;
        float cameraHalfHeight = camera.viewportHeight * .5f;


        // Move camera after player as normal
        float cameraLeft = camera.position.x - cameraHalfWidth;
        float cameraRight = camera.position.x + cameraHalfWidth;
        float cameraBottom = camera.position.y - cameraHalfHeight;
        float cameraTop = camera.position.y + cameraHalfHeight;



        // Horizontal axis
        if(mapRight < camera.viewportWidth)
        {
            camera.position.x = mapRight / 2;
        }
        else if(cameraLeft <= mapLeft)
        {
            camera.position.x = mapLeft + cameraHalfWidth;
        }
        else if(cameraRight >= mapRight)
        {
            camera.position.x = mapRight - cameraHalfWidth;
        }


        // Vertical axis
        if(mapTop < camera.viewportHeight)
        {
            camera.position.y = mapTop / 2;
        }
        else if(cameraBottom <= mapBottom)
        {
            camera.position.y = mapBottom + cameraHalfHeight;
        }
        else if(cameraTop >= mapTop)
        {
            camera.position.y = mapTop - cameraHalfHeight;
        }


        camera.update();
        renderer.setView(camera);
    }

    /**
     * Accessor
     * @return
     */
    @SuppressWarnings("unchecked")
    public static final <T extends CameraManager> T get() {
        return (T) instance;
    }
}
