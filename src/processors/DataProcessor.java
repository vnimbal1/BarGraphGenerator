package processors;

import java.awt.Color;
import java.io.IOException;

import model.Data;
import model.Values;
import util.Utility;

/**
 * The Processor class provides methods to perform computation on given input
 * data
 * 
 * @author Viraj
 */
public class DataProcessor {

	/**
	 * Method to generate random colors for each label
	 * 
	 * @throws IOException
	 */
	public void populateColorForLables() throws IOException {
		Data data = Data.getInstance();
		for (String s : data.getLabels()) {
			int max = Utility.getProperty("color_max");
			int min = Utility.getProperty("color_min");
			data.getColorsforLabels().put(s, new Color((int) (Math.random() * (max - min)) + min,
					(int) (Math.random() * (max - min)) + min, (int) (Math.random() * (max - min)) + min));
		}
	}

	/**
	 * This method finds the span between input values Used in order to format
	 * display panel properly
	 */
	public void findValueRange() {
		Data data = Data.getInstance();
		for (Values value : data.getValues()) {
			for (String label : value.getMap().keySet()) {
				data.setMaxVal(value.getMap().get(label) > data.getMaxVal() ? value.getMap().get(label) : data.getMaxVal());
				data.setMinVal(value.getMap().get(label) < data.getMinVal() ? value.getMap().get(label) : data.getMinVal());
			}
		}
	}

	/**
	 * Method to put values from file into data structure
	 * 
	 * @param input
	 */
	public static void populateData(String input) {
		Data data = Data.getInstance();
		String temp[] = input.split(",");
		int headLabel = Integer.parseInt(temp[0]);

		// check if row is duplicate and already added
		if (data.getMap().containsKey(headLabel)) {
			Values value = data.getMap().get(headLabel);
			for (int i = 1; i < temp.length; i++) {
				String label = data.getLabels().get(i);
				int currentValue = Integer.parseInt(temp[i]);
				value.getMap().put(label, value.getMap().get(label) + currentValue);
			}
		} else { // label does not exist
			Values value = new Values();
			value.setHeading(temp[0]);
			for (int i = 1; i < temp.length; i++) {
				if (temp[i] != null && !temp[i].isEmpty()) {
					value.getMap().put(data.getLabels().get(i), Integer.parseInt(temp[i]));
				}
			}
			data.getMap().put(Integer.parseInt(temp[0]), value);
			data.getValues().add(value);
		}

	}

}
