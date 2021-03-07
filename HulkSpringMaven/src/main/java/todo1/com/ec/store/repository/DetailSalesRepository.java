/**
 * 
 */
package todo1.com.ec.store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import todo1.com.ec.store.model.DetailSales;



/**
 * @author WINNER
 *
 */

@Repository
public interface DetailSalesRepository extends CrudRepository<DetailSales, Integer> {

}