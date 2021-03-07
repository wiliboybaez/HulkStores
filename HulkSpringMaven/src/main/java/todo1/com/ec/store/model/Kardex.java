/**
 * 
 */
package todo1.com.ec.store.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * @author WINNER
 *
 */
@Entity
@Table(name = "kardex")
public class Kardex implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3000870499031673857L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "product")
	private Product product;

	private Integer cantidad;

	private BigDecimal total;

	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	@Column(name = "fecha_ingreso")
	private Date fechaKardex;

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
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
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
	 * @return the user
	 */
	public Usuario getUser() {
		return usuario;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Usuario user) {
		this.usuario = user;
	}

	/**
	 * @return the fechaKardex
	 */
	public Date getFechaKardex() {
		return fechaKardex;
	}

	/**
	 * @param fechaKardex the fechaKardex to set
	 */
	public void setFechaKardex(Date fechaKardex) {
		this.fechaKardex = fechaKardex;
	}

}
