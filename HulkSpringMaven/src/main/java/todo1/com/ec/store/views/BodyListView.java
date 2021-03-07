/**
 * 
 */
package todo1.com.ec.store.views;

import java.io.Serializable;
import java.util.List;

/**
 * @author WINNER
 *
 */
public class BodyListView<T> implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4045176169453215010L;
	private List<T> data;

	public BodyListView(List<T> data) {
		super();
		this.data = data;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}

