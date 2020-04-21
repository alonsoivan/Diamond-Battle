package com.ivn.diamondbattle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ivn.diamondbattle.Aplication;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// TODO ajustar tamaño, resizable, etc
		config.width = 900;
		config.height = 900;
		config.resizable = false;
		config.foregroundFPS = 30;
		config.backgroundFPS = 30;
		config.pauseWhenBackground = true;

		new LwjglApplication(new Aplication(), config);
	}
}
