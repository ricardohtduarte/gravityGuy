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
 * classe que representa o menu que e mostrado 
 * quando o estado Play acaba, o jogador pode 
 * decidir por tentar o nivel de novo ou ir para o menu principal
 *
 */
public class PlayEnd extends GameState{

	private GameButton retry;
	private GameButton mainMenuB;
	
	private GameButton win1;
	private GameButton win2;

	private GameButton cleared;
	private GameButton lost;

	private int gameMode;

	private boolean player1Wins = false;


	/**
	 * contrutor da classe MainMenu
	 * @param gsm gestor de estados do jogo
	 * @param player1wins variavel que contem a informacao sobre qual dos jogadores ganhou
	 * @param level nivel atual do jogo
	 * @param gameMode modo atual do jogo( 0 singleplayer, 1 multiplayer)
	 */
	public PlayEnd(GameStateManager gsm, boolean player1wins, int level, int gameMode) {
		super(gsm);

		//this.gameMode = gameMode;
		this.player1Wins = player1wins;
		this.gameMode = gameMode;
		
		Texture tex = Game.textures.getTexture("menu");
		TextureRegion men = new TextureRegion(tex, 0, -300, 2000, 900);
		bg = new Background(men, cam, 1f);

		bg.setVector(-10, 0);

		
		
		tex = Game.textures.getTexture("mainB");
		mainMenuB = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 530, 60, cam);
		
		tex = Game.textures.getTexture("retry");
		retry = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 535, 140, cam);
		
		tex = Game.textures.getTexture("win1");
		win1 = new GameButton(tex,new TextureRegion(tex, 0, 0, 500, 100), 505, 250, cam);

		tex = Game.textures.getTexture("win2");
		win2 = new GameButton(tex,new TextureRegion(tex, 0, 0, 500, 100), 505, 250, cam);
		
		tex = Game.textures.getTexture("cleared");
		cleared = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 470, 265, cam);
		
		tex = Game.textures.getTexture("lost");
		lost = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 160), 535, 220, cam);
		

		

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
		if(retry.isClicked()){
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

		retry.update(dt);
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
		retry.render(sb);
		mainMenuB.render(sb);
		
		if (gameMode == 1){
			if (player1Wins)
				win1.render(sb);
			else 
				win2.render(sb);
		}
		if (gameMode == 0){
			if (player1Wins)
				cleared.render(sb);
			else 
				lost.render(sb);
		}
		
		

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

