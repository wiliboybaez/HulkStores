/**
 * 
 */
package todo1.com.ec.store.controller;

import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import todo1.com.ec.store.model.Usuario;
import todo1.com.ec.store.services.LoginService;
import todo1.com.ec.store.views.*;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginController.
 *
 * @author WINNER
 */
@RestController
@RequestMapping("/users")
public class LoginController {



	/** The login service. */
	private LoginService loginService;

	/**
	 * Instantiates a new login controller.
	 *
	 * @param loginService the login service
	 */
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	/**
	 * API REST para crear un user.
	 *
	 * @param model the model
	 * @param reqUser the req user
	 * @return the body view
	 */
	@CrossOrigin
	@PostMapping(produces = "application/json")
	public BodyView<UserView> saveUser(ModelMap model, @RequestBody Usuario reqUser) {
		BodyView<UserView> user = null;
		try {
			user = loginService.crearUser(reqUser);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		}
		if (user == null) {
			throw new NoSuchElementException("Ya existe un user con email: " + reqUser.getEmail());
		}
		return user;
	}

	/**
	 * API REST para obtener todos los users.
	 *
	 * @return the users
	 */
	@CrossOrigin
	@GetMapping(produces = "application/json")
	public List<Usuario> getUsers() {
		try {
			return loginService.obtenerUsers();
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		}

	}

	/**
	 * API REST para obtener un User por email y password.
	 *
	 * @param email the email
	 * @param password the password
	 * @return the user por email password
	 */
	@CrossOrigin
	@GetMapping(value = "/{email}/{password}", produces = "application/json")
	public BodyView<UserView> getUserPorEmailPassword(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		try {
			BodyView<UserView> usuarioLogin = loginService.obtenerPorEmailPassword(email, password);
			return usuarioLogin;
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		} catch (Exception ex) {
			throw new DataIntegrityViolationException("Error verifique los datos ingresados: ");
		}
	}

	/**
	 * API REST para obtener un User por email y password.
	 *
	 * @param user the user
	 * @return the user por email password
	 */
	@CrossOrigin
	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<UserView> getUserPorEmailPassword(@Validated @RequestBody Usuario user) {
		try {
			
			UserView userv= loginService.obtenerPorEmailPassword(user.getEmail(), user.getPassword()).getData();
			
			return ResponseEntity.ok().body(userv);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException("Verifique los datos ingresados: ");
		} catch (Exception ex) {
			throw new DataIntegrityViolationException("Error verifique los datos ingresados: ");
		}
	}

}
