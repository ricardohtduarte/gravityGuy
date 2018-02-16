package handlers.manage;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import entities.Enemy;
import entities.Player;


/**
 * 
 * classe que gere as colisoes entre fixtures
 *
 */
public class MyContactListener implements ContactListener {

	
	private Player player1, player2;
	private Enemy enemy;
	
	/**
	 * contrutor da classe MyContactListener destinada ao singleplayer
	 * @param player classe cujas colisoes vao ser verificadas
	 * @param enemy classe cujas colisoes vao ser verificadas
	 */
	
	public MyContactListener(Player player, Enemy enemy)  //construtor for singleplayer
	{
		this.player1 = player;
		this.enemy = enemy;
	}
	
	/**
	 * contrutor da classe MyContactListener destinada ao multiplayer
	 * @param player1 classe cujas colisoes vao ser verificadas
	 * @param player2 classe cujas colisoes vao ser verificadas
	 */
	
	public MyContactListener(Player player1, Player player2)  //construtor for multiplayer 
	{
		this.player1 = player1;
		this.player2 = player2;
	}
	
	
	/**
	 * funcao chamada quando duas fixtures colidem,
	 * esta verifica qual o body dessas fixtures e 
	 * chamam o beginContact das classes respectivas
	 */
	public void beginContact(Contact c) {
		Body bodyA = c.getFixtureA().getBody();
		Body bodyB = c.getFixtureB().getBody();
		
		if (bodyA == player1.getBody() || bodyB == player1.getBody()) 
			player1.beginContact(c);
		if (player2 != null){
			if (bodyA == player2.getBody() || bodyB == player2.getBody())
				player2.beginContact(c);
		}
		if (enemy != null){
			if (bodyA == enemy.getBody() || bodyB == enemy.getBody())
				enemy.beginContact(c);
		}
	}
	
	/**
	 * funcao chamada quando duas fixtures deixam de colidir,
	 * esta verifica qual o body dessas fixtures e 
	 * chamam o endContact das classes respectivas
	 */
	public void endContact(Contact c) {
		Body bodyA = c.getFixtureA().getBody();
		Body bodyB = c.getFixtureB().getBody();
		
		if (bodyA == player1.getBody() || bodyB == player1.getBody()) 
			player1.endContact(c);
		if (player2 != null){
			if (bodyA == player2.getBody() || bodyB == player2.getBody())
				player2.endContact(c);
		}
		if (enemy != null){
			if (bodyA == enemy.getBody() || bodyB == enemy.getBody())
				enemy.endContact(c);
		}
	}
	
	
	/**
	 * metodo abstrato que deve ser implementado
	 * nao tem qualquer funcao
	 */
	public void preSolve(Contact c, Manifold m) {}
	
	/**
	 * metodo abstrato que deve ser implementado
	 * nao tem qualquer funcao
	 */
	public void postSolve(Contact c, ContactImpulse ci) {}
	
}
