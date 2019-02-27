package cl.crojas.blog.utils.model.datatable;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import cl.crojas.blog.utils.model.ModelBase;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataTablesRequest extends ModelBase {

	private static final long serialVersionUID = 8495132296895946975L;

	private int draw;

	private int start;

	private int length;

	private Search search;

	private Map<String, String> aditionalFilters;

	@JsonProperty("order")
	private List<Order> orders;

	private List<Column> columns;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	/**
	 * Método que permite imprimir como String el Objeto correspondiente a esta
	 * Clase La Anotación @Override indica que este método es sobreescrito de la
	 * clase que hereda o interfaz que implementa
	 * 
	 * @return String Objeto correspondiente a la Versión String del Objeto
	 * @author cristian.palavecinos
	 * 
	 */
	@Override
	public String toString() {
		return "DataTablesRequest [draw=" + draw + ", start=" + start + ", length=" + length + ", search=" + search
				+ ", orders=" + orders + ", columns=" + columns + "]";
	}

	public Map<String, String> getAditionalFilters() {
		return aditionalFilters;
	}

	public void setAditionalFilters(Map<String, String> aditionalFilters) {
		this.aditionalFilters = aditionalFilters;
	}

}
