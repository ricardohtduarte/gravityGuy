package entities;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.physics.box2d.World;

/**
 * 
 * classe que representa o inimigo do player 
 * este segue o jogador pelo mapa e deriva da classe player 
 *
 */
public class Enemy extends Player{
	private Queue<Float> movePos = new LinkedList<Float>();  // fila que guarda as posições do player que o enemy vai replicar
	
	/**
	 * @return retorna a fila que contem as posicoes onde o player mudou de sentido
	 */
	public Queue<Float> getmovePos(){
		return movePos;
	}
	

	/**
	 * contrutor da classe enemy possui os 
	 * mesmos parametros da classe da qual deriva 
	 * @param world mundo onde o enemy sera desenhado 
	 * @param x  coordenada x do enemy no mundo
	 * @param y  coordenada y do enemy no mundo
	 * @param texDown textura usada quando o enemy esta no chao
	 * @param texUp textura usada quando o enemy esta no tecto
	 * @param speed velocidade do enemy que é igual à do player 
	 */
	public Enemy(World world, float x, float y, String texDown, String texUp, float speed){
        super(world,x,y,texDown,texUp,speed);
	}
}
