package handlers.input;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;


/**
 *   Classe com funçoes que detectam inputs 
 *   do teclado e do rato usando a API InputAdapter 
 *  
 */

public class MyInputProcessor extends InputAdapter {
	
	/**
	 *    detecta o movimento do rato guardando as suas coordenadas
	 *    retorna verdadeiro se existir movimento no rato 
	 */
	public boolean mouseMoved(int x, int y) {
		MyInput.x = x;
		MyInput.y = y;
		return true;
	}
	
	/*
	public boolean touchDragged(int x, int y, int pointer) {
		MyInput.x = x;
		MyInput.y = y;
		MyInput.down = true;
		return true;
	}
	*/

	/**
	 *  funcao que detecta se os botoes do rato foram premidos
	 *  e são guardados nas variaveis da 
	 *  classe MyInput x e y
	 */
	
	public boolean touchDown(int x, int y, int pointer, int button) {
		MyInput.x = x;
	    MyInput.y = y;
	    MyInput.down = true;
		return true;
		
	}
	
	/**
	 *  funcao que detecta se os botoes do rato foram largados
	 *  e são guardados nas variaveis da 
	 *  classe MyInput x e y
	 */
	
	public boolean touchUp(int x, int y, int pointer, int button) {
		MyInput.x = x;
		MyInput.y = y;
		MyInput.down = false;
		return true;
	}
	
	/**
	 *  funcao que detecta se as teclas desejadas 
	 *  foram premidas e são guardadas nas variaveis da 
	 *  classe MyInput 
	 */
	
	public boolean keyDown(int k) {
		if(k == Keys.Z) {
			MyInput.setKey(MyInput.BUTTON1, true);
		}
		if(k == Keys.M) {
			MyInput.setKey(MyInput.BUTTON2, true);
		}
		return true;
	}
	
	/**
	 *  funcao que detecta se as teclas desejadas 
	 *  foram largadas e são guardadas nas variaveis da 
	 *  classe MyInput 
	 */
	
	public boolean keyUp(int k) {
		if(k == Keys.Z) {
			MyInput.setKey(MyInput.BUTTON1, false);
		}
	    if(k == Keys.M) {
			MyInput.setKey(MyInput.BUTTON2, false);
		}
		return true;
	}
	
}
