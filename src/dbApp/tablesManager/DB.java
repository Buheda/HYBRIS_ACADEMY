package dbApp.tablesManager;

public interface DB {
	public boolean createTables() throws Exception;
	public boolean dropTables() throws Exception;
}
