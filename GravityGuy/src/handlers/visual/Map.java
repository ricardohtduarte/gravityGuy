package handlers.visual;

import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;

import entities.Thunder;


/**
 * 
 * classe que representa o builder do mapa
 *
 */
public class Map {
	private MapBuilder mapBuilder;
	
	/**
	 * contrutor da classe Map
	 * @param world mundo onde vai ser desenhado o mapa
	 * @param level nivel atual do jogo
	 */
	public Map(World world, int level){
		mapBuilder = new MapBuilder(world, level);
	}
	/**
	 * 
	 * @return retorna o trovao presente no fim do mapa
	 */
	public Thunder getThunder(){
		return mapBuilder.getThunder();
	}
	/**
	 * 
	 * @return retorna o mapa
	 */
	public OrthogonalTiledMapRenderer getTmr(){
		return mapBuilder.getTmr();
	}
}
