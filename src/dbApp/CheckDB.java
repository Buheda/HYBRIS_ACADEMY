package dbApp;

import db.DBController;

public class CheckDB  {	

	public static void main(String[] args) throws Exception {
		DBController.getDB().createDatabase();
		DBController.getConnection().close();
	}
}




