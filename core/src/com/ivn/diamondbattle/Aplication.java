package com.ivn.diamondbattle;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ivn.diamondbattle.screens.SplashScreen;

public class Aplication extends Game {

	private Skin skin;

	@Override
	public void create () {
		setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	/**
	 * Devuelve el skin utilizado para los menú del juego
	 * @return
	 */
	public Skin getSkin() {
		if (skin == null)
			skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

		return skin;
	}
}
