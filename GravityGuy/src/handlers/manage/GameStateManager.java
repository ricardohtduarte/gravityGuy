package handlers.manage;

import java.util.Stack;

import main.Game;
import states.GameState;
import states.Play;
import states.menus.ChangeDiff;
import states.menus.LevelSelect;
import states.menus.MainMenu;
import states.menus.PlayEnd;


/**
 * 
 * classe que gere os estados do jogo 
 *
 */
public class GameStateManager {
	
	private Game game;
	
	private  Stack<GameState> gameStates;
	
	public static final int PLAYsingle = 0;
	public static final int PLAYmulti = 1;
	public static final int MAINmenu = 2;
	public static final int LEVELselect = 3;
	public static final int PLAYend = 4;
	public static final int CHANGEdif = 5;
	
	private int gameMode;  // 0 single, 1 multi
	private int level = 1;  // default value 
	private boolean player1wins;
	private float playerSpeed = 4.5f;
	
	/**
	 * construtor da classe GameStateManager
	 * @param game jogo atual 
	 */
	public GameStateManager(Game game) {
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(MAINmenu);
	}
	
	/**
	 * 
	 * @return retorna o game que o GameStateManager está a manipular
	 */
	public Game game() { return game; }
	
	/**
	 * faz o update o estado que está no todo da pilha
	 * @param dt
	 */
	public void update(float dt) {
		gameStates.peek().update(dt);
	}
	
	/**
	 * constroi o estado que está no todo da pilha
	 * @param dt
	 */
	public void render() {
		gameStates.peek().render();
	}
	
	
	/**
	 * retorna o estado atual do GameStateManager
	 * @param state variavel que define um estado do jogo especifico
	 * @return retorna o estado que o parametro define
	 */
	private GameState getState(int state) {
		if(state == PLAYsingle) return new Play(this, 0, level, playerSpeed);
		if (state == PLAYmulti) return new Play(this, 1, level, playerSpeed);
		if(state == MAINmenu) return new MainMenu(this);
		if (state == LEVELselect) return new LevelSelect(this, gameMode);
		if (state == PLAYend) return new PlayEnd(this, player1wins, level, gameMode);
		if (state == CHANGEdif) return new ChangeDiff(this);
		return null;
	}
	
	/**
	 * altera o estado atual do jogo
	 * @param state novo estado atual
	 */
	public void setState(int state) {
		popState();
		pushState(state);
	}
	
	/**
	 * coloca o estado passad como parametro no topo da pilha
	 * @param state estado que sera inserido no topo da pilha
	 */
	public void pushState(int state) {
		gameStates.push(getState(state));
	}
	
	/**
	 *  retira o estado atual do topo da pilha
	 */
	public void popState() {
		GameState g = gameStates.pop();
		g.dispose();
	}
	
	/**
	 * altera o nivel atual do jogo
	 * @param level novo nivel do jogo
	 */
	public void setLevel(int level){
		this.level = level;
	}
	/**
	 * altera o modo de jogo atual(singleplayer ou multiplayer)
	 * @param option novo valor do gameMode (0 para singleplayer e 1 para multiplayer)
	 */
	public void setGameMode(int option){
		this.gameMode = option;
	}
	/**
	 * altera o variavel Player1Wins que indica se o 
	 * jogador 1 ganhou ou perdeu
	 * @param option novo valor de Player1Wins, verdadeiro se o 
	 * player 1 ganhar falso se o player 2 ganhar 
	 */
	public void setPlayer1Wins(boolean option){
		this.player1wins = option;
	}
	/**
	 * altera a velocidade do player para alternar entre 
	 * os tres niveis de dificuldade
	 * @param v novo valor para a velocidade do player
	 */
	public void setPlayerSpeed(float v){
		this.playerSpeed = v;
	}
}















