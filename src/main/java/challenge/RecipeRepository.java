package challenge;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String>{

	List<Recipe> findByIngredientsIn(List<String> ingredient, Sort sort);
	
	@Query("{ $or: [{'title' : {$regex: ?0, $options: 'i'}}, {'description' : {$regex: ?0, $options: 'i'}}] }")
	List<Recipe> SearchTitleOrDescription(String text, Sort sort);	
	
}
