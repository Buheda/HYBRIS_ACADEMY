package dbApp.creator;

import core.db.DBConnection;
import core.db.DBType;

public class Factory {	
	static DB dbCreator = null;
	
	public static DB getDBCreator() throws Exception {
		if (null != dbCreator) 
			return dbCreator;
				
		if (DBType.HSQLDB.equals(DBConnection.getDBType()))
			dbCreator = new DB_HSQLDB();
		else if(DBType.MySQL.equals(DBConnection.getDBType()))
			dbCreator = new DB_MySql();
		else 
			throw new IllegalArgumentException("Incorrect database type");	    

		return dbCreator;
	};
}
