package challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public Recipe save(Recipe recipe) {
		return this.recipeRepository.save(recipe);
	}

	@Override
	public void update(String id, Recipe recipe) {
		Optional<Recipe> newObj = this.recipeRepository.findById(id);
		if (newObj.isPresent()) {
			Recipe updateRecipe = newObj.get();
			updateRecipe.setTitle(recipe.getTitle());
			updateRecipe.setDescription(recipe.getDescription());
			updateRecipe.setIngredients(recipe.getIngredients());
			this.recipeRepository.save(updateRecipe);
		}
	}

	@Override
	public void delete(String id) {
		Optional<Recipe> newObj = this.recipeRepository.findById(id);
		if (newObj.isPresent()) 
			this.recipeRepository.delete(newObj.get());
	}

	@Override
	public Recipe get(String id) {
		return this.recipeRepository.findById(id).get();
	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {
		Sort sort = new Sort(Sort.Direction.ASC, "title");
		return this.recipeRepository.findByIngredientsIn(Arrays.asList(ingredient), sort);
	}

	@Override
	public List<Recipe> search(String search) {
		Sort sort = new Sort(Sort.Direction.ASC, "title");
		return this.recipeRepository.SearchTitleOrDescription(search, sort);
	}

	@Override
	public void like(String id, String userId) {
		Recipe recipe = this.get(id);
		if (recipe.getLikes() == null) {
			recipe.setLikes(new ArrayList<String>());			
		}
		recipe.getLikes().add(userId);
		recipe = this.recipeRepository.save(recipe);
	}

	@Override
	public void unlike(String id, String userId) {
		Recipe recipe = this.get(id);
		if (recipe.getLikes() != null && recipe.getLikes().size() > 0) {
			recipe.getLikes().remove(userId);			
			recipe = this.recipeRepository.save(recipe);
		}
	}

	@Override
	public RecipeComment addComment(String id, RecipeComment comment) {
		Recipe recipe = this.get(id);
		if (recipe.getComments() == null) {
			recipe.setComments(new ArrayList<RecipeComment>());			
		}
		recipe.getComments().add(comment);
		recipe = this.recipeRepository.save(recipe);
		
		return comment;
	}
		
	@Override
	public void updateComment(String id, RecipeComment comment, String commentId) {
		Recipe recipe = this.get(id);	
		List<RecipeComment>  recipeComments = recipe.getComments().
			stream().
			filter(rc -> rc.getId().equals(commentId)).
			collect(Collectors.toList());
		
		if (recipeComments.size() > 0 && recipeComments != null) {
			recipeComments.forEach(r -> r.setComment(comment.getComment()));
			recipe = this.recipeRepository.save(recipe) ;
		}	
	}

	@Override
	public void deleteComment(String id, String commentId) {
		Recipe recipe = this.get(id);
		recipe.getComments().removeIf(rc -> rc.getId().equals(commentId));
		recipe = this.recipeRepository.save(recipe) ;
	}
	
}
