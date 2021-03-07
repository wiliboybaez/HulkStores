/**
 * 
 */
package todo1.com.ec.store.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import todo1.com.ec.store.model.Kardex;
import todo1.com.ec.store.model.Product;
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

@Service
@Transactional
public class KardexService {

	private KardexRepository kardexRepository;
	private ProductRepository productRepository;
	private UserRepository userRepository;

	public KardexService() {

	}

	@Autowired
	public KardexService(KardexRepository kardexRepository, ProductRepository productRepository,
			UserRepository userRepository) {
		this.kardexRepository = kardexRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}

	/**
	 * Método que permite transfomar de tipo Kardex a KardexView
	 * 
	 * @param kardex
	 * @return
	 */
	private KardexView doKardexView(Kardex kardex) {
		KardexView kardexView = new KardexView(kardex.getId(), kardex.getCantidad(),
				kardex.getFechaKardex(), kardex.getTotal());
		Optional<Product> productOpcional = productRepository.findById(kardex.getProduct().getId());
		if (productOpcional.isPresent()) {
			kardexView.setProducto(productOpcional.get().getName());
			kardexView.setPrecio(productOpcional.get().getPrice());
		}
		Optional<Usuario> userOpciona = userRepository.findById(kardex.getUser().getId());
		if (userOpciona.isPresent()) {
			kardexView.setUser(userOpciona.get().getEmail());
		}
		return kardexView;
	}

	/**
	 * Método que obtiene todos los kardex de la bdd
	 * 
	 * @return
	 */
	public BodyListView<KardexView> obtenerKardexs() {
		List<KardexView> products = new ArrayList<>();
		List<Kardex> respuesta = (List<Kardex>) kardexRepository.findAll();

		if (respuesta != null && !respuesta.isEmpty()) {
			for (Kardex kardex : respuesta) {
				products.add(doKardexView(kardex));
			}
		}
		return new BodyListView<>(products);
	}

	/**
	 * Método que permite crear un Kardex en la bdd
	 * 
	 * @param kardex
	 * @return
	 */
	public KardexView crearKardex(Kardex kardex) {
		kardex.setFechaKardex(new Date());
		Optional<Product> productOpcional = productRepository.findById(kardex.getProduct().getId());
		if (productOpcional.isPresent()) {
			productOpcional.get().setStock(productOpcional.get().getStock() + kardex.getCantidad());
			productRepository.save(productOpcional.get());
		}
		return doKardexView(kardexRepository.save(kardex));
	}

	/**
	 * Método que permite eliminar un Kardex de la bdd
	 * 
	 * @param codigo
	 */
	public void eliminar(Integer codigo) {
		Optional<Kardex> kardexOpcional = kardexRepository.findById(codigo);
		Product product = null;
		if (kardexOpcional.isPresent()) {
			product = kardexOpcional.get().getProduct();
			product.setStock(product.getStock() - kardexOpcional.get().getCantidad());
			productRepository.save(product);
			kardexRepository.deleteById(codigo);
		}
	}

}
