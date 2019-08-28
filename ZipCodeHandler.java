package com.nisum.zipcode;

import java.util.LinkedList;
import java.util.List;

public class ZipCodeHandler {

	private String zipCodes;

	public ZipCodeHandler(String zipcodes) {
		this.zipCodes = zipcodes;
	}

	public List<Zipcode> processInput() {
		String[] zipCodeArr = zipCodes.split(" ");

		List<Zipcode> zipcodesList = new LinkedList<Zipcode>();
		for (int i = 0; i < zipCodeArr.length; i++) {

			String[] zipArray = zipCodeArr[i].replaceAll("\\[|\\]", "").split(",");

			if (zipArray.length != 2)
				throw new IllegalArgumentException(zipArray[0] + "ZipCode range should have proper bound values");
			int lower = Integer.parseInt(zipArray[0]);
			int upper = Integer.parseInt(zipArray[1]);
			Zipcode zipcode = null;
			if (validateZipcodeRange(lower, upper) == true)
				zipcode = new Zipcode(lower, upper);

			zipcodesList.add(zipcode);
		}
		return zipcodesList;
	}

	public boolean validateZipcodeRange(int lowerLimit, int upperLimit) {
		if (compareZipcodeRange(lowerLimit, upperLimit) == false)
			throw new IllegalArgumentException(
					lowerLimit + " " + upperLimit + ":  " + "Zipcode lower limit should be less than upper limit");
		if (!checkZipCodeLength(lowerLimit) && !checkZipCodeLength(upperLimit))
			throw new IllegalArgumentException(lowerLimit + " " + upperLimit + ": " + "Zipcode should have 5 digits"); 
		
		return true;
	}

	public boolean checkZipCodeLength(int zipcode) {
		//checking length by converting it to string
		if (String.valueOf(zipcode).length() != 5)
			return false;
		return true;
	}

	public boolean compareZipcodeRange(int lowerLimit, int upperLimit) { 
		if (lowerLimit > upperLimit)
			return false;
		return true;
	}

}
