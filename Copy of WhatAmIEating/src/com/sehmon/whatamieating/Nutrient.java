package com.sehmon.whatamieating;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Nutrient implements Serializable{	
	
private static final long serialVersionUID = 1L;
private String name;
private double value;

public static Nutrient fromJson(JSONObject jsonObject){
	Nutrient n = new Nutrient();
	try {
		n.name = jsonObject.getString("nutrient_name");
		Double nutrientVal = 0.0;
		try {
			nutrientVal = Double.parseDouble(jsonObject.getString("nutrient_value"));
		} catch (NumberFormatException e) {
			// meh... it was probably empty
		}
		n.value = nutrientVal.doubleValue();
	} catch (JSONException e1) { }
	
	return n;
}
public void setName(String name) {
	this.name = name;
}
public void setValue(double value) {
	this.value = value;
}
public String getName() {
	return name;
}
public double getValue() {
	return value;	
}

}
