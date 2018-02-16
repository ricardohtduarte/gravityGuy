package handlers.visual;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


/**
 * 
 * classe onde sao guardadas as imagens carregadas para o jogo
 *
 */
public class Tex {
	private static Tex instance;
	private HashMap<String, Texture> textures;  // estrutura onde guardamos as texturas 
											    // assim para chegar a uma textura basta indicar o 
	                                           // o nome com que a indentificámos
	
	/**
	 * construtor da classe Tex, cria 
	 * um hashmap onde serao guardadas as imagens com uma 
	 * respectiva chave
	 */
	private Tex() {
		textures = new HashMap<String, Texture>();
	}
	
	/**
	 * carrega as imagens para o hashmap
	 * @param path string com o caminho para o diretorio onde esta a imagem
	 * @param key chave que da acesso a imagem
	 */
	public void loadTexture(String path, String key) {
		Texture tex = new Texture(Gdx.files.internal(path));
		textures.put(key, tex);
	}
	
	/**
	 * 
	 * @param key chave que da acesso a textura desejada
	 * @return retorna a textura cuja chave e key
	 */
	public Texture getTexture(String key) {
		return textures.get(key);
	}
	
	/**
	 * eliminda textura 
	 * @param key chave da textura que deseja eliminar
	 */
	public void disposeTexture(String key) {
		Texture tex = textures.get(key);
		if(tex != null) tex.dispose();
	}

	public static Tex getTex(){

		if(instance == null) 
		{
			instance = new Tex();
		}
		return instance;
	}
}
