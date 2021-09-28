package core.persistent;


public enum CommandsErrors {
	INVALID_PARAMETERS,
	INVALID_KEY,
	INVALID_VALUE,
	INCORRECT_PASSWORD;

	private static CommandsErrors currentError = null;
	public static CommandsErrors getLastError() {
		return currentError;
	}
	
	public static void showCommandsErrorsMessage(CommandsErrors error) {
		currentError = error; 
		switch (error) {
			case INVALID_PARAMETERS:
				System.err.println("ERROR: Invalid or empty list of parameters. See help");
				break;
			case INVALID_KEY:
				System.err.println("ERROR: Missing parameter. See help");
				break;
			case INVALID_VALUE:
				System.err.println("ERROR: Incorrect parameter value. See help");
				break;
			case INCORRECT_PASSWORD:
				System.err.println("ERROR: Incorrect password for removing all products");
				break;
			default:
				break;
		}
	}
	
	public static void showCommandsErrorsMessage(CommandsErrors error, String paramName) {
		currentError = error; 
		switch (error) {
			case INVALID_PARAMETERS:
				showCommandsErrorsMessage(error);
				break;
			case INVALID_KEY:
				System.err.println("ERROR: Missing parameter '"+paramName+"'. See help");
				break;
			case INVALID_VALUE:
				System.err.println("ERROR: Empty or missing value for parameter '"+paramName+"'. See help");
				break;
			case INCORRECT_PASSWORD:
				showCommandsErrorsMessage(error);
			default:
				break;
		}
	}

}
