package cl.crojas.blog.utils.model.datatable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Christian Rojas N.
 *
 * @param <T>
 */
public class DataTablesResponse<T> implements Serializable {

	private static final long serialVersionUID = 1225171308431519092L;

	private int draw;

	private long recordsTotal;

	private long recordsFiltered;

	private String error;

	private Map<String, String> errors;

	private List<T> data = new ArrayList<>();

	public DataTablesResponse() {
		super();
	}

	public DataTablesResponse(int draw, long recordsTotal, long recordsFiltered, String error, List<T> data) {

		super();

		this.draw = draw;

		this.recordsTotal = recordsTotal;

		this.recordsFiltered = recordsFiltered;

		this.error = error;

		this.data = data;

	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
