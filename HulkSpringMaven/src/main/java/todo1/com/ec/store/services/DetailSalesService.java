/**
 * 
 */
package todo1.com.ec.store.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import todo1.com.ec.store.model.DetailSales;
import todo1.com.ec.store.model.Product;
import todo1.com.ec.store.repository.DetailSalesRepository;
import todo1.com.ec.store.repository.ProductRepository;
import todo1.com.ec.store.views.DetailSalesView;

/**
 * @author WINNER
 *
 */
@Service
@Transactional
public class DetailSalesService {

	private DetailSalesRepository detailSalesRepository;
	private ProductRepository productRepository;

	public DetailSalesService() {

	}

	@Autowired
	public DetailSalesService(DetailSalesRepository detailSalesRepository, ProductRepository productRepository) {
		this.detailSalesRepository = detailSalesRepository;
		this.productRepository = productRepository;
	}

	/**
	 * Método que permite transfomar de tipo DetailSales a DetailSalesView
	 * 
	 * @param detailSales
	 * @return
	 */
	private DetailSalesView doDetalleventaView(DetailSales detailSales) {
		DetailSalesView detailSalesView = new DetailSalesView(detailSales.getCantidad(),
				detailSales.getSubtotal(), detailSales.getTotal());
		Optional<Product> productOptional = productRepository.findById(detailSales.getProduct().getId());
		if (productOptional.isPresent()) {
			detailSalesView.setProducto(productOptional.get().getName());
		}
		return detailSalesView;
	}

	/**
	 * Método que obtiene todos los detailSales de la bdd
	 * 
	 * @return
	 */
	public List<DetailSales> obtenerDetailSaless() {
		return (List<DetailSales>) detailSalesRepository.findAll();
	}

	/**
	 * Método que permite crear un DetailSales en la bdd
	 * 
	 * @param detailSales
	 * @return
	 */
	public DetailSalesView crearDetailSales(DetailSales detailSales) {
		detailSales = detailSalesRepository.save(detailSales);
		return doDetalleventaView(detailSales);
	}

}
