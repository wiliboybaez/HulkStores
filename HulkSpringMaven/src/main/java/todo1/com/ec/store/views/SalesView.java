/**
 * 
 */
package todo1.com.ec.store.views;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author WINNER
 *
 */
public class SalesView implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2325932834997074967L;
	private String nroDocumento;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fecha;
	private BigDecimal subtotal;
	private BigDecimal iva;
	private BigDecimal total;
	private String user;

	public SalesView(String nroDocumento, Date fecha, BigDecimal subtotal, BigDecimal iva, BigDecimal total) {
		super();
		this.nroDocumento = nroDocumento;
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.iva = iva;
		this.total = total;
	}

	/**
	 * @return the nroDocumento
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}

	/**
	 * @param nroDocumento the nroDocumento to set
	 */
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the subtotal
	 */
	public BigDecimal getSubtotal() {
		return subtotal;
	}

	/**
	 * @param subtotal the subtotal to set
	 */
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	/**
	 * @return the iva
	 */
	public BigDecimal getIva() {
		return iva;
	}

	/**
	 * @param iva the iva to set
	 */
	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	/**
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * @return the usuario
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUser(String usuario) {
		this.user = usuario;
	}

}
