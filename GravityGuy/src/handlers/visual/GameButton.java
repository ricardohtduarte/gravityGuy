package handlers.visual;

import handlers.input.MyInput;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

/**
 *  classe que cria um botao baseado numa imagem
 */
public class GameButton {
	
	// center at x, y
	private float x;
	private float y;
	private float width;
	private float height;
	
	private TextureRegion reg;
	
	Vector3 vec;
	private OrthographicCamera cam;
	
	private boolean clicked;
	

	/**
	 * contrutor do GameButton
	 * @param tex textura do botao
	 * @param reg regiao da textura onde estara o botao
	 * @param x coordenada x do botao
	 * @param y coordenada y do botao
	 * @param cam camara onde sera inserido o botao
	 */
	public GameButton(Texture tex, TextureRegion reg, float x, float y, OrthographicCamera cam) {
		
		this.reg = reg;
		this.x = x;
		this.y = y;
		this.cam = cam;
		
		width = reg.getRegionWidth();
		height = reg.getRegionHeight();
		vec = new Vector3();
		
	}
	
	/**
	 * 
	 * @return retorna verdadeiro de o botao for clicado e falso 
	 * no caso oposto
	 */
	public boolean isClicked() { return clicked; }

	/**
	 * faz o update do botao
	 * @param dt intervalo de tempo do update do botao
	 */
	public void update(float dt) {
		
		vec.set(MyInput.x, MyInput.y, 0);
		cam.unproject(vec);
		
		if(MyInput.isPressed() &&
			vec.x > x - width / 2 && vec.x < x + width / 2 &&
			vec.y > y - height / 2 && vec.y < y + height / 2) {
			clicked = true;
		}
		else {
			clicked = false;
		}
		
	}
	
	/**
	 * renderiza a imagem do background atraves de um spritebatch
	 * @param sb spritebatch que vai renderizar imagem do botao
	 */
	public void render(SpriteBatch sb) {
		
		sb.begin();
		
		sb.draw(reg, x - width / 2, y - height / 2);
		
		sb.end();
		
	}
	
	
}
