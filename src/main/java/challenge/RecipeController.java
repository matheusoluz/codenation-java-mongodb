package challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	private RecipeService service;

	@PostMapping
	public Recipe save(@RequestBody Recipe recipe, BindingResult result) {
		return service.save(recipe);
	}

	@PutMapping(value = "/{id}")
	public void update(@PathVariable("id") String id, @RequestBody Recipe recipe) {
		service.update(id, recipe);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}

	@GetMapping(value = "/{id}")
	public Recipe get(@PathVariable("id") String id) {
		return service.get(id);
	}

	@GetMapping(value = "/ingredient")
	public List<Recipe> listByIngredient(@RequestParam(value = "ingredient", defaultValue = "") String ingredient) {
		return service.listByIngredient(ingredient);
	}

	@GetMapping(value = "/search")
	public List<Recipe> search(@RequestParam(value = "search", defaultValue = "") String search) {
		return service.search(search);
	}

	@PostMapping(value = "/{id}/like/{userId}")
	public void like(@PathVariable("id") String id, @PathVariable("userId") String userId, BindingResult result) {
		service.like(id, userId);
	}

	public void unlike() {
		service.unlike(null, null);
	}

	@PostMapping(value = "/{id}/comment")
	public RecipeComment addComment(@PathVariable("id") String id, @RequestBody RecipeComment comment) {
		return service.addComment(id, comment);
	}

	@PutMapping(value = "/{id}/comment/{commentId}")
	public void updateComment(@PathVariable("id") String id, @RequestBody RecipeComment comment, @PathVariable("commentId") String commentId) {
		service.updateComment(id, comment, commentId);
	}

	public void deleteComment() {
		service.deleteComment(null, null);
	}

}
