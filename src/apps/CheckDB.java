package apps;

import core.db.DBConnection;
import dbApp.tablesManager.Factory;

public class CheckDB  {	

	public static void main(String[] args) throws Exception {
		Factory.getDBManager().createTables();
		DBConnection.getConnection().close();
	}
}




