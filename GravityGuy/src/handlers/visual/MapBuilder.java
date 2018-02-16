package handlers.visual;

import main.Game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import entities.Thunder;

/**
 * 
 * classe responsavel pela criacao do mapa
 *
 */
public class MapBuilder {
	
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer tmr;
	private Thunder thunder;
	
	BodyDef bdef = new BodyDef();
	FixtureDef fdef = new FixtureDef();
	
	/**
	 * contrutor da classe MapBuilder
	 * @param world mundo onde sera criado o mapa
	 * @param level nivel do jogo (o mapa e diferente em cada nivel
	 */
	public MapBuilder(World world, int level){
		
		tiledMap = new TmxMapLoader().load("res/maps/level" + level + ".tmx");
		tmr = new OrthogonalTiledMapRenderer(tiledMap);

		thunder = new Thunder(world, 7700 / Game.PPM, 250 / Game.PPM);
		
		TiledMapTileLayer layer ;
		layer = (TiledMapTileLayer) tiledMap.getLayers().get("block2");
		createBlocks(layer, world);
		layer = (TiledMapTileLayer) tiledMap.getLayers().get("block2");
		createBlocks(layer, world);
		layer = (TiledMapTileLayer) tiledMap.getLayers().get("block3");
		createBlocks(layer, world);		
	}

	/**
	 * 
	 * @return retorna o trovao que (entidade que indica o fim do mapa
	 */
	public Thunder getThunder(){
		return thunder;
	}
	
	/**
	 * funcao que cria os body's em torno dos blocos do mapa carregado
	 * @param layer tipo de bloco a ser criado
	 * @param world mundo onde os body's dos blocos serao criados 
	 */
	
	private void createBlocks(TiledMapTileLayer layer, World world){
		
		float tileSize = layer.getTileWidth();
		
		for (int row = 0; row < layer.getHeight(); row++){
			for (int col = 0; col < layer.getWidth(); col++){

				Cell cell = layer.getCell(col, row);

				if (cell == null)  continue;
				if (cell.getTile() == null) continue;

				bdef.type = BodyType.StaticBody;
				/*bdef.position.set(
						(col + 0.5f) * tileSize /Game.PPM,
						(row + 0.5f) * tileSize / Game.PPM
						);*/
				bdef.position.set(
						(col) * tileSize /Game.PPM,
						(row + 0.5f) * tileSize / Game.PPM
						);
				ChainShape cs = new ChainShape();
				Vector2[] v = new Vector2[4];


				v[0] = new Vector2(
						tileSize /2 / Game.PPM, -tileSize / 2/ Game.PPM);
				v[1] = new Vector2(
						-tileSize /2 / Game.PPM, -tileSize / 2/ Game.PPM);
				v[2] = new Vector2(
						-tileSize /2 / Game.PPM, tileSize / 2/ Game.PPM);
				v[3] = new Vector2(
						tileSize /2 / Game.PPM, tileSize / 2/ Game.PPM);

				cs.createChain(v);
				fdef.friction = 0;
				fdef.shape = cs;
				fdef.isSensor = false;
				world.createBody(bdef).createFixture(fdef);
				cs.dispose();
			}
		}
	}

	/**
	 * 
	 * @return retorna o mapa
	 */
	public OrthogonalTiledMapRenderer getTmr(){
		return tmr;
	}

}
