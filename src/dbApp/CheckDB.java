package dbApp;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import java.sql.ResultSetMetaData;

import db.DBController;


public class CheckDB  {	

	public static void main(String[] args) throws Exception {
		DBController.getDB().createDatabase();
/*
				try {
			

		} catch(Exception e){ 
			System.out.err(e);
		}*/
	}
}




