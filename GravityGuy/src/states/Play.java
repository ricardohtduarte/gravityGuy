package states;



import handlers.input.MyInput;
import handlers.manage.GameStateManager;
import handlers.manage.MyContactListener;
import handlers.visual.Background;
import handlers.visual.Map;
import main.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import entities.Enemy;
import entities.Player;


/**
 * 
 * classe que representa o estado onde 
 * o utilizador efectivamente joga
 *
 */
public class Play extends GameState {
	
	private World world;
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera b2dCam;

	
	
	private MyContactListener contactListener;
	private Map map;
	private int actualLevel;
	
	
	/*private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer tmr;
	private float tileSize;*/
	
	BodyDef bdef = new BodyDef();
	FixtureDef fdef = new FixtureDef();
	
	private Player player1;
	private Player player2;
	private Enemy enemy;
	
	private float playerSpeed;
	
	
	private Background background;
	
	
	private float cameraMovement = 1;
	private float cameraLimit = 6f;  
	//private float cameraIncrease = 0.083f;
	private float cameraIncrease;
	
	//private Queue<Float> movePos = new LinkedList<Float>();

	private boolean drawBoxes = false;  //verdadeiro para ver as caixas do box2d
	
	/**
	 * contrutor da classe play
	 * @param gsm gestor dos estados do jogo
	 * @param gameMode modo do jogo (single ou multiplayer)
	 * @param level nivel atual do jogo
	 * @param speed velociade do player e do enemy
	 */
	public Play(GameStateManager gsm, int gameMode, int level, float speed) {   //gameOption: 0 para single Player e 1 para multiplayer
		
		super(gsm);
		
	
		this.actualLevel = level;
		this.playerSpeed = speed;
		cameraIncrease = playerSpeed / Game.CIC;
		
		
		world = new World(new Vector2(0, -10), true);  //-9.81f
		
		
		//world.setContactListener(contact2);
		
		b2dr = new Box2DDebugRenderer();
		
		map = new Map(world , level);
		
		
		
		// cria players
		
		player1 = new Player(world, 160 / Game.PPM, 140 / Game.PPM, "guyDown", "guyUp", playerSpeed);
		
		if (gameMode == 0)
		{
			enemy = new Enemy(world, -20 / Game.PPM, 140 / Game.PPM, "enemyDown", "enemyUp", playerSpeed);
			contactListener = new MyContactListener(player1, enemy);
			
		}
		else {
			player2 = new Player(world, 160 / Game.PPM, 260 / Game.PPM, "guyDown2", "guyUp2", playerSpeed);
			contactListener = new MyContactListener(player1, player2);
		}
		
		world.setContactListener(contactListener);
	
        createBackGround(actualLevel);
		
		// set up box2d cam
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Game.V_WIDTH / Game.PPM, Game.V_HEIGHT / Game.PPM);
	}


	/**
	 * funcao que cria o fundo para os diferentes niveis
	 * @param level nivel em que o jogo se encontra
	 */
	public void createBackGround(int level){
		Texture bgs;
		TextureRegion sky;
		switch(level){
		case 1:
			bgs = Game.textures.getTexture("skyline");
			 sky = new TextureRegion(bgs, 0, 0, 1000, 600);
			background = new Background(sky, cam, 0.2f);
			break;
		case 2:
		    bgs = Game.textures.getTexture("skyline2");
			 sky = new TextureRegion(bgs, 0, 0, 1000, 600);
			background = new Background(sky, cam, 0.2f);
			break;
		case 3:
			bgs = Game.textures.getTexture("skyline3");
			 sky = new TextureRegion(bgs, 0, 0, 1000, 600);
			background = new Background(sky, cam, 0.2f);
			break;
		}
		
	
	}

	/**
	 *  funcao que verifica se o jogador perdeu ou, no caso 
	 *  do multplayer, qual dos jogadores perdeu, muda o estado 
	 *  do GameStateManager consoante as anteriores decisoes
	 */
	private void checkPlayerLost(){
		if (player1.getBody().getPosition().x < cam.position.x / Game.PPM - cameraLimit
				|| player1.getBody().getPosition().y < cam.position.y / Game.PPM - (cameraLimit + 4)
				|| player1.getBody().getPosition().y > cam.position.y / Game.PPM + (cameraLimit + 4)
				|| (enemy != null && player1.getBody().getPosition().x <= enemy.getBody().getPosition().x +0.2)){
			gsm.setPlayer1Wins(false);
			gsm.setState(GameStateManager.PLAYend);
			//gsm.pushState(GameStateManager.PLAYend);
		}

			
		if (player2 != null){
			if (player2.getBody().getPosition().x < cam.position.x / Game.PPM - cameraLimit
					|| player2.getBody().getPosition().y < cam.position.y / Game.PPM - (cameraLimit+4)
					|| player2.getBody().getPosition().y > cam.position.y / Game.PPM + (cameraLimit+4)){
				gsm.setPlayer1Wins(true);
				gsm.setState(GameStateManager.PLAYend);
			}
		}
	}
