package csv;

import java.util.HashMap;
import java.util.Map;

/**
 * An entity represents a row in a CSV file
 * @author alex
 *
 */
public class Entity {
	private final String primaryKey;
	private final Map<String, String> attributes;
	
	public Map<String, String> getAttributes() {
		return attributes;
	}

	public Entity(String primaryKey) {
		this.primaryKey = primaryKey;
		this.attributes = new HashMap<>();
	}
	
	
	
	public String getAttribute(String attribute) {
		return this.attributes.get(attribute);
	}
	
	public void setAttribute(String attribute, String value) {
		this.attributes.put(attribute, value);
	}

	public String getPrimaryKey() {
		return primaryKey;
	}
}
