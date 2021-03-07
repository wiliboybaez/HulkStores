/**
 * 
 */
package todo1.com.ec.store.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import todo1.com.ec.store.model.Sales;



/**
 * @author WINNER
 *
 */
@Repository
public interface SalesRepository extends CrudRepository<Sales, Integer> {

	List<Sales> findByUsuarioId(Integer id);
}