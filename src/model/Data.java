package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {
	ArrayList<String> labels;
	Map<String, Color> colorsforLabels;
	ArrayList<Values> values;;
	HashMap<Integer, Values> map;
	int maxVal;
	int minVal;

	private static Data data = null;

	private Data() {
		labels = new ArrayList<>();
		colorsforLabels = new HashMap<>();
		values = new ArrayList<>();
		map = new HashMap<>();
		maxVal = 0;
		minVal = 0;
	}

	public ArrayList<String> getLabels() {
		return labels;
	}

	public void setLabels(ArrayList<String> labels) {
		this.labels = labels;
	}

	public Map<String, Color> getColorsforLabels() {
		return colorsforLabels;
	}

	public void setColorsforLabels(Map<String, Color> colorsforLabels) {
		this.colorsforLabels = colorsforLabels;
	}

	public ArrayList<Values> getValues() {
		return values;
	}

	public void setValues(ArrayList<Values> values) {
		this.values = values;
	}

	public HashMap<Integer, Values> getMap() {
		return map;
	}

	public void setMap(HashMap<Integer, Values> map) {
		this.map = map;
	}

	public int getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(int maxVal) {
		this.maxVal = maxVal;
	}

	public int getMinVal() {
		return minVal;
	}

	public void setMinVal(int minVal) {
		this.minVal = minVal;
	}

	public static Data getInstance() {
		if (data == null) {
			data = new Data();
		}
		return data;
	}

}
