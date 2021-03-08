/**
 * 
 */
package todo1.com.ec.store.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import todo1.com.ec.store.model.Category;
import todo1.com.ec.store.model.Product;
import todo1.com.ec.store.repository.CategoryRepository;
import todo1.com.ec.store.repository.ProductRepository;
import todo1.com.ec.store.views.BodyListView;
import todo1.com.ec.store.views.ProductView;


/**
 * @author WINNER
 *
 */
@RunWith(SpringRunner.class)
public class ProductServiceTest {

	@Autowired
	private ProductService productService;
	@MockBean
	private ProductRepository productRepository;
	@MockBean
	private CategoryRepository categoryRepository;
	@Mock
    private Product product;
	
	@TestConfiguration
    static class ProductServiceImplTestContextConfiguration {
        @Bean
        public ProductService employeeService() {
            return new ProductService();
        }
    }
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		productService = new ProductService(productRepository, categoryRepository);
		Mockito.when(categoryRepository.findByName((findCategory().getName())).equals(findCategory()));
	}

	private Category findCategory() {
		Category category = new Category();
		category.setName("CAMISETA");
		category.setDescription("Camisetas Find");
		return category;
	}
	
	private Product createProduct() {
		Product product = new Product();
		//product.setId(1);
		product.setName("CrearCamProduct");
		product.setDescription("Crea camiseta producto");
		product.setStock(10);
		product.setPrice(new BigDecimal(10));
		product.setUrl("url");
		product.setCategory(findCategory());
		return product;
	}
	private Product findProduct() {
		Product product = new Product();
		//product.setId(1);
		product.setName("findCamProduct");
		product.setDescription("findProduct camiseta producto");
		product.setStock(20);
		product.setPrice(new BigDecimal(10));
		product.setUrl("url");
		product.setCategory(findCategory());
		return product;
	}
	
	
	
	@Test
	public void whenValidName_thenProductShouldBeFound() {
		String name = "Supereroes two";
		product = createProduct();
		Product product = findProduct();
		when(productRepository.findByName(product.getName())).thenReturn(product);
		when(productRepository.save(product)).thenReturn(product);
		ProductView savedProduct = productService.crearProduct(product);
		// Assert
		assertThat(savedProduct.getName(), is(equalTo(name)));
	}
	
	
	@Test
	public void whenValidName_thenProductShouldNotFound() {
		product = createProduct();
		when(productRepository.findByName(product.getName())).thenReturn(product);
		when(productRepository.save(product)).thenReturn(product);
		ProductView savedProduct = productService.crearProduct(product);
		// Assert
		assertThat(savedProduct, is(equalTo(null)));
	}
	
	@Test
	public void whenValidList_thenProductShouldBeFound() {
		List<Product> lista = new ArrayList<>();
		lista.add(createProduct());
		when(productRepository.findAll()).thenReturn(lista);
		BodyListView<ProductView> getProducts = productService.obtenerProducts();
		// Assert
		assertThat(getProducts.getData().size(), is(equalTo(1)));
	}
	
	@Test
    public void shouldCallDeleteMethodOfProductRepository_whenDeleteProductIsCalled() throws Exception {
        doNothing().when(productRepository).deleteById(5);
        productService.eliminar(5);
        // Assert
        verify(productRepository, times(1)).deleteById(5);
    }
}
