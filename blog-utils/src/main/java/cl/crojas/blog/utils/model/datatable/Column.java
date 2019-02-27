package cl.crojas.blog.utils.model.datatable;

import java.io.Serializable;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class Column implements Serializable {

	private static final long serialVersionUID = 7532944054313981077L;

	private String data;

	private String name;

	private boolean searchable;

	private boolean orderable;

	private Search search;

	private boolean regex;

	public Column() {
		super();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSearchable() {
		return searchable;
	}

	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

	public boolean isOrderable() {
		return orderable;
	}

	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public boolean isRegex() {
		return regex;
	}

	public void setRegex(boolean regex) {
		this.regex = regex;
	}

}
