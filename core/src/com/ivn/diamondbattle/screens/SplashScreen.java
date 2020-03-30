package com.ivn.diamondbattle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.ivn.diamondbattle.Aplication;
import com.ivn.diamondbattle.managers.ResourceManager;

public class SplashScreen implements Screen {
    private Texture splashTexture;
    private Image splashImage;
    private Stage stage;

    private boolean splashDone = false;

    private Aplication game;

    public SplashScreen(Aplication game) {
        this.game = game;

        splashTexture = new Texture(Gdx.files.internal("ui/splash.png"));
        splashImage = new Image(splashTexture);
        stage = new Stage();
    }

    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        // Muestra la imagen de SplashScreen como una animación
        splashImage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f),
                Actions.delay(1.5f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        splashDone = true;
                    }
                })
        ));

        table.row().height(splashTexture.getHeight());
        table.add(splashImage).center();
        stage.addActor(table);

        // Lanza la carga de recursos
        ResourceManager.loadAllResources();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_CLEAR_VALUE);

        stage.act();
        stage.draw();

        // Comprueba si se han cargado todos los recursos
        if (ResourceManager.update()) {
            // Si la animación ha terminado se muestra ya el menú principal
            if (splashDone) {
                System.out.println(ResourceManager.assets.getAssetNames());
                game.setScreen(new MainMenuScreen(game));
            }
        }
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
        dispose();
    }

    @Override
    public void dispose() {
        splashTexture.dispose();
        stage.dispose();
    }
}
