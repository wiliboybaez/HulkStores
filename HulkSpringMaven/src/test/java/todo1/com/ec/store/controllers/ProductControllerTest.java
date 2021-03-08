/**
 * 
 */
package todo1.com.ec.store.controllers;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import todo1.com.ec.store.controller.ProductController;
import todo1.com.ec.store.model.Product;
import todo1.com.ec.store.views.BodyListView;
import todo1.com.ec.store.views.ProductView;


/**
 * @author WINNER
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private ProductController productController;
	@Autowired
	private ObjectMapper objectmapper;
	String productJson
	= "{\"name\":\"Cam Test\",\"descripcion\":\"Camiseta test\"}";

	@Test
	public void getArrivals() throws Exception {
		ProductView product = new ProductView(1, "Name Arrival", "description", new Date(), 100, "url",
				new BigDecimal(10));
		List<ProductView> products = new ArrayList<>();
		products.add(product);

		given(productController.getProducts()).willReturn(new BodyListView<>(products));
		mvc.perform(get("/product").contentType(MediaType.APPLICATION_JSON))
		//mvc.perform(get("/product").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.data", hasSize(1)))
				.andExpect(jsonPath("$.data.[0].name", is(product.getName())))
				;
	}

	@Test
	public void eliminarProduct() throws Exception {
		Product product = crearProduct();
		mvc.perform(delete("/product/" + product.getId()).contentType(MediaType.APPLICATION_JSON))
						/*.andExpect(status().isOk())*/;
	}

	@Test
	public void nuevoProduct() throws Exception {
		Product product = crearProduct();
		product.setId(null);
		mvc.perform(post("/product/").content(objectmapper.writeValueAsString(product))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	public void actualizarProduct() throws Exception {
		Product product = crearProduct();
		mvc.perform(put("/product/").content(objectmapper.writeValueAsString(product))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	private Product crearProduct() {
		Product product = new Product();
		//product.setId(1);
		product.setName("CrearCamProduct");
		product.setDescription("Crea camiseta producto");
		product.setStock(10);
		product.setPrice(new BigDecimal(10));
		product.setUrl("url");
		return product;
	}

	@Test
	public void createProductTest() throws Exception {
		ProductView mockProduct = new ProductView(1, "CreaCamTest", "Crear camiseta por TEST",
				new Date(), 10, "url", new BigDecimal(10));

		Mockito.when(productController.saveProduct(Mockito.any(Product.class))).thenReturn(mockProduct);

		// Send course as body to /product
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product").accept(MediaType.APPLICATION_JSON)
				.content(productJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

}
