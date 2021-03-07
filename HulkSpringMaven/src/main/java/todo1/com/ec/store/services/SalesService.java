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

import todo1.com.ec.store.model.Product;
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
@Service
@Transactional
public class SalesService {


	private SalesRepository salesRepository;
	private ProductRepository productRepository;
	private UserRepository userRepository;

	public SalesService() {

	}

	@Autowired
	public SalesService(SalesRepository salesRepository, ProductRepository productRepository,
			UserRepository userRepository) {
		this.salesRepository = salesRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}

	/**
	 * Método que permite transfomar de tipo Sales a SalesView
	 * 
	 * @param sales
	 * @return
	 */
	private SalesView doSalesView(Sales sales) {
		SalesView salesView = new SalesView(sales.getNroDocumento(), sales.getFechaSales(),
				sales.getSubtotal(), sales.getIva(), sales.getTotal());
		Optional<Usuario> userOpcional = userRepository.findById(sales.getUsuario().getId());
		if (userOpcional.isPresent()) {
			salesView.setUser(userOpcional.get().getEmail());
		}
		return salesView;
	}

	/**
	 * Método que obtiene todos los Sales de la bdd
	 * 
	 * @return
	 */
	public BodyListView<SalesView> obtenerSaless() {
		List<SalesView> saless = new ArrayList<>();
		List<Sales> respuesta = (List<Sales>) salesRepository.findAll();

		if (respuesta != null && !respuesta.isEmpty()) {
			for (Sales sales : respuesta) {
				saless.add(doSalesView(sales));
			}
		}
		return new BodyListView<>(saless);
	}

	/**
	 * Método que obtiene todos los Sales de un empleado especifico
	 * 
	 * @return
	 */
	public BodyListView<SalesView> obtenerSalessPorIdUser(Integer idUser) {
		List<SalesView> saless = new ArrayList<>();
		List<Sales> respuesta = (List<Sales>) salesRepository.findByUsuarioId(idUser);

		if (respuesta != null && !respuesta.isEmpty()) {
			for (Sales sales : respuesta) {
				saless.add(doSalesView(sales));
			}
		}
		return new BodyListView<>(saless);
	}

	/**
	 * Método que permite crear un sales en la bdd
	 * 
	 * @param sales
	 * @return
	 */
	public SalesView crearSales(Sales sales) {
		sales.setFechaSales(new Date());
		sales.setNroDocumento("FAC-" + productRepository.count() + 1);
		sales.getDetailList().forEach(detalle -> {
			Optional<Product> productOpcional = productRepository.findById(detalle.getProduct().getId());
			productOpcional.get().setStock(productOpcional.get().getStock() - detalle.getCantidad());
			productRepository.save(productOpcional.get());

			detalle.setSales(sales);
		});
		return doSalesView(salesRepository.save(sales));
	}

}
