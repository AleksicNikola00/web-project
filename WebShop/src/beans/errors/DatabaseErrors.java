package beans.errors;

import java.util.*;

public class DatabaseErrors {
	public static String NO_ERROR = "";
	public static String ALREADY_EXISTS = "The object with the same id already exists";
	public static String NOT_FOUND = "The object with sent id does not exist in the database";
	public static String ALREADY_DELETED = "The object with sent id is already deleted";
	
	//Food for restaurant
	public static String NO_RESTAURANT_FOUND = "No restaurant found for requested id";
	
	//Order cancelation
	public static String WRONG_ORDER_STATUS = "The order isn't in the pending state";
	
	//Success options
	public static String OK = "OK";
	public static String PARTIAL = "PARTIAL";
	public static String FAILED = "FAILED";
}