package com.ivn.diamondbattle.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.ivn.diamondbattle.Aplication;
import com.ivn.diamondbattle.managers.ResourceManager;
import com.ivn.diamondbattle.managers.SpriteManager;

import static com.ivn.diamondbattle.managers.SpriteManager.miNombre;
import static com.ivn.diamondbattle.managers.SpriteManager.miTextura;
import static com.ivn.diamondbattle.util.Constantes.TEXTURE_ATLAS_GAME_GUN;
import static com.ivn.diamondbattle.util.Constantes.TEXTURE_ATLAS_SELECTION;

public class MainMenuScreen implements Screen {

    private Aplication game;

    private Stage stage;

    private Image image;

    private TextField tfNombre;

    private TextButton rightButton;
    private TextButton leftButton;

    private int cont;

    public MainMenuScreen(Aplication game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();
        cont = 1;

        Label title = new Label("DIAMOND BATTLE", game.getSkin());
        title.setFontScale(2.5f);
        title.setBounds(Gdx.graphics.getWidth()/2 - title.getWidth() - 40,Gdx.graphics.getHeight()-100,200,50);


        image = new Image(ResourceManager.getRegion("1",TEXTURE_ATLAS_SELECTION));
        image.setBounds(270,150,image.getWidth(),image.getHeight());

        rightButton = new TextButton(">",game.getSkin());
        rightButton.setBounds(630,400,50,50);
        rightButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                cont ++;
                if(cont > 5)
                    cont = 1;

                if(cont < 1)
                    cont = 5;
                image.setDrawable(new TextureRegionDrawable(ResourceManager.getRegion(String.valueOf(cont),TEXTURE_ATLAS_SELECTION)));
            }
        });


        leftButton = new TextButton("<",game.getSkin());
        leftButton.setBounds(230,400,50,50);
        leftButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                cont --;
                if(cont > 5)
                    cont = 1;

                if(cont < 1)
                    cont = 5;
                image.setDrawable(new TextureRegionDrawable(ResourceManager.getRegion(String.valueOf(cont),TEXTURE_ATLAS_SELECTION)));
            }
        });


        final TextButton playButton = new TextButton("JUGAR", game.getSkin());
        playButton.setBounds(Gdx.graphics.getWidth()/2 - playButton.getWidth() - 40,20,200,50);
        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                String nombre = tfNombre.getText();

                if (!nombre.isEmpty() && !nombre.equals("NOMBRE")){

                    // Guardo la skin seleccionado por el jugador
                    miTextura = String.valueOf(cont);
                    miNombre = nombre;

                    game.setScreen(new GameScreen(game));
                    dispose();
                }
            }
        });
        playButton.setDisabled(true);


        tfNombre = new TextField("NOMBRE",game.getSkin());
        tfNombre.setAlignment(Align.center);
        tfNombre.setBounds(playButton.getX(),80,200,30);
        tfNombre.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                playButton.setDisabled(false);
            }
        });

        TextButton optionsButton = new TextButton("OPCIONES", game.getSkin());
        optionsButton.setBounds(20,20,100,50);
        optionsButton.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                //game.setScreen(new ConfigurationScreen(game));
            }
        });

        TextButton exitButton = new TextButton("SALIR", game.getSkin());
        exitButton.setBounds(Gdx.graphics.getWidth()- (exitButton.getWidth()) -60,20,100,50);
        exitButton.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                System.exit(0);
            }
        });

        Label aboutLabel = new Label("IVÁN ALONSO - 2020", game.getSkin());
        aboutLabel.setFontScale(1f);


        stage.addActor(title);
        stage.addActor(image);
        stage.addActor(rightButton);
        stage.addActor(leftButton);
        stage.addActor(tfNombre);
        stage.addActor(playButton);
        stage.addActor(optionsButton);
        stage.addActor(exitButton);
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void render(float delta) {
        // Limpia la pantalla
        Gdx.gl.glClearColor(0, 0, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
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
        stage.dispose();
    }
}
