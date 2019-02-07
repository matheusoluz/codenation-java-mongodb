package challenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceImplTest {

	@Mock
	private RecipeRepository recipeRepository;
	
	@InjectMocks
	private RecipeServiceImpl recipeServiceImpl;
	
	@Test
	public void deveRetornarValorXSeEuPassarValorY() {
		Recipe recipeWithId = new Recipe("123456");
		Recipe recipe = new Recipe();
		when(recipeRepository.save(recipe)).thenReturn(recipeWithId);
		Recipe actual = recipeServiceImpl.save(recipe);
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(recipeWithId);
		verify(recipeRepository, times(1)).save(recipe);
	}
	
}
