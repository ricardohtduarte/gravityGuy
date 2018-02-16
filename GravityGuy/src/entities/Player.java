package entities;

import main.Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;



/**
 *  classe que representa o jogador
 *  que vai percorrer o mapa com o objectivo  
 *  de chegar ao seu fim
 */
public class Player{

	private Sprite sprite;


	private Texture texturaGuy;
	private Body playerBody;

	private int numFootContact;
	private int numHeadContact;
	
	private String texDown;
	private String texUP;

	private float playerSpeed;

	private static final float PPM = 100;
	private static final int INPUT_TIMEOUT = 1000;
	private static final int LARGURA = 10;
	private static final int ALTURA = 16;
	
	private int inputTime = 0;


	/**
	 * contrutor da classe player
	 * @param world mundo onde o player sera desenhado 
	 * @param x  coordenada x do player no mundo
	 * @param y  coordenada y do player no mundo
	 * @param texDown textura usada quando o player esta no chao
	 * @param texUp textura usada quando o player esta no tecto
	 * @param speed velocidade do player
	 */
	public Player(World world, float x, float y, String texDown, String texUp, float speed){

		this.texDown = texDown;
		this.texUP = texUp;
		this.playerSpeed = speed;
		
		//animation = new Animation();
		this.texturaGuy = Game.textures.getTexture(texDown);

		playerBody = createBody(world, x, y);
		sprite = new Sprite(playerBody);

		TextureRegion[] spritesGuyDown = TextureRegion.split(texturaGuy, 48, 41)[0];
		sprite.setAnimation(spritesGuyDown, 1/12f);
		
	}
	
	/**
	 * funcao que efectivamente desenha o player no mundo criando 
	 * um body e respectiva fixtures (fixtures principais e texturas)
	 * @param world mundo onde o player será desenhado
	 * @param x coordenada x do player no mundo
	 * @param y coordenada y do player no mundo
	 * @return retorna o body construido
	 */

	public Body createBody(World world, float x, float y){

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();

		// create player
		bdef.position.set(x, y);
		bdef.type = BodyType.DynamicBody;
		Body body = world.createBody(bdef);

		shape.setAsBox(LARGURA / PPM, ALTURA / PPM);
		fdef.shape = shape;
		fdef.friction = 0;
		body.createFixture(fdef).setUserData("player");

		// create foot sensor
		shape.setAsBox(0.8f*LARGURA/PPM, ALTURA/3/PPM, new Vector2(-0.2f*LARGURA/PPM, -ALTURA/PPM), 0);
		fdef.shape = shape;

		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("foot");

		// create head sensor
		shape.setAsBox(0.8f*LARGURA/PPM, ALTURA/3/PPM, new Vector2(-0.2f*LARGURA/PPM, ALTURA/PPM), 0);
		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("head");

		return body;

	}

	
	/**
	 * função que muda a textura do player 
	 * consoante a sua posicao no mapa, esta funcao 
	 * @param down variável booleana que decide qual das texturas vai ser usada
	 */
	private void setOrientation(boolean down){
		if (down){
			this.texturaGuy = Game.textures.getTexture(texDown);
			TextureRegion[] spritesGuyDown = TextureRegion.split(texturaGuy, 48, 41)[0];
			sprite.setAnimation(spritesGuyDown, 1/12f);
		}
		else {
			this.texturaGuy = Game.textures.getTexture(texUP);
			TextureRegion[] spritesGuyDown = TextureRegion.split(texturaGuy, 48, 41)[0];
			sprite.setAnimation(spritesGuyDown, 1/12f);
		}

	}
	
	/**
	 * 
	 * @return retorna o valor da velocidade do player 
	 */

	public float getPlayerSpeed(){
		return playerSpeed;
	}
	
	/**
	 * 
	 * @return devolve a sprite do player
	 */

	public Sprite getSprite(){
		return sprite;
	}

	/**
	 * 
	 * @return  devolve o body do player
	 */
	public Body getBody(){
		return playerBody;
	}

	/**
	 * funcao que detecta se foi iniciado contacto do player com qualquer outra fixture
	 * @param c instancia de Contact classe que guarda contactos
	 */

	public void beginContact(Contact c) {
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();

		if (fa.getBody() == playerBody && fa.getUserData() != null)
		{
			if(fa.getUserData().equals("foot")) {
				numFootContact++;
			}
			else if(fa.getUserData().equals("head")) {
				numHeadContact++;
			}
		}
		else if (fb.getBody() == playerBody && fb.getUserData() != null)
		{
			if(fb.getUserData().equals("foot")) {
				numFootContact++;
			}
			else if(fb.getUserData().equals("head")) {
				numHeadContact++;
			}
		}
	}
	
	/**
	 * funcao que detecta se foi terminado contacto do player com qualquer outra fixture
	 * @param c instancia de Contact classe que guarda contactos
	 */


	public void endContact(Contact c) {
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();
	
		if (fa.getBody() == playerBody && fa.getUserData() != null)
		{
			if(fa.getUserData().equals("foot")) {
				numFootContact--;
			}
			else if(fa.getUserData().equals("head")) {
				numHeadContact--;
			}
		}
		else if (fb.getBody() == playerBody && fb.getUserData() != null)
		{
			if(fb.getUserData().equals("foot")) {
				numFootContact--;
			}
			else if(fb.getUserData().equals("head")) {
				numHeadContact--;
			}
		}
	}
	
	/**
	 * 
	 * @return retorna verdadeiro se o player estiver no tecto e falso 
	 * na situacao oposta
	 */
	
	private boolean isPlayerOnRoof()
	{
		return numHeadContact != 0;
	}
	
	/**
	 * 
	 * @return retorna verdadeiro se o player estiver no chao e falso 
	 * na situacao oposta
	 */
	

	private boolean isPlayerOnGround()
	{
		return numFootContact != 0;
	}
	
	/**
	 * funcao que atualiza determinadas caracteristicas do player,
	 * a sua velociade, a sua sprite e o inputTime  
	 * @param dt parametro que contém o intervalo de tempo da funcao update
	 */
	
	public void update(float dt)
	{
		getBody().setLinearVelocity(getPlayerSpeed(),getBody().getLinearVelocity().y);
		getSprite().update(dt);
		if (inputTime > 0)
		{
			if (handleInput())
				inputTime = 0;
			else inputTime-=dt;
		}
	}
	
	/**
	 * altera o sentido do player de acordo com as variaveis
	 * derivadas da detecao de colisao
	 * @return retorna verdadeiro se o player mudou de posicao e 
	 * falso na situacao oposta
	 */
	
	private boolean handleInput()
	{
		if(isPlayerOnGround()) {
			this.getBody().setGravityScale(-1);
			this.setOrientation(false);  // textura do jogador em baixo
			return true;
		}
		else if(isPlayerOnRoof()){
			this.getBody().setGravityScale(1);
			this.setOrientation(true);  // textura do jogador em cima
			return true;
		}
		return false;
	}
	/**
	 *  funcao que controla a variavel inputime, esta varival tem a funcao 
	 *  de guardar durante um segundo a tecla premida pelo utilizador 
	 *  o que torna a exepriencia do jogo mais responsivo
	 */
	public void input()
	{
		if (!handleInput()) this.inputTime = INPUT_TIMEOUT;
	}
}
