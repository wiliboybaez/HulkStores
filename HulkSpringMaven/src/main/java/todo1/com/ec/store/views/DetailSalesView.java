/**
 * 
 */
package todo1.com.ec.store.views;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author WINNER
 *
 */

public class DetailSalesView implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6229847319354995810L;
	private Integer cantidad;
	private String producto;
	private BigDecimal subtotal;
	private BigDecimal total;

	public DetailSalesView(Integer cantidad, BigDecimal subtotal, BigDecimal total) {
		super();
		this.cantidad = cantidad;
		this.subtotal = subtotal;
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

}
