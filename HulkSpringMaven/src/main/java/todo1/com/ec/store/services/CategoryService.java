/**
 * 
 */
package todo1.com.ec.store.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import todo1.com.ec.store.model.Category;
import todo1.com.ec.store.repository.CategoryRepository;



/**
 * @author WINNER
 *
 */
@Service
@Transactional
public class CategoryService {


	private CategoryRepository categoryRepository;

	public CategoryService() {

	}

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/**
	 * Método que obtiene todos los category de la bdd
	 * 
	 * @return
	 */
	public List<Category> obtenerCategorys() {
		return (List<Category>) categoryRepository.findAll();
	}

	/**
	 * Método que permite crear un Category en la bdd
	 * 
	 * @param category
	 * @return
	 */
	public Category crearCategory(Category category) {
		Category categoryExiste = categoryRepository.findByName(category.getName());
		if (categoryExiste == null) {
			return categoryRepository.save(category);
		}
		return null;
	}

}
