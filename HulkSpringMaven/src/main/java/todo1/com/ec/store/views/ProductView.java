/**
 * 
 */
package todo1.com.ec.store.views;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author WINNER
 *
 */
public class ProductView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6233645630848956713L;
	
	private Integer id;
	private String name;
	private String description;
	private Date date;
	private Integer stock;
	private String url;
	private String category;
	private BigDecimal price;

	public ProductView(Integer id, String nombre, String descripcion, Date fecha, Integer cantidad, String url,
			BigDecimal precio) {
		super();
		this.id = id;
		this.name = nombre;
		this.description = descripcion;
		this.date = fecha;
		this.stock = cantidad;
		this.url = url;
		this.price = precio;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the stock
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	}
