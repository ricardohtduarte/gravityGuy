package main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * 
 * classe main do jogo
 *
 */
public class GravityGuyDesktop {
	
	public static void main(String[] args) {
		
		LwjglApplicationConfiguration cfg =
			new LwjglApplicationConfiguration();
		
		cfg.title = Game.TITLE;
		
		
		// tamanho da janela que abre
		cfg.width = Game.V_WIDTH ;
     	cfg.height = Game.V_HEIGHT;
		
		new LwjglApplication(new Game(), cfg);
		
	}
	
}
