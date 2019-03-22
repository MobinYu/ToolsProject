package com.study.reflact;

public class City {
	
	private String cityName;

	@CodeToName(type = "std_canton", field = "cantonName")
	private String cantonCode;
	
	private String cantonName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCantonCode() {
		return cantonCode;
	}

	public void setCantonCode(String cantonCode) {
		this.cantonCode = cantonCode;
	}

	public String getCantonName() {
		return cantonName;
	}

	public void setCantonName(String cantonName) {
		this.cantonName = cantonName;
	}
	
}
