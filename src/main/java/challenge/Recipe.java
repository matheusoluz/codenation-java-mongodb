package challenge;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe para mapear a receita no MongoDB
 *
 */

@Document(collection = "recipe")
public class Recipe{
	
	private String id;
	private String title;
	private String description;
	private List<String> ingredients;
	private List<RecipeComment> comments;
	private List<String> likes;
		
	public Recipe() {
		
	}

	public Recipe(String id, String title, String description, List<String> ingredients) {		
		this.id = id;
		this.title = title;
		this.description = description;
		this.ingredients = ingredients;
	}

	
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<RecipeComment> getComments() {
		return comments;
	}

	public void setComments(List<RecipeComment> comments) {
		this.comments = comments;
	}

	public List<String> getLikes() {
		return likes;
	}

	public void setLikes(List<String> likes) {
		this.likes = likes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
