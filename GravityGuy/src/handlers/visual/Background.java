package handlers.visual;

import main.Game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * 
 * classe responsavel pelo desenho dos fundos presentes no jogo
 *
 */
public class Background {
	private TextureRegion image;
	private OrthographicCamera gameCam;
	private float scale;
	
	private float x;
	private float y;
	private int numDrawX;
	private int numDrawY;
	
	private float dx;
	private float dy;
	
	/**
	 * 	construtor do Bachground
	 * @param image imagem que servira de fundo
	 * @param gameCam camera onde sera colocada a imagem
	 * @param scale escala da imagem
	 */
	public Background(TextureRegion image, OrthographicCamera gameCam, float scale) {
		this.image = image;
		this.gameCam = gameCam;
		this.scale = scale;
		numDrawX = Game.V_WIDTH / image.getRegionWidth() + 1;
		numDrawY = Game.V_HEIGHT / image.getRegionHeight() + 1;
	}
	
	/**
	 * altera o vetor da varicao da posicao do fundo
	 * @param dx novo valor de varicao de x 
	 * @param dy novo valor de varicao de y
	 */
	public void setVector(float dx, float dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
	 * faz o update do fundo
	 * @param dt intervalo de tempo do update do Background
	 */
	public void update(float dt) {
		x += (dx * scale) * dt;
		y += (dy * scale) * dt;
	}

	/**
	 * renderiza a imagem do background ateves de um spritebatch
	 * @param sb spritebatch que vai renderizar imagem de fundo
	 */
	public void render(SpriteBatch sb) {
		
		float x = ((this.x + gameCam.viewportWidth  /2 - gameCam.position.x) * scale) % image.getRegionWidth();
		float y = ((this.y + gameCam.viewportHeight /2 - gameCam.position.y) * scale) % image.getRegionHeight();
		
		sb.begin();
		
		int colOffset = x > 0 ? -1 : 0;
		int rowOffset = y > 0 ? -1 : 0;
		
		for(int row = 0; row < numDrawY; row++) {
			for(int col = 0; col < numDrawX; col++) {
				sb.draw(image, x + (col + colOffset) * image.getRegionWidth(), y + (rowOffset + row) * image.getRegionHeight());
			}
		}
		
		sb.end();
		
	}
}
