/**
 * 
 */
package todo1.com.ec.store.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import todo1.com.ec.store.model.Product;
import todo1.com.ec.store.model.Role;



/**
 * @author WINNER
 *
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

	Optional<Role> findById(Integer id);
	Role findByRole(String role);

}
