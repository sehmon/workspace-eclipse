package com.sehmon.whatamieating;

import java.util.List;

public interface NutrientProvider {

	public List<Nutrient> getNutrients();
	
	public List<Additive> getAdditives();
	
	public Food getFood();
	
	public String title();
}
