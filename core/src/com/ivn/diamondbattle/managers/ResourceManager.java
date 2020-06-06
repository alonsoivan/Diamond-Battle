package com.ivn.diamondbattle.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.io.File;

import static com.ivn.diamondbattle.util.Constantes.TEXTURE_ATLAS_GAME_GUN;
import static com.ivn.diamondbattle.util.Constantes.TEXTURE_ATLAS_SELECTION;

public class ResourceManager {
    public static AssetManager assets = new AssetManager();


    public static FreeTypeFontGenerator generator;
    public static FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    // NOMBRE PANTALLA
    public static BitmapFont nombrePantalla;
    public static BitmapFont miNombrePantalla;


    // BARRA VIDA
    public static BitmapFont vidaPantalla;

    public static Texture textureVida;
    public static Texture textureContainerVida;
    public static NinePatch health;
    public static NinePatch container;


    // LASER
    public static Texture tbegin1;
    public static Texture tbegin2;
    public static Texture tmid1;
    public static Texture tmid2;
    public static Texture tend1;
    public static Texture tend2;


    // DIAMANTE PANTALLA
    public static Texture diamante;
    public static BitmapFont numeroDiamante;


    // TIMER
    public static String timer;


    // WEAPONS
    public static Texture gun;
    public static Texture shotgun;
    public static Texture rafagas;

    /**
     * Carga todos los recursos del juego
     */
    public static void loadAllResources() {
        //assets.load("my_texture_atlas.pack", TextureAtlas.class);

        //loadSounds();
        //loadMusics();

        assets.load(TEXTURE_ATLAS_GAME_GUN, TextureAtlas.class);
        assets.load(TEXTURE_ATLAS_SELECTION, TextureAtlas.class);
        assets.load("balas/bala.png",Texture.class);


        // LASER
        // TODO HACER SPRITESHEET
        tbegin1 = new Texture("laser/beamstart1.png");
        tbegin2 = new Texture("laser/beamstart2.png");
        tmid1 = new Texture("laser/beammid1.png");
        tmid2 = new Texture("laser/beammid2.png");
        tend1 = new Texture("laser/beamend1.png");
        tend2 = new Texture("laser/beamend2.png");

        // NOMBRE PANTALLA
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/LemonMilk.otf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.WHITE;
        parameter.size = 20;

        nombrePantalla = generator.generateFont(parameter);
        nombrePantalla.setUseIntegerPositions(false);

        parameter.color = Color.RED;
        miNombrePantalla = generator.generateFont(parameter);
        miNombrePantalla.setUseIntegerPositions(false);


        // DIAMANTE
        diamante = new Texture("diamantes/diamante.png");
        parameter.color = Color.WHITE;
        parameter.size = 20;
        numeroDiamante = generator.generateFont(parameter);
        numeroDiamante.setUseIntegerPositions(false);


        // BARRA VIDA
        parameter.color = Color.WHITE;
        vidaPantalla = generator.generateFont(parameter);

        textureVida = new Texture("hud/vida.jpg");
        textureContainerVida = new Texture("hud/container.jpg");

        health = new NinePatch(textureVida, 0, 0, 0, 0);
        container = new NinePatch(textureContainerVida, 5, 5, 5, 5);


        // TIMER
        timer = "";


        // WEAPONS
        rafagas = new Texture("weapons/rafagas.png");
        gun = new Texture("weapons/gun.png");
        shotgun = new Texture("weapons/shotgun.png");
    }

    /** Actualiza la carga de recursos */
    public static boolean update() {
        return assets.update();
    }

    /**
     * Carga los sonidos
     */
    public static void loadSounds() {
        assets.load("sounds" + File.separator + "game_begin.wav", Sound.class);
    }

    /**
     * Carga las músicas
     */
    public static void loadMusics() {
        assets.load("musics" + File.separator + "bso.mp3", Music.class);
    }

    /**
     * Obtiene una región de textura o la primera de una animación
     * @param name
     * @return
     */
    public static TextureRegion getRegion(String name, String texture) {
        return assets.get(texture, TextureAtlas.class).findRegion(name);
    }

    /**
     * Obtiene una región de textura determinada de las que forman una animación
     */
    /*
    public static TextureRegion getRegion(String name, int position) {
        return assets.get(TEXTURE_ATLAS, TextureAtlas.class).findRegion(name, position);
    }
*/
    /**
     * Obtiene todas las regiones de textura que forman una misma animación
     * @param name
     * @return
     *//*
    public static Array<TextureAtlas.AtlasRegion> getRegions(String name) {
        return assets.get(TEXTURE_ATLAS, TextureAtlas.class).findRegions(name);
    }
*/
    /**
     * Obtiene un sonido determinado
     */
    public static Sound getSound(String name) {
        return assets.get(name, Sound.class);
    }

    /**
     * Obtiene una música determinada
     */
    public static Music getMusic(String name) {
        return assets.get(name, Music.class);
    }
}