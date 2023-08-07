package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.Gamemap;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Settings settings = new Settings();
        settings.maxWidth = 4096;
        settings.maxHeight = 4096;
        settings.alias = false;
     //   TexturePacker.process(settings, "../assets", "../assets", "game");
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		//config.setWindowedMode(1366, 768);
		config.setForegroundFPS(60);
		config.useVsync(true);
		config.setTitle("helsinki");
		new Lwjgl3Application(new Gamemap(), config);

	}
}
