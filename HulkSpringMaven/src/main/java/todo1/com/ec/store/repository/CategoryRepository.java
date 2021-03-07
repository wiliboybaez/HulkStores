/**
 * 
 */
package todo1.com.ec.store.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import todo1.com.ec.store.model.Category;



/**
 * @author WINNER
 *
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {

	Optional<Category> findById(Integer id);

	Category findByName(String name);
}