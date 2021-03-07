/**
 * 
 */
package todo1.com.ec.store.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author WINNER
 *
 */
@Entity
@Table(name="sales")
public class Sales implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 295777814420111662L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nro_documento")
	private String nroDocumento;

	@Column(name = "fecha_venta")
	private Date fechaSales;

	private BigDecimal subtotal;

	private BigDecimal iva;

	private BigDecimal total;

	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetailSales> detailList;

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
	 * @return the fechaSales
	 */
	public Date getFechaSales() {
		return fechaSales;
	}

	/**
	 * @param fechaSales the fechaSales to set
	 */
	public void setFechaSales(Date fechaSales) {
		this.fechaSales = fechaSales;
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
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the user to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the detailList
	 */
	public List<DetailSales> getDetailList() {
		return detailList;
	}

	/**
	 * @param detailList the detailList to set
	 */
	public void setDetailList(List<DetailSales> detailList) {
		this.detailList = detailList;
	}

}
