package states;

import handlers.manage.GameStateManager;
import handlers.visual.Background;
import main.Game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * 
 * classe que representa o estado de um jogo
 *
 */
public abstract class GameState {
	
	protected GameStateManager gsm;
	protected Game game;
	
	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	protected OrthographicCamera hudCam;
	
	protected boolean debug = false;
	protected Background bg;

	protected World world;
	protected Box2DDebugRenderer b2dRenderer;
	
	/**
	 * contrutor da classe Gamestate
	 * @param gsm gestor de estados de jogo
	 */
	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		game = gsm.game();
		sb = game.getSpriteBatch();
		cam = game.getCamera();
		hudCam = game.getHUDCamera();
	}
	
	/**
	 * trata dos inputs do teclado e do rato
	 */
	
	public abstract void handleInput();

	/**
	 * faz o update dos menus
	 */
	
	public abstract void update(float dt);

	/**
	 * renderiza os menus
	 */

	public abstract void render();

	/**
	 *  nao realiza qualquer funcao
	 */
	
	public abstract void dispose();
	
}









