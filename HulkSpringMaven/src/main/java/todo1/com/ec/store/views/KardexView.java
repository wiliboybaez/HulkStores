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

public class KardexView implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2253287852387569189L;
	private Integer id;
	private Integer cantidad;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fecha;
	private String producto;
	private BigDecimal total;
	private String user;
	private BigDecimal precio;

	public KardexView(Integer id, Integer cantidad, Date fecha, BigDecimal total) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.total = total;
	}

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
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

	/**
	 * @return the precio
	 */
	public BigDecimal getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
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

}

