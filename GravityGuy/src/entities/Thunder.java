
package entities;

import main.Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;



/**
 * classe thunder que representa um trovao
 * que indica o final do mapa
 */
public class Thunder{

	private Sprite sprite;
	private Texture texThunder;
	private Body thunderBody;

	private static final float PPM = 100;
	private static final int LARGURA = 30;
	private static final int ALTURA = 100;
	
	/**
	 * construtor da classe thunder
	 * @param world mundo onde o thunder vai ser desenhado
	 * @param x coordenada x do thunder
	 * @param y coordenada y do thunder
	 */
	public Thunder(World world, float x, float y){

		
		//animation = new Animation();
		this.texThunder = Game.textures.getTexture("thunder");
		thunderBody = createBody(world, x, y);
		sprite = new Sprite(thunderBody);

		TextureRegion[] spritesThunder = TextureRegion.split(texThunder, 40, 230)[0];
		sprite.setAnimation(spritesThunder, 1/12f);
	}

	/**
	 * cria e desenha o thunder no mundo
	 * @param world mundo onde o thunder vai ser desenhado
	 * @param x coordenada x do thunder
	 * @param y coordenada y do thunder
	 * @return retorna o body do thunder construido
	 */
	public Body createBody(World world, float x, float y){

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();

		// create player
		bdef.position.set(x, y);
		bdef.type = BodyType.StaticBody;
		Body body = world.createBody(bdef);

		shape.setAsBox(LARGURA / PPM, ALTURA / PPM);
		fdef.shape = shape;
		fdef.friction = 0;
		fdef.filter.maskBits = 8;
		body.createFixture(fdef).setUserData("thunder");

		return body;

	}

	/**
	 * 
	 * @return retorna a sprite do thunder
	 */
	public Sprite getSprite(){
		return sprite;
	}

	/**
	 * 
	 * @return retorna o body do thunder 
	 */
	public Body getBody(){
		return thunderBody;
	}	
}