package com.anderson.passaro.desktop;

import com.anderson.passaro.Jogo;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "Passaro";
    config.width = 272;
    config.height = 408;
    new LwjglApplication(new Jogo(), config);
	}
}
