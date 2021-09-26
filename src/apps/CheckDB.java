package apps;

import core.db.DBConnection;
import dbApp.creator.Factory;

public class CheckDB  {	

	public static void main(String[] args) throws Exception {
		Factory.getDBCreator().createTables();
		DBConnection.getConnection().close();
	}
}




