package todo1.com.ec.store.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import todo1.com.ec.store.model.Category;
import todo1.com.ec.store.repository.CategoryRepository;


/**
 * @author WINNER
 *
 */
@RunWith(SpringRunner.class)
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;
	@MockBean
	private CategoryRepository categoryRepository;
	@Mock
    private Category category;
	
	@TestConfiguration
    static class CategoryServiceImplTestContextConfiguration {
        @Bean
        public CategoryService employeeService() {
            return new CategoryService();
        }
    }
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		categoryService = new CategoryService(categoryRepository);
	}
	
	private Category createCategory() {
		Category catalogo = new Category();
		catalogo.setName("CAMISETA5");
		catalogo.setDescription("CREATE CAMISETA");
		return catalogo;
	}
	
	private Category findCategory() {
		Category catalogo = new Category();
		catalogo.setName("CAMISETA4");
		catalogo.setDescription("FIND CATEGORIA");
		return catalogo;
	}
	
	@Test
	public void whenValidName_thenCategoryShouldBeFound() {
		String nameCategory = "CAMISETA5";
		category = createCategory();
		Category catalogo = findCategory();
		when(categoryRepository.findByName(catalogo.getName())).thenReturn(catalogo);
		when(categoryRepository.save(category)).thenReturn(category);
		Category savedCategory = categoryService.crearCategory(category);
		// Assert
		assertThat(savedCategory.getName(), is(equalTo(nameCategory)));
	}
	
	
	@Test
	public void whenValidName_thenCategoryShouldNotFound() {
		category = createCategory();
		when(categoryRepository.findByName(category.getName())).thenReturn(category);
		when(categoryRepository.save(category)).thenReturn(category);
		Category savedCategory = categoryService.crearCategory(category);
		// Assert
		assertThat(savedCategory, is(equalTo(null)));
	}
	
	@Test
	public void whenValidList_thenCategoryShouldBeFound() {
		List<Category> lista = new ArrayList<>();
		lista.add(createCategory());
		when(categoryRepository.findAll()).thenReturn(lista);
		List<Category> getCategorys = categoryService.obtenerCategorys();
		// Assert
		assertThat(getCategorys.size(), is(equalTo(1)));
	}
}
