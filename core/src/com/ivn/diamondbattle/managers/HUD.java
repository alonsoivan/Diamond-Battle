package com.ivn.diamondbattle.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import static com.ivn.diamondbattle.managers.NetworkManager.miId;
import static com.ivn.diamondbattle.managers.ResourceManager.diamante;
import static com.ivn.diamondbattle.managers.SpriteManager.personajes;

public class HUD {

    public static Batch batch = new SpriteBatch();
    public static BitmapFont font = new BitmapFont();

    public static ArrayList<String> ranking = new ArrayList<>();

    public static void drawHUD(){


        batch.begin();

        // DIAMANTE
        batch.draw(diamante,30, Gdx.graphics.getHeight() - diamante.getHeight() - 30);
        font.draw(batch,"x "+personajes.get(miId).diamantes,diamante.getWidth() + 20, Gdx.graphics.getHeight() - diamante.getHeight() - 30);


        // TIMER
        font.draw(batch,ResourceManager.timer,Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-50);


        // RANKING
        int altura = 50;
        for(String rank : ranking) {
            font.draw(batch, rank, Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - altura);
            altura += 50;
        }


        batch.end();
    }
}
