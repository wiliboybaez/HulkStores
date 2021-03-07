/**
 * 
 */
package todo1.com.ec.store.views;

import java.io.Serializable;

/**
 * @author WINNER
 *
 */
public class ErrorView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4123176125269032208L;
	private String mensaje;
	private int codigo;

	public ErrorView(String mensaje, int codigo) {
		super();
		this.mensaje = mensaje;
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}
