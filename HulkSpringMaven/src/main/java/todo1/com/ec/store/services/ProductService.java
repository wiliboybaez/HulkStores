/**
 * 
 */
package todo1.com.ec.store.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import todo1.com.ec.store.model.Product;
import todo1.com.ec.store.repository.CategoryRepository;
import todo1.com.ec.store.repository.ProductRepository;
import todo1.com.ec.store.views.BodyListView;
import todo1.com.ec.store.views.ProductView;

/**
 * @author WINNER
 *
 */
@Service
@Transactional
public class ProductService {

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;

	public ProductService() {

	}

	@Autowired
	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	/**
	 * Método que permite transfomar de tipo Product a ProductView
	 * 
	 * @param product
	 * @return
	 */
	private ProductView doProductView(Product product) {
		ProductView productView = new ProductView(product.getId(), product.getName(),
				product.getDescription(), product.getDate(), product.getStock(), product.getUrl(),
				product.getPrice());
		productView
				.setCategory(categoryRepository.findByName(product.getCategory().getName()).getDescription());
		return productView;
	}

	/**
	 * Método que obtiene todos los product de la bdd
	 * 
	 * @return
	 */
	public BodyListView<ProductView> obtenerProducts() {
		List<ProductView> products = new ArrayList<>();
		List<Product> respuesta = (List<Product>) productRepository.findAll();

		if (respuesta != null && !respuesta.isEmpty()) {
			for (Product prod : respuesta) {
				products.add(doProductView(prod));
			}
		}
		return new BodyListView<>(products);
	}

	/**
	 * Método que permite crear un Product en la bdd
	 * 
	 * @param product
	 * @return
	 */
	public ProductView crearProduct(Product product) {
		product.setDate(new Date());
		product.setCategory(categoryRepository.findByName(product.getCategory().getName()));
		Product productExiste = productRepository.findByName(product.getName());
		if (productExiste == null) {
			product = productRepository.save(product);
			return doProductView(product);
		}
		return null;
	}

	/**
	 * Método que permite crear un Product en la bdd
	 * 
	 * @param product
	 * @return
	 */
	public ProductView actualizarProduct(Product product) {
		product.setDate(new Date());
		if (product.getId() != null) {
			product = productRepository.save(product);
			return doProductView(product);
		}
		return null;
	}

	/**
	 * Método que permite eliminar un Product de la bdd
	 * 
	 * @param codigo
	 */
	public void eliminar(Integer codigo) {
		productRepository.deleteById(codigo);
	}
}
