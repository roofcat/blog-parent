package cl.crojas.blog.utils.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class PreviewConfigModel implements Serializable {

	private static final long serialVersionUID = 8499220021810759402L;

	private static final String OBJECT = "object";

	private static final Map<String, String> mapTypes = new HashMap<>();

	private long arcId;

	private float size;

	private String type;

	private String caption;

	private String filetype;

	private String key;

	private String url;

	private String wccId;

	static {
		String image = "image";

		mapTypes.put("jpg", image);
		mapTypes.put("png", image);
		mapTypes.put("gif", image);
		mapTypes.put("pdf", "pdf");
		mapTypes.put("doc", OBJECT);
		mapTypes.put("docx", OBJECT);

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getArcId() {
		return arcId;
	}

	public void setArcId(long arcId) {
		this.arcId = arcId;
	}

	public String getJson() {

		String json = null;

		StringBuilder sb = new StringBuilder();

		sb.append("{");
		sb.append("\"type\" : ");

		if (null != this.type) {

			sb.append("\"");
			sb.append(this.type);
			sb.append("\"");

		}

		sb.append(", \"filetype\" : ");

		if (null != this.filetype) {

			sb.append("\"");
			sb.append(this.filetype.trim());
			sb.append("\"");

		}

		sb.append(", \"size\" : ");
		sb.append(String.valueOf(this.size));
		sb.append(", \"caption\" : ");

		if (null != this.caption) {

			sb.append("\"");
			sb.append(this.caption.trim());
			sb.append("\"");

		}

		sb.append(", \"url\" : ");

		if (null != this.url) {

			sb.append("\"");
			sb.append(this.url.trim());
			sb.append("\"");

		}

		sb.append(", \"key\" : ");

		if (null != this.key) {

			sb.append("\"");
			sb.append(this.key.trim());
			sb.append("\"");

		}

		sb.append(", \"wccId\" : ");

		if (null != this.wccId) {

			sb.append("\"");
			sb.append(this.wccId.trim());
			sb.append("\"");

		}

		sb.append(", \"arcId\" : ");
		sb.append(String.valueOf(this.arcId));
		sb.append("}");

		json = sb.toString();

		return json;

	}

	public void configureType(String ext) {
		this.setType(mapTypes.get(ext) == null ? OBJECT : mapTypes.get(ext));
	}

	public String getWccId() {
		return wccId;
	}

	public void setWccId(String wccId) {
		this.wccId = wccId;
	}

}
