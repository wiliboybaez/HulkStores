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
import todo1.com.ec.store.model.Kardex;
import todo1.com.ec.store.model.Product;
import todo1.com.ec.store.model.Role;
import todo1.com.ec.store.model.Usuario;
import todo1.com.ec.store.repository.KardexRepository;
import todo1.com.ec.store.repository.ProductRepository;
import todo1.com.ec.store.repository.UserRepository;
import todo1.com.ec.store.views.BodyListView;
import todo1.com.ec.store.views.KardexView;

/**
 * @author WINNER
 *
 */
@RunWith(SpringRunner.class)
public class KardexServiceTest {

	@Autowired
	private KardexService stockService;
	@MockBean
	private KardexRepository stockRepository;
	@MockBean
	private ProductRepository productRepository;
	@MockBean
	private UserRepository usuarioRepository;
	
	@Mock
    private Kardex stock;
	
	@TestConfiguration
    static class KardexServiceImplTestContextConfiguration {
        @Bean
        public KardexService employeeService() {
            return new KardexService();
        }
    }
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		stockService = new KardexService(stockRepository, productRepository, usuarioRepository);
	}
	
	private Category findCategory() {
		Category category = new Category();
		category.setName("CAMISETA");
		category.setDescription("Camisetas Find");
		return category;
	}

	private Optional<Product> findProduct() {
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
	
	private Role findRol() {
		Role rol = new Role();
		rol.setId(1);
		rol.setRole("CLIENTE");
		rol.setDescription("CLIENTE DE VENTAS");
		return rol;
	}

	private Usuario findUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(2);
		usuario.setEmail("venta@todo1.com");
		usuario.setRole(findRol());
		return usuario;
	}
	
	
	private Kardex createKardex() {
		Kardex stock = new Kardex();
		stock.setId(1);
		stock.setCantidad(2);
		stock.setFechaKardex(new Date());
		stock.setTotal(new BigDecimal(200));
		stock.setProduct(findProduct().get());
		stock.setUser(findUsuario());
		return stock;
	}
	
	@Test
	public void whenValidName_thenKardexShouldBeFound() {
		BigDecimal total = new BigDecimal(200);
		stock = createKardex();
		when(productRepository.findById(findProduct().get().getId())).thenReturn(findProduct());
		when(usuarioRepository.findById(findUsuario().getId())).thenReturn(Optional.of(findUsuario()));
		when(stockRepository.save(stock)).thenReturn(stock);
		KardexView savedKardex = stockService.crearKardex(stock);
		// Assert
		assertThat(savedKardex.getTotal(), is(equalTo(total)));
	}
	
	
	@Test
	public void whenValidList_thenKardexShouldBeFound() {
		List<Kardex> lista = new ArrayList<>();
		lista.add(createKardex());
		when(productRepository.findById(findProduct().get().getId())).thenReturn(findProduct());
		when(usuarioRepository.findById(findUsuario().getId())).thenReturn(Optional.of(findUsuario()));
		when(stockRepository.findAll()).thenReturn(lista);
		BodyListView<KardexView> getKardexs = stockService.obtenerKardexs();
		// Assert
		assertThat(getKardexs.getData().size(), is(equalTo(1)));
	}
	
	
	@Test
    public void shouldCallDeleteMethodOfKardexRepository_whenDeleteProductIsCalled() throws Exception {
		Optional<Kardex> stock = Optional.of(createKardex());
		when(stockRepository.findById(stock.get().getId())).thenReturn(stock);
        doNothing().when(stockRepository).deleteById(1);
        stockService.eliminar(1);
        // Assert
        verify(stockRepository, times(1)).deleteById(1);
    }
}
