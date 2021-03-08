/**
 * 
 */
package todo1.com.ec.store.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import todo1.com.ec.store.model.DetailSales;
import todo1.com.ec.store.model.Product;
import todo1.com.ec.store.repository.DetailSalesRepository;
import todo1.com.ec.store.repository.ProductRepository;
import todo1.com.ec.store.views.DetailSalesView;


/**
 * @author WINNER
 *
 */
@RunWith(SpringRunner.class)
public class DetailSalesServiceTest {

	@Autowired
	private DetailSalesService detailSalesService;
	@MockBean
	private DetailSalesRepository detailSalesRepository;
	@MockBean
	private ProductRepository productRepository;

	@Mock
	private DetailSales detailSales;

	@TestConfiguration
	static class DetailSalesServiceImplTestContextConfiguration {
		@Bean
		public DetailSalesService detailSalesService() {
			return new DetailSalesService();
		}
	}

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		detailSalesService = new DetailSalesService(detailSalesRepository, productRepository);
		Mockito.when(productRepository.findById(findProducts().get().getId())).thenReturn(findProducts());
	}

	private Category findCategory() {
		Category category = new Category();
		category.setName("CAMISETA");
		category.setDescription("Camisetas Find");
		return category;
	}

	private Optional<Product> findProducts() {
		Optional<Product> product =Optional.of(new Product());
		//product.get().setId(1);
		product.get().setName("findCamProduct");
		product.get().setDescription("findProduct camiseta product");
		product.get().setStock(20);
		product.get().setPrice(new BigDecimal(10));
		product.get().setUrl("url");
		product.get().setCategory(findCategory());
		return product;
	}
	
	private DetailSales createDetailSales() {
		DetailSales detailSales = new DetailSales();
		detailSales.setProduct(findProducts().get());
		detailSales.setCantidad(2);
		detailSales.setSubtotal(new BigDecimal(100));
		detailSales.setTotal(new BigDecimal(200));
		return detailSales;
	}

	@Test
	public void whenValidName_thenDetailSalesShouldBeFound() {
		BigDecimal total = new BigDecimal(200);
		detailSales = createDetailSales();
		when(detailSalesRepository.save(detailSales)).thenReturn(detailSales);
		DetailSalesView savedDetailSales = detailSalesService.crearDetailSales(detailSales);
		// Assert
		assertThat(savedDetailSales.getTotal(), is(equalTo(total)));
	}

	@Test
	public void whenValidList_thenDetailSalesShouldBeFound() {
		List<DetailSales> lista = new ArrayList<>();
		lista.add(createDetailSales());
		when(detailSalesRepository.findAll()).thenReturn(lista);
		List<DetailSales> getDetailSaless = detailSalesService.obtenerDetailSaless();
		// Assert
		assertThat(getDetailSaless.size(), is(equalTo(1)));
	}
}
