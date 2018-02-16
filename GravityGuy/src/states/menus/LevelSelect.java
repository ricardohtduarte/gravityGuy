package states.menus;

import states.GameState;
import handlers.manage.GameStateManager;
import handlers.visual.Background;
import handlers.visual.GameButton;
import main.Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * 
 * classe que representa o menu que gere 
 * o nivel escolhido pelo utilizador
 *
 */
public class LevelSelect extends  GameState{


	private GameButton level1, level2, level3;
	private GameButton playerpress, player1press, player2press;
	private GameButton selectTheLevel;
	private GameButton mainMenuB;
	
	private int gameMode;
	

	/**
	 * contrutor da classe LevelSelect
	 * @param gsm gestor dos estados do jogo
	 * @param gameMode modo de jogo (single ou multiplayer)
	 */
	public LevelSelect(GameStateManager gsm, int gameMode) {
		super(gsm);

		this.gameMode = gameMode;
		
		Texture tex = Game.textures.getTexture("menu");
		TextureRegion men = new TextureRegion(tex, 0, -300, 2000, 900);
		bg = new Background(men, cam, 1f);

		bg.setVector(-10, 0);

		tex = Game.textures.getTexture("B1");
		level1 = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 500, 175, cam);

		tex = Game.textures.getTexture("B2");
		level2 = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 620, 175, cam);
		
		tex = Game.textures.getTexture("B3");
		level3 = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 740, 175, cam);
		
		tex = Game.textures.getTexture("playerp");
		playerpress = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 200, 80, cam);
		
		tex = Game.textures.getTexture("player1p");
		player1press = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 200, 80, cam);
		
		tex = Game.textures.getTexture("player2p");
		player2press = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 900, 70, cam);
		
		tex = Game.textures.getTexture("select");
		selectTheLevel = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 475, 275, cam);
		

		tex = Game.textures.getTexture("mainB");
		mainMenuB = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 530, 70, cam);
		
	
	
		cam.setToOrtho(false, Game.V_WIDTH, Game.V_HEIGHT);

		world = new World(new Vector2(0, -9.8f * 5), true);
		//world = new World(new Vector2(0, 0), true);
		b2dRenderer = new Box2DDebugRenderer();

	}

	/**
	 *  funcao que verifica se o utilizador clicou em algum
	 *  dos botoes 
	 */
	@Override
	public void handleInput() {
		// mouse/touch input
		if(mainMenuB.isClicked()){
			gsm.setState(GameStateManager.MAINmenu);
		}
		if(level1.isClicked()) {
			gsm.setLevel(1);
			gsm.setState(gameMode);
		}
		if(level2.isClicked()) {
			gsm.setLevel(2);
			gsm.setState(gameMode);
		}
		if(level3.isClicked()) {
			gsm.setLevel(3);
			gsm.setState(gameMode);
		}
	}

	/**
	 *  funcao que faz o update dos botoes e do mundo 
	 */
	@Override
	public void update(float dt) {
		handleInput();

		world.step(dt / 5, 8, 3);

		level1.update(dt);
		level2.update(dt);
		level3.update(dt);
		mainMenuB.update(dt);
	}

	/**
	 *  funcao que renderiza os botoes e fundo do menu
	 */
	@Override
	public void render() {

		sb.setProjectionMatrix(cam.combined);

		// draw background
		bg.render(sb);

		// draw button
		level1.render(sb);
		level2.render(sb);
		level3.render(sb);
		
		if (gameMode == 1){
			player1press.render(sb);
			player2press.render(sb);
		}
		else 
			playerpress.render(sb);
		
		
	    selectTheLevel.render(sb);
		
		mainMenuB.render(sb);

		// debug draw box2d
		if(debug) {
			cam.setToOrtho(false, Game.V_WIDTH / 100, Game.V_HEIGHT / 100);
			b2dRenderer.render(world, cam.combined);
			cam.setToOrtho(false, Game.V_WIDTH, Game.V_HEIGHT);
		}

	}
	/**
	 *  classe abstrata,
	 *  nao lhe e atribuida qualquer funcao
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
