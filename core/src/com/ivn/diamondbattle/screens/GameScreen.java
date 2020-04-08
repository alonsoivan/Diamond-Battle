package com.ivn.diamondbattle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.ivn.diamondbattle.Aplication;
import com.ivn.diamondbattle.managers.CameraManager;
import com.ivn.diamondbattle.managers.MyTimer;
import com.ivn.diamondbattle.managers.NetworkManager;
import com.ivn.diamondbattle.managers.SpriteManager;

import static com.ivn.diamondbattle.managers.NetworkManager.miId;

public class GameScreen implements Screen {

    private Aplication game;

    CameraManager cameraManager;
    SpriteManager spriteManager;
    NetworkManager networkManager;


    // TIMER PRUEBA
    MyTimer myTimer = new MyTimer(0,30);

    public GameScreen(Aplication game) {
        this.game = game;

        cameraManager = new CameraManager();
        spriteManager = new SpriteManager(game);
        networkManager = new NetworkManager();
    }

    @Override
    public void show() {

        cameraManager.init();

        // PRUEBA TIMER
        myTimer.start();
    }

    @Override
    public void render(float delta) {

        if(miId != 0)
            cameraManager.handleCamera();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteManager.drawFrame();
        spriteManager.handleInput();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
