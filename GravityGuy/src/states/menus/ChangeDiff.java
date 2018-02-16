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
 * classe que representa o menu responsavel pela 
 * gestao da dificuldade do jogo 
 *
 */
public class ChangeDiff extends GameState{
	
	
    private GameButton easy, medium, hard;
	
    /**
     * contrutor da classe ChangeDiff
     * @param gsm gestor dos estados do jogo
     */
	public ChangeDiff(GameStateManager gsm) {
		super(gsm);
		
		Texture tex = Game.textures.getTexture("menu");
		TextureRegion men = new TextureRegion(tex, 0, -300, 2000, 900);
		bg = new Background(men, cam, 1f);

		bg.setVector(-10, 0);

		tex = Game.textures.getTexture("easy");
		easy = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 590, 260, cam);

		tex = Game.textures.getTexture("medium");
		medium = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 590, 180, cam);
		
		tex = Game.textures.getTexture("hard");
		hard = new GameButton(tex,new TextureRegion(tex, 0, 0, 400, 100), 590, 100, cam);
			
	
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
		if(easy.isClicked()) {
			gsm.setPlayerSpeed(3.5f);
			gsm.setState(GameStateManager.MAINmenu);
		}
		if(medium.isClicked()) {
			gsm.setPlayerSpeed(4.5f);
			gsm.setState(GameStateManager.MAINmenu);
		}
		if(hard.isClicked()) {
			gsm.setPlayerSpeed(7f);
			gsm.setState(GameStateManager.MAINmenu);
		}
	}

	/**
	 *  funcao que faz o update dos botoes e do mundo 
	 */
	@Override
	public void update(float dt) {
		handleInput();

		world.step(dt / 5, 8, 3);

		easy.update(dt);
		medium.update(dt);
		hard.update(dt);
		
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
		easy.render(sb);
		medium.render(sb);
		hard.render(sb);
		

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