/**
 *  verifica se o jogador chegou ao final do mapa
 */
	private void checkMapFinale(){
		if (player1.getBody().getPosition().x > map.getThunder().getBody().getPosition().x + 1){
			gsm.setPlayer1Wins(true);
			gsm.setState(GameStateManager.PLAYend);
		}
		if (player2 != null){
			if (player2.getBody().getPosition().x > map.getThunder().getBody().getPosition().x + 1){
				gsm.setPlayer1Wins(false);
				gsm.setState(GameStateManager.PLAYend);
			}
		}
	}
	
	/**
	 *  gere os inputs do teclado e do 
	 *  rato despoletando accoes no jogo
	 */
	public void handleInput() {

		// player jump
		if(MyInput.isPressed(MyInput.BUTTON1) || MyInput.isPressed()) { // z
			if (enemy != null) enemy.getmovePos().add(player1.getBody().getPosition().x);
			player1.input();
		}

		// player2 jump
		if (player2 != null){
			if(MyInput.isPressed(MyInput.BUTTON2)) { //m
				player2.input();
			}
		}
		
		if (enemy != null && enemy.getmovePos().peek()!=null){
			if(enemy.getBody().getPosition().x >= enemy.getmovePos().peek()) { 
				enemy.getmovePos().remove();
				enemy.input();
			}
		}
	}
	
	/**
	 * faz o update das principais entidades do jogo(player, player1, enemy, thunder, world)
	 * e verifica se alguns dos jogadores perdeu ou chegou ao final do mapa
	 */
	public void update(float dt) {
		player1.update(dt);
		if (enemy != null) enemy.update(dt);
		if (player2 != null) player2.update(dt);
		map.getThunder().getSprite().update(dt);
		handleInput();
		world.step(dt, 6, 2);
		
		// verifica se o jogador saiu do alcance da camera (perdeu)
		checkMapFinale();
	    checkPlayerLost();
	}
	
	/**
	 *  renderiza as principais entidades do jogo  a funcionar 
	 *  (camaras, fundos, mapas e jogadores)
	 */
	public void render() {
			
		// clear screen
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//cam.position.set(player1.getBody().getPosition().x * Game.PPM + Game.V_WIDTH / 4, Game.V_HEIGHT / 2, 0);  
		cam.position.set(cameraMovement * Game.PPM + Game.V_WIDTH / 4, Game.V_HEIGHT / 2, 0); 
		//System.out.println(cam.position.y / PPM  + "--" + player1.getBody().getPosition().y);
		
		cam.update();
		
		b2dCam.position.set(cameraMovement + Game.V_WIDTH / 4 / Game.PPM, Game.V_HEIGHT / 2 / Game.PPM, 0);
		b2dCam.update();
		
		// draw background
     	sb.setProjectionMatrix(hudCam.combined);
		background.render(sb);
		
		
		// draw tile map
		map.getTmr().setView(cam);
		map.getTmr().render();
		
		
		sb.setProjectionMatrix(cam.combined);
		player1.getSprite().render(sb);
		if (enemy != null) enemy.getSprite().render(sb);
		if (player2 != null) player2.getSprite().render(sb);
		map.getThunder().getSprite().render(sb);
		
		// draw box2d world
		if (drawBoxes)
		   b2dr.render(world, b2dCam.combined);
		
		cameraMovement += cameraIncrease;
		
	}
	
	/**
	 * metodo abstrato da classe, 
	 * nao lhe e atribuida qualquer funcao
	 */
	public void dispose() {
		
	}

	
	
}