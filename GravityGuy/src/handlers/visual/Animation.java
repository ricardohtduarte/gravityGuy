package handlers.visual;

import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * 
 * classe responsavel pelas animacoes do jogo
 *
 */
public class Animation {
	
	private TextureRegion[] frames;  // cada pedaço da imagem da sprite
	private float time;
	private float delay;
	private int currentFrame;
	private int timesPlayed;
	
	/**
	 * contrutor da classe Animation sem parametros	
	 */
	public Animation() {}
	
	/**
	 * contrutor da classe Animation
	 * @param frames regiao da imagem que servira contem os frames da animcao
	 */
	public Animation(TextureRegion[] frames) {
		this(frames, 1 / 2f);
	}
	/**
	 * contrutor da classe Animation
	 * @param frames regiao da imagem que servira contem os frames da animcao
	 * @param delay intervalo de tempo entre cada frame
	 */
	public Animation(TextureRegion[] frames, float delay) {
		setFrames(frames, delay);
	}
	
	/**
	 * altera os frames da animacao
	 * @param frames novo vetor de frames a utilizar 
	 * @param delay novo intervalo de tempo a utilizar
	 */
	public void setFrames(TextureRegion[] frames, float delay) {
		this.frames = frames;
		this.delay = delay;
		time = 0;
		currentFrame = 0;
		timesPlayed = 0;
	}
	
	/**
	 * atualiza a animcao
	 * @param dt intervalo de tempo do update
	 */
	public void update(float dt) {
		if(delay <= 0) return;
		time += dt;
		while(time >= delay) {
			step();
		}
	}
	/**
	 * atualiza os frames da animacao
	 */
	private void step() {
		time -= delay;
		currentFrame++;
		if(currentFrame == frames.length) {
			currentFrame = 0;
			timesPlayed++;
		}
	}
	/**
	 * 
	 * @return retorna o frame atual
	 */
	public TextureRegion getFrame() { return frames[currentFrame]; }

	
}
