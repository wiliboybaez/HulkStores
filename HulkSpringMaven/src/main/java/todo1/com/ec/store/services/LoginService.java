/**
 * 
 */
package todo1.com.ec.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todo1.com.ec.store.model.Usuario;
import todo1.com.ec.store.repository.RoleRepository;
import todo1.com.ec.store.repository.UserRepository;
import todo1.com.ec.store.views.BodyView;
import todo1.com.ec.store.views.UserView;

/**
 * @author WINNER
 *
 */
@Service
@Transactional
public class LoginService {

	private UserRepository userRepository;
	private RoleRepository rolRepository;

	public LoginService() {

	}

	@Autowired
	public LoginService(UserRepository userRepository, RoleRepository rolRepository) {
		this.userRepository = userRepository;
		this.rolRepository = rolRepository;
	}

	/**
	 * Método que permite transfomar de tipo User a UserViews
	 * 
	 * @param user
	 * @return
	 */
	private UserView doUserResponse(Usuario user) {
		UserView userViews = new UserView(user.getId(), user.getEmail());
		userViews.setRol(rolRepository.findById(user.getRole().getId()).get().getRole());
		userViews.setToken(user.getEmail()+"-123456789");//token temporal
		return userViews;
	}

	/**
	 * Método que obtiene todos los user de la bdd
	 * 
	 * @return
	 */
	public List<Usuario> obtenerUsers() {
		return (List<Usuario>) userRepository.findAll();
	}

	/**
	 * Método que permite crear un User en la bdd
	 * 
	 * @param user
	 * @return
	 */
	public BodyView<UserView> crearUser(Usuario user) {
		Usuario userExiste = userRepository.findByEmail(user.getEmail());
		if (userExiste == null) {
			user.setRole(rolRepository.findByRole(user.getRole().getRole()));
					//rolRepository.findById(user.getRole().getId()).get().getRole());
			user = userRepository.save(user);
			return new BodyView<>(doUserResponse(user));
		}
		return null;
	}

	/**
	 * Método que obtiene un User por correo y password
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public BodyView<UserView> obtenerPorEmailPassword(String email, String password) {
		Usuario respuesta = userRepository.findByEmailAndPassword(email, password);
		
		if (respuesta != null) {
			return new BodyView<>(doUserResponse(respuesta));
		}
		return null;
	}


}
