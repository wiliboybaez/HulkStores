/**
 * 
 */
package todo1.com.ec.store.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import todo1.com.ec.store.model.Sales;
import todo1.com.ec.store.services.SalesService;
import todo1.com.ec.store.views.BodyListView;
import todo1.com.ec.store.views.SalesView;

/**
 * @author WINNER
 *
 */
@RestController
@RequestMapping("/sales")
public class SalesController  {

	final static Logger logger = LoggerFactory.getLogger(SalesController.class);

	private SalesService salesService;

	@Autowired
	public SalesController(SalesService salesService) {
		this.salesService = salesService;
	}

	/**
	 * API REST para crear una nueva sales
	 * 
	 * @param model
	 * @param reqSales
	 * @return
	 */
	@PostMapping(produces = "application/json")
	public SalesView saveSales(ModelMap model, @RequestBody Sales reqSales) {
		SalesView sales = null;
		try {
			sales = salesService.crearSales(reqSales);
		} catch (DataIntegrityViolationException ex) {
			logger.debug(ex.getMessage(), ex);
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		}
		return sales;
	}

	/**
	 * API REST para obtener todas las saless
	 * 
	 * @return
	 */
	@GetMapping(produces = "application/json")
	public BodyListView<SalesView> getSaless() {
		try {
			return salesService.obtenerSaless();
		} catch (DataIntegrityViolationException ex) {
			logger.debug(ex.getMessage(), ex);
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		}

	}

	/**
	 * API REST para obtener todas las sales
	 * 
	 * @return
	 */
	@GetMapping(value = "/{idUser}", produces = "application/json")
	public BodyListView<SalesView> getSaless(@PathVariable("idUser") Integer idUser) {
		try {
			return salesService.obtenerSalessPorIdUser(idUser);
		} catch (DataIntegrityViolationException ex) {
			logger.debug(ex.getMessage(), ex);
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		}

	}

}
