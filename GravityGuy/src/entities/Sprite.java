package entities;

import handlers.visual.Animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;


/**
 * 
 * classe que vai gerir a sprite do player, do enemy e do thunder 
 * esta classe é responsável pela criação da animação das entidades anterirores 
 *
 */
public class Sprite {
	protected Body body;
	protected float width;
    protected float height;
    protected Animation animation;
    
    private static final float PPM = 100;
    
    // criar animação 
    
    /**
     * contrutor da classe Sprite 
     * @param body que e criado na classe player 
     */
   public Sprite(Body body){
    	this.body = body;
    	animation = new Animation();
    }
    
   
   /**
    * funcao que gere a animacao da sprite 
    * @param reg regiao da imagem que vai ser usada para a animacao
    * @param delay intervalo de tempo entre os cortes da imagem
    */
    public void setAnimation(TextureRegion[] reg, float delay){
    	animation.setFrames(reg, delay);
    	width = reg[0].getRegionWidth();
    	height = reg[0].getRegionHeight();
    }
    
    /**
     * faz o update da animacao
     * @param dt intervalo de tempo passado ao update da funcao update
     */
    public void update(float dt){
    	animation.update(dt);
    }
    
    /**
     * renderiza a animacao da sprite 
     * @param sb spritebatch a ser usado
     */
    public void render(SpriteBatch sb){
    	sb.begin();
    	sb.draw(animation.getFrame(), body.getPosition().x * PPM - width /2, 
    			                      body.getPosition().y * PPM - height /2);  // necessaria conversao da escala para trabalhar com frame
       	sb.end();
    }

    /**
     * 
     * @return retorna o vector posicao da sprite
     */
    public Vector2 getPosition(){
    	return body.getPosition();
    }
    
    /**
     * 
     * @return retorna o body da sprite
     */
    public Body getBody(){
    	return body;
    }

    /**
     * 
     * @return retorna a largura da sprite
     */
	public float getWidth() {
		return width;
	}

	/**
	 * altera a largura da sprite
	 * @param novo valor da largura da sprite a ser utilizado
	 */
	public void setWidth(float width) {
		this.width = width;
	}

     /**
     * 
     * @return retorna o comprimento da sprite
     */
	public float getHeight() {
		return height;
	}

	/**
	 * altera o comprimento da sprite
	 * @param novo valor do comprimento da sprite a ser utilizado
	 */
	public void setHeight(float height) {
		this.height = height;
	}
    
	/**
	 * 
	 * @return retorna a animacao da sprite
	 */
	public Animation getAnimation(){
		return animation;
	}
    
    
}
