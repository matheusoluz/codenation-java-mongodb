package challenge;

import org.springframework.data.annotation.Id;

/**
 * Classe para mapear a receita no MongoDB
 *
 */
public class Recipe {

	@Id
	private String id;

	public Recipe() {
		
	}
	
	public Recipe(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
