/**
 * 
 */
package todo1.com.ec.store.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import todo1.com.ec.store.model.Usuario;



/**
 * @author WINNER
 *
 */
@Repository
public interface UserRepository extends CrudRepository<Usuario, Integer> {

	Optional<Usuario> findById(Integer id);

	Usuario findByEmail(String email);

	Usuario findByEmailAndPassword(String email, String password);
}
