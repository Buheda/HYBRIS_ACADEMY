package core.util;

public abstract class StringUtil {
	
	public static boolean isEmptyString(String str) {
		return str == null || str.isBlank();
	} 
}
