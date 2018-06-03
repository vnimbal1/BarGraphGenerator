package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class serves as Model class holding the data for each year
 * 
 * @author Viraj
 *
 */
public class Values {
	String heading;
	Map<String, Integer> map;

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	public Values() {
		map = new HashMap<String, Integer>();
	}

}
