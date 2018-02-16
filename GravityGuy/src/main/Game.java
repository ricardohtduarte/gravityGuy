package main;

import handlers.input.MyInput;
import handlers.input.MyInputProcessor;
import handlers.manage.GameStateManager;

import handlers.visual.Tex;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * 
 * classe que representa e guarda 
 * todo a informacao que se relaciona com o jogo
 *
 */
public class Game implements ApplicationListener {
	
	public static final String TITLE = "Gravity Guy";
	public static final int V_WIDTH = 1000;   //1000
	public static final int V_HEIGHT = 600;   //600
	public static final int SCALE = 2;
	public static final float PPM = 100;  // pixel por metro
	public static final float CIC = 61.5f;   // converte incremento da camera
	public static Tex textures; // instancia onde serao guardadas as texturas
	
	
	public static final float STEP = 1 / 60f;
	private float accum;

	
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	
	private GameStateManager gsm;
	
	/**
	 * criador do jogo, faz o load de todas 
	 * as imagens necessarias ao jogo, cria um spritebatch, 
	 * duas camaras e um GameStateManager
	 */
	public void create() {
		
		Texture.setEnforcePotImages(false);
		
		Gdx.input.setInputProcessor(new MyInputProcessor());
		
		
		textures = Tex.getTex();
		// load images
		textures.loadTexture("res/images/guyDown.png", "guyDown");
		textures.loadTexture("res/images/guyUp.png", "guyUp");
		textures.loadTexture("res/images/guy2Down.png", "guyDown2");
		textures.loadTexture("res/images/guy2Up.png", "guyUp2");
		textures.loadTexture("res/images/enemyDown.png", "enemyDown");
		textures.loadTexture("res/images/enemyUp.png", "enemyUp");
		
		textures.loadTexture("res/images/skyline.png", "skyline");
		textures.loadTexture("res/images/skyline2.jpg", "skyline2");
		textures.loadTexture("res/images/skyline3.png", "skyline3");		
		textures.loadTexture("res/images/circuit board.jpg", "fundo2");
		textures.loadTexture("res/images/grav3.jpg", "menu");
		
		textures.loadTexture("res/images/singleplayer.png", "single");
		textures.loadTexture("res/images/multiplayer.png", "multi");
		textures.loadTexture("res/images/exit.png", "exit");
		textures.loadTexture("res/images/level1B.png", "B1");
		textures.loadTexture("res/images/level2B.png", "B2");
		textures.loadTexture("res/images/level3B.png", "B3");
		textures.loadTexture("res/images/mainMenuB.png", "mainB");
		textures.loadTexture("res/images/win1.png", "win1");
		textures.loadTexture("res/images/win2.png", "win2");
		textures.loadTexture("res/images/tryagain.png", "retry");
		textures.loadTexture("res/images/easy.png", "easy");
		textures.loadTexture("res/images/medium.png", "medium");
		textures.loadTexture("res/images/hard.png", "hard");
		textures.loadTexture("res/images/difficulty.png", "difficulty");
		textures.loadTexture("res/images/thunder2.png", "thunder");
	
		textures.loadTexture("res/images/levelcleared.png", "cleared");
		textures.loadTexture("res/images/you lost.png", "lost");
		textures.loadTexture("res/images/playerpress.png", "playerp");
		textures.loadTexture("res/images/player1press.png", "player1p");
		textures.loadTexture("res/images/player2press.png", "player2p");
		textures.loadTexture("res/images/selectthelevel.png", "select");
		
		
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		gsm = new GameStateManager(this);
				
	}
	
	/**
	 *  faz o update e render de acordo com o step, 
	 *  renderiza a cada 60 frames
	 *  
	 */
	public void render() {
		
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP) {
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
			MyInput.update();
		}
		
	}
	
	/**
	 * funcao abstrata que deve ser implementada, 
	 * nao apresenta qualquer funcao
	 */
	public void dispose() {
		
	}
	
	/**
	 * 
	 * @return retorna o spritebatch do jogo
	 */
	public SpriteBatch getSpriteBatch() { return sb; }
	/**
	 * 
	 * @return retorna a camara principal do jogo
	 */
	public OrthographicCamera getCamera() { return cam; }
	/**
	 * 
	 * @return retorna a camara secundaria do jogo
	 */
	public OrthographicCamera getHUDCamera() { return hudCam; }
	
	/**
	 * funcao abstrata que deve ser implementada, 
	 * nao apresenta qualquer funcao
	 */
	public void resize(int w, int h) {}
	/**
	 * funcao abstrata que deve ser implementada, 
	 * nao apresenta qualquer funcao
	 */
	public void pause() {}
	/**
	 * funcao abstrata que deve ser implementada, 
	 * nao apresenta qualquer funcao
	 */
	public void resume() {}
	
}
