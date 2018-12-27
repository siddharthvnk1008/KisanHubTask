package com.siddharth.task.kissanhub.network;

/**
 * Created by Siddharth Vanakudari on 27/12/18.
 */
public interface URLConstants {

	String MINIMUM_TEMP = "Tmin-";
	String MAXIMUM_TEMP = "Tmax-";
	String RAINFALL_TEMP= "Rainfall-";

	String UK="UK.json";
	String England = "England.json";
	String Scotland = "Scotland.json";
	String Wales = "Wales.json";

	String UK_MINIMUM_TEMP = MINIMUM_TEMP+UK;
	String UK_MAXIMUM_TEMP = MAXIMUM_TEMP+UK;
	String UK_RAINFALL_TEMP= RAINFALL_TEMP+UK;

	String England_MINIMUM_TEMP = MINIMUM_TEMP+England;
	String England_MAXIMUM_TEMP = MAXIMUM_TEMP+England;
	String England_RAINFALL_TEMP= RAINFALL_TEMP+England;

	String Scotland_MINIMUM_TEMP = MINIMUM_TEMP+Scotland;
	String Scotland_MAXIMUM_TEMP = MAXIMUM_TEMP+Scotland;
	String Scotland_RAINFALL_TEMP= RAINFALL_TEMP+Scotland;

	String Wales_MINIMUM_TEMP = MINIMUM_TEMP+Wales;
	String Wales_MAXIMUM_TEMP = MAXIMUM_TEMP+Wales;
	String Wales_RAINFALL_TEMP= RAINFALL_TEMP+Wales;
}
