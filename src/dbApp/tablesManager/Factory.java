package dbApp.tablesManager;

import core.db.DBConnection;
import core.db.DBType;

public class Factory {	
	static DB dbManager = null;
	
	public static DB getDBManager() throws Exception {
		if (null != dbManager) 
			return dbManager;
				
		if (DBType.HSQLDB.equals(DBConnection.getDBType()))
			dbManager = new DB_HSQLDB();
		else if(DBType.MySQL.equals(DBConnection.getDBType()))
			dbManager = new DB_MySql();
		else 
			throw new IllegalArgumentException("Incorrect database type");	    

		return dbManager;
	};
}
