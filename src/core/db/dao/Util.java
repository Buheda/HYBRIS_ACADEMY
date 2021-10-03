package core.db.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Util {
	
	public static List<HashMap<Object, Object>> parseResultSet(ResultSet rs)  throws Exception {
		ResultSetMetaData meta;
		HashMap<Object, Object> rowValues;
		int columnsCount;
		List<HashMap<Object, Object>> result = new ArrayList<HashMap<Object, Object>>();
		
		while (rs.next()) {
			meta = rs.getMetaData();
			columnsCount = meta.getColumnCount();
			rowValues = new HashMap<Object, Object>();
			rowValues.put("id", rs.getObject("id"));
	        for (int i = 1; i <= columnsCount; i++) {
	        	rowValues.put(meta.getColumnName(i).toLowerCase(), rs.getObject(i));
	        }
	        result.add(rowValues);
	    }
		
		return result;
	}
}
