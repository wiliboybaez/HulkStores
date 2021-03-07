/**
 * 
 */
package todo1.com.ec.store.views;

import java.io.Serializable;

/**
 * @author WINNER
 *
 */
public class UserView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5140208371689642930L;
	private Integer id;
	private String email;
	private String rol;
	private String token;

	public UserView(Integer id,String email) {
		super();
		this.id = id;
		this.email = email;
		
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
}
