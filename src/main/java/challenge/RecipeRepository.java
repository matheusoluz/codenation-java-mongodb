package challenge;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RecipeRepository extends MongoRepository<Recipe, String>{

	@Query("{ 'ingredients': ?0 }")
	List<Recipe> findByIngredient(String text, Sort sort);
	
	@Query("{ $or: [{'title' : {$regex: ?0, $options: 'i'}}, {'description' : {$regex: ?0, $options: 'i'}}] }")
	List<Recipe> SearchTitleOrDescription(String text, Sort sort);
	
	
	
}
