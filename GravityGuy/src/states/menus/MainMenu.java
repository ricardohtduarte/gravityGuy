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
 * menu que representa o menu principal do jogo
 * o utilizador pode decidir de quer jogar singleplayer, 
 * multiplayer, pode escolher a dificuldade do jogo
 * e pode sair do programa
 *
 */
public class MainMenu extends  GameState{
	
	private GameButton singlePlayer;
	private GameButton multiPlayer;
	private GameButton difficulty;
	private GameButton exitButton;
	
	/**
	 * contrutor da classe MainMenu
	 * @param gsm gestor dos estados do jogo 
	 */
	public MainMenu(GameStateManager gsm) {
		super(gsm);
		
		Texture tex = Game.textures.getTexture("menu");
		TextureRegion men = new TextureRegion(tex, 0, -300, 2000, 900);
		bg = new Background(men, cam, 1f);
		
		bg.setVector(-10, 0);
		
		tex = Game.textures.getTexture("single");
		singlePlayer = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 550, 260, cam);
		
		tex = Game.textures.getTexture("multi");
		multiPlayer = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 550, 180, cam);
		
		tex = Game.textures.getTexture("difficulty");
		difficulty = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 590, 100, cam);
		
		tex = Game.textures.getTexture("exit");
		exitButton = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 540, 50, cam);
		
	
		
		
		
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
		if(singlePlayer.isClicked()) {
			gsm.setGameMode(0);
			gsm.setState(GameStateManager.LEVELselect);
		}
		if (multiPlayer.isClicked()){
			gsm.setGameMode(1);
			gsm.setState(GameStateManager.LEVELselect);
		}
		if (difficulty.isClicked()){
			gsm.setGameMode(1);
			gsm.setState(GameStateManager.CHANGEdif);
		}
		if (exitButton.isClicked()){
			System.exit(0);
		}
	}
	
	/**
	 *  funcao que faz o update dos botoes e do mundo 
	 */
	@Override
	public void update(float dt) {
		handleInput();

		world.step(dt / 5, 8, 3);

		singlePlayer.update(dt);
		multiPlayer.update(dt);
		difficulty.update(dt);
		exitButton.update(dt);

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
		singlePlayer.render(sb);
		multiPlayer.render(sb);
		difficulty.render(sb);
		exitButton.render(sb);
		
		
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
