package net.estemon.studio;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import net.estemon.studio.config.GameConfig;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode((int) GameConfig.WIDTH, (int) GameConfig.HEIGHT);
		config.setForegroundFPS(60);
		config.setTitle("SimpleSnake");
		new Lwjgl3Application(new SimpleSnakeGame(), config);
	}
}
