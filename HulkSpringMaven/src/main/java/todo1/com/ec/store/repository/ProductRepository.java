/**
 * 
 */
package todo1.com.ec.store.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import todo1.com.ec.store.model.Product;

/**
 * @author WINNER
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

	Optional<Product> findById(Integer id);

	Product findByName(String name);
}