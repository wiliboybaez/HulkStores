/**
 * 
 */
package todo1.com.ec.store.controller;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import todo1.com.ec.store.model.Product;
import todo1.com.ec.store.services.ProductService;
import todo1.com.ec.store.views.BodyListView;
import todo1.com.ec.store.views.ProductView;

/**
 * @author WINNER
 *
 */
@RestController
@RequestMapping("/product")
public class ProductController {

	final static Logger logger = LoggerFactory.getLogger(ProductController.class);

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * API REST para crear un product
	 * 
	 * @param model
	 * @param reqProduct
	 * @return
	 */
	@CrossOrigin
	@PostMapping(produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductView saveProduct(@RequestBody Product reqProduct) {
		ProductView product = null;
		try {
			product = productService.crearProduct(reqProduct);
		} catch (DataIntegrityViolationException ex) {
			logger.debug(ex.getMessage(), ex);
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		}
		if (product == null) {
			throw new NoSuchElementException("Ya existe un product con nombre: " + reqProduct.getName());
		}
		return product;
	}

	/**
	 * API REST para obtener todos los products
	 * 
	 * @return
	 */
	@CrossOrigin
	@GetMapping(produces = "application/json")
	public BodyListView<ProductView> getProducts() {
		try {
			return productService.obtenerProducts();
		} catch (DataIntegrityViolationException ex) {
			logger.debug(ex.getMessage(), ex);
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		}

	}

	/**
	 * API REST para eliminar un product
	 * 
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(path = "/{id}", produces = "application/json")
	public void eliminarPaquete(@PathVariable(value = "id") Integer codigo) {
		try {
			productService.eliminar(codigo);
		} catch (DataIntegrityViolationException e) {
			logger.info("Error en el consumo del servicio eliminarPaquete : " + e.getMessage());
			throw new DataIntegrityViolationException(e.getMessage());
		}
	}
	/**
	 * API REST para actualizar un product
	 * 
	 * @param model
	 * @param reqProduct
	 * @return
	 */
	@CrossOrigin
	@PutMapping(produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ProductView updateProduct(@RequestBody @Validated Product reqProduct) {
		ProductView product = null;
		try {
			product = productService.actualizarProduct(reqProduct);
		} catch (DataIntegrityViolationException ex) {
			logger.debug(ex.getMessage(), ex);
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		}
		if (product == null) {
			throw new NoSuchElementException("Ya existe un product con nombre: " + reqProduct.getName());
		}
		return product;
	}
}

