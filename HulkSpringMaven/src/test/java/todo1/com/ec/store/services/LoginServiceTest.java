/**
 * 
 */
package todo1.com.ec.store.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import todo1.com.ec.store.model.Role;
import todo1.com.ec.store.model.Usuario;
import todo1.com.ec.store.repository.RoleRepository;
import todo1.com.ec.store.repository.UserRepository;
import todo1.com.ec.store.views.BodyView;
import todo1.com.ec.store.views.UserView;


/**
 * @author WINNER
 *
 */
@RunWith(SpringRunner.class)
public class LoginServiceTest {

	@Autowired
	private LoginService loginService;
	@MockBean
	private UserRepository usuarioRepository;
	@MockBean
	private RoleRepository roleRepository;
	@Mock
	private Usuario user;

	@TestConfiguration
	static class LoginServiceImplTestContextConfiguration {
		@Bean
		public LoginService employeeService() {
			return new LoginService();
		}
	}

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		loginService = new LoginService(usuarioRepository, roleRepository);
		Mockito.when(roleRepository.findById(findRol().getId())).thenReturn(Optional.of(findRol()));
	}

	private Role findRol() {
		Role rol = new Role();
		rol.setId(1);
		rol.setRole("CLIENTE");
		rol.setDescription("CLIENTE DE VENTAS");
		return rol;
	}

	private Usuario createUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setEmail("cliente@todo1.com");
		usuario.setRole(findRol());
		return usuario;
	}

	private Usuario findUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(2);
		usuario.setEmail("venta@todo1.com");
		usuario.setRole(findRol());
		return usuario;
	}

	@Test
	public void whenValidName_thenUsuarioShouldBeFound() {
		String email = "venta@todo1.com";
		user = createUsuario();
		Usuario usuario = findUsuario();
		when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(usuario);
		when(usuarioRepository.save(user)).thenReturn(usuario);
		BodyView<UserView> savedUsuario = loginService.crearUser(user);
		// Assert
		assertThat(savedUsuario.getData().getEmail(), is(equalTo(email)));
	}

	@Test
	public void whenValidName_thenusuarioShouldNotFound() {
		user = createUsuario();
		when(usuarioRepository.findByEmail(user.getEmail())).thenReturn(user);
		when(usuarioRepository.save(user)).thenReturn(user);
		BodyView<UserView> savedUsuario = loginService.crearUser(user);
		// Assert
		assertThat(savedUsuario, is(equalTo(null)));
	}

	@Test
	public void whenValidList_thenUsuarioShouldBeFound() {
		List<Usuario> lista = new ArrayList<>();
		lista.add(createUsuario());
		when(usuarioRepository.findAll()).thenReturn(lista);
		List<Usuario> getUses = loginService.obtenerUsers();
		// Assert
		assertThat(getUses.size(), is(equalTo(1)));
	}

	@Test
	public void shouldReturnusuario_whenGetProductByCorroPasswordIsCalled() {
		String mail = "cliente@todo1.com";
		user = createUsuario();
		when(usuarioRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(user);
		BodyView<UserView> savedUsuario = loginService.obtenerPorEmailPassword(user.getEmail(),
				user.getPassword());
		// Assert
		assertThat(savedUsuario.getData().getEmail(), is(equalTo(mail)));
	}
}
