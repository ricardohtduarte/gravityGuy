package handlers.input;

/**
 * 
 * classe responsavel pela manipulacao 
 * inputs enviados ao programa
 *
 */

public class MyInput {
	
	public static int x;
	public static int y;
	public static boolean down;
	public static boolean pdown;
	
	public static boolean[] keys;
	public static boolean[] pkeys;
	
	
	public static final int NUM_KEYS = 2;
	public static final int BUTTON1 = 0;   // z 
	public static final int BUTTON2 = 1;   // m
	
	
	static {
		keys = new boolean[NUM_KEYS];
		pkeys = new boolean[NUM_KEYS];
	}
	
	/**
	 *  guarda as teclas e os cliques 
	 *  do rato 
	 */
	
	public static void update() {
		pdown = down;
		for(int i = 0; i < NUM_KEYS; i++) {
			pdown = down;
			pkeys[i] = keys[i];
		}
	}
	
	/**
	 * 
	 * @return retorna verdadeiro de o rato por clicado
	 */
	public static boolean isDown() { return down; }
	public static boolean isPressed() { return down && !pdown; }
	public static boolean isReleased() { return !down && pdown; }
	
	public static void setKey(int i, boolean b) { keys[i] = b; }
	public static boolean isDown(int i) { return keys[i]; }
	public static boolean isPressed(int i) { return keys[i] && !pkeys[i]; }
	
}
