package dbApp;

import db.DBConnection;
import db.creator.Factory;

public class CheckDB  {	

	public static void main(String[] args) throws Exception {
		Factory.getDBCreator().createTables();
		DBConnection.getConnection().close();
	}
}




