/**
 * 
 */
package todo1.com.ec.store.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import todo1.com.ec.store.model.Kardex;
import todo1.com.ec.store.services.KardexService;
import todo1.com.ec.store.views.BodyListView;
import todo1.com.ec.store.views.KardexView;

/**
 * @author WINNER
 *
 */
@RestController
@RequestMapping("/kardex")
public class KardexController {

	final static Logger logger = LoggerFactory.getLogger(KardexController.class);

	private KardexService kardexService;

	@Autowired
	public KardexController(KardexService kardexService) {
		this.kardexService = kardexService;
	}

	/**
	 * API REST para crear un nuevo kardex
	 * 
	 * @param model
	 * @param reqKardex
	 * @return
	 */
	@PostMapping(produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public KardexView saveKardex(ModelMap model, @RequestBody Kardex reqKardex) {
		KardexView kardex = null;
		try {
			kardex = kardexService.crearKardex(reqKardex);
		} catch (DataIntegrityViolationException ex) {
			logger.debug(ex.getMessage(), ex);
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		}
		return kardex;
	}

	/**
	 * API REST para obtener todos los kardexs
	 * 
	 * @return
	 */
	@GetMapping(produces = "application/json")
	public BodyListView<KardexView> getKardexs() {
		try {
			return kardexService.obtenerKardexs();
		} catch (DataIntegrityViolationException ex) {
			logger.debug(ex.getMessage(), ex);
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		}

	}

	/**
	 * API REST para eliminar un kardex
	 * 
	 * @return
	 */
	@RequestMapping(path = "/{id}", produces = "application/json")
	public void eliminarPaquete(@PathVariable(value = "id") Integer codigo) {
		try {
			kardexService.eliminar(codigo);
		} catch (DataIntegrityViolationException e) {
			logger.info("Error en el consumo del servicio eliminarPaquete : " + e.getMessage());
			throw new DataIntegrityViolationException(e.getMessage());
		}
	}

}
