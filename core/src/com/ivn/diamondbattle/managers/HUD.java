package com.ivn.diamondbattle.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.ivn.diamondbattle.managers.NetworkManager.miId;
import static com.ivn.diamondbattle.managers.ResourceManager.diamante;
import static com.ivn.diamondbattle.managers.SpriteManager.personajes;

public class HUD {

    public static Batch batch = new SpriteBatch();
    public static BitmapFont font = new BitmapFont();

    public static void drawHUD(){
        // DIAMANTE

        batch.begin();
        batch.draw(diamante,30, Gdx.graphics.getHeight() - diamante.getHeight() - 30);
        font.draw(batch,"x "+personajes.get(miId).diamantes,diamante.getWidth() + 20, Gdx.graphics.getHeight() - diamante.getHeight() - 30);
        batch.end();
    }
}
