/**
 * 
 */
package todo1.com.ec.store.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import todo1.com.ec.store.model.Category;
import todo1.com.ec.store.services.CategoryService;

/**
 * @author WINNER
 *
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

	final static Logger logger = LoggerFactory.getLogger(CategoryController.class);

	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * API REST para crear una categoria
	 * 
	 * @param model
	 * @param reqCategria
	 * @return
	 */
	@PostMapping(produces = "application/json")
	public Category saveCategoria(ModelMap model, @RequestBody Category reqCategory) {
		Category category = null;
		try {
			category = categoryService.crearCategory(reqCategory);
		} catch (DataIntegrityViolationException ex) {
			logger.debug(ex.getMessage(), ex);
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		}
		if (category == null) {
			throw new NoSuchElementException("Ya existe un categoria con código: " + reqCategory.getName());
		}
		return category;
	}

	/**
	 * API REST para obtener todas las categorias
	 * 
	 * @return
	 */
	
	@GetMapping(produces = "application/json")
	public List<Category> getCategorias() {
		try {
			return categoryService.obtenerCategorys();
		} catch (DataIntegrityViolationException ex) {
			logger.debug(ex.getMessage(), ex);
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		}

	}

}
