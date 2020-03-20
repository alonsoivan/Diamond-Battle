package com.ivn.diamondbattle.managers;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapsManager {

    public static TiledMap getRandomMap(){

        //TODO retornar mapas aleatorios

        return new TmxMapLoader().load("levels/pruebita2.tmx");
    }
}
