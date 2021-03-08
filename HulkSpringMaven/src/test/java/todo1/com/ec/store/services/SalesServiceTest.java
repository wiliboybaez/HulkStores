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
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import todo1.com.ec.store.model.DetailSales;
import todo1.com.ec.store.model.Product;
import todo1.com.ec.store.model.Role;
import todo1.com.ec.store.model.Sales;
import todo1.com.ec.store.model.Usuario;
import todo1.com.ec.store.repository.ProductRepository;
import todo1.com.ec.store.repository.SalesRepository;
import todo1.com.ec.store.repository.UserRepository;
import todo1.com.ec.store.views.BodyListView;
import todo1.com.ec.store.views.SalesView;

/**
 * @author WINNER
 *
 */
@RunWith(SpringRunner.class)
public class SalesServiceTest {

	@Autowired
	private SalesService salesService;
	@MockBean
	private SalesRepository salesRepository;
	@MockBean
	private ProductRepository productRepository;
	@MockBean
	private UserRepository usuarioRepository;

	@Mock
	private Sales sales;

	@TestConfiguration
	static class SalesServiceImplTestContextConfiguration {
		@Bean
		public SalesService employeeService() {
			return new SalesService();
		}
	}

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		salesService = new SalesService(salesRepository, productRepository, usuarioRepository);
	}

	private Category findCategory() {
		Category category = new Category();
		category.setName("CAMISETA");
		category.setDescription("Camisetas Find");
		return category;
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

	private Role findRol() {
		Role rol = new Role();
		rol.setId(1);
		rol.setRole("CLIENTE");
		rol.setDescription("CLIENTE DE VENTAS");
		return rol;
	}

	private Usuario findUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setEmail("cliente@todo1.com");
		usuario.setRole(findRol());
		return usuario;
	}
	
	private Sales createSales() {
		Sales sales = new Sales();
		sales.setNroDocumento("nroDocumento");
		sales.setFechaSales(new Date());
		sales.setSubtotal(new BigDecimal(100));
		sales.setIva(new BigDecimal(12));
		sales.setTotal(new BigDecimal(112));
		sales.setUsuario(findUsuario());
		List<DetailSales> detalleList = new ArrayList<>();
		detalleList.add(createDetailsSales());
		sales.setDetailList(detalleList);
		return sales;
	}

	private DetailSales createDetailsSales() {
		DetailSales detalleSales = new DetailSales();
		detalleSales.setProduct(findProduct());
		detalleSales.setCantidad(2);
		detalleSales.setSubtotal(new BigDecimal(100));
		detalleSales.setTotal(new BigDecimal(200));
		return detalleSales;
	}

	@Test
	public void whenValidName_thenSalesShouldBeFound() {
		BigDecimal total = new BigDecimal(112);
		sales = createSales();
		when(productRepository.findById(findProduct().getId())).thenReturn(Optional.of(findProduct()));
		when(usuarioRepository.findById(findUsuario().getId())).thenReturn(Optional.of(findUsuario()));
		when(salesRepository.save(sales)).thenReturn(sales);
		SalesView savedSales = salesService.crearSales(sales);
		// Assert
		assertThat(savedSales.getTotal(), is(equalTo(total)));
	}

	@Test
	public void whenValidList_thenSalesShouldBeFound() {
		List<Sales> lista = new ArrayList<>();
		lista.add(createSales());
		when(usuarioRepository.findById(findUsuario().getId())).thenReturn(Optional.of(findUsuario()));
		when(salesRepository.findAll()).thenReturn(lista);
		BodyListView<SalesView> getSaless = salesService.obtenerSaless();
		// Assert
		assertThat(getSaless.getData().size(), is(equalTo(1)));
	}

	@Test
	public void whenValidList_thenSalesByUserShouldBeFound() {
		List<Sales> lista = new ArrayList<>();
		lista.add(createSales());
		when(usuarioRepository.findById(findUsuario().getId())).thenReturn(Optional.of(findUsuario()));
		when(salesRepository.findByUsuarioId(1)).thenReturn(lista);
		BodyListView<SalesView> getSaless = salesService.obtenerSalessPorIdUser(1);
		// Assert
		assertThat(getSaless.getData().size(), is(equalTo(1)));
	}
}
