/**
 * 
 */
package todo1.com.ec.store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import todo1.com.ec.store.model.Kardex;



/**
 * @author WINNER
 *
 */

@Repository
public interface KardexRepository extends CrudRepository<Kardex, Integer> {

}