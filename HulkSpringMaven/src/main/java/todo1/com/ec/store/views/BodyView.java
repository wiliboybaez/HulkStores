/**
 * 
 */
package todo1.com.ec.store.views;

import java.io.Serializable;

/**
 * @author WINNER
 *
 */
public class BodyView <T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3061986908466898666L;
	
	private T data;

	public BodyView(T data) {
		super();
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
