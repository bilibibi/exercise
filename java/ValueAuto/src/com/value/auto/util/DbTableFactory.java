package com.value.auto.util;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.value.auto.metadata.Field;
import com.value.auto.metadata.Table;
import com.value.auto.provider.GlobalPropertiesProvider;

public class DbTableFactory {
	/**
	 * Logger for this class
	 */
	private static final Log _log = LogFactory.getLog(DbTableFactory.class);

//	Properties props;
	public String catalog;
	public String schema;
	
	private Connection connection;
	private static DbTableFactory instance = new DbTableFactory();
	
	private DbTableFactory() {
		init();
	}

	private void init() {
		
		this.schema = GlobalPropertiesProvider.getProperty("jdbc_schema","");
		if("".equals(schema.trim())) {
			this.schema = null;
		}
		this.catalog = GlobalPropertiesProvider.getProperty("jdbc_catalog","");
		if("".equals(catalog.trim())) {
			this.catalog = null;
		}
		
		System.out.println("jdbc.schema="+this.schema+" jdbc.catalog="+this.catalog);
		try {
			Class.forName(GlobalPropertiesProvider.getProperty("jdbc_driver"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("not found jdbc driver class",e);
		}
	}
	
	public static DbTableFactory getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws SQLException {
		if(connection == null || connection.isClosed()) {
			String url = GlobalPropertiesProvider.getProperty("jdbc_url");
			String userName = GlobalPropertiesProvider.getProperty("jdbc_user");
			String password = GlobalPropertiesProvider.getProperty("jdbc_password");
			connection = DriverManager.getConnection(url,userName,password);
		}
		return connection;
	}

	public List<Table> getAllTables() throws Exception {
		Connection conn = getConnection();
		return getAllTables(conn);
	}

	private List<Table> getAllTables(Connection conn) throws SQLException {
		DatabaseMetaData dbMetaData = conn.getMetaData();
		ResultSet rs = dbMetaData.getTables(catalog, schema, null, null);
		List<Table> tables = new ArrayList<Table>();
		while(rs.next()) {
			Table table = createTable(conn, rs);
			tables.add(table);
		}
		return tables;
	}

	public Table getTable(String sqlTableName) throws Exception {
		return getTable(sqlTableName, null);
	}
	
	public Table getTable(String sqlTableName, List<String> fieldNameList) throws Exception {
		Connection conn = getConnection();
		DatabaseMetaData dbMetaData = conn.getMetaData();
		//Upper table name 
		if(sqlTableName != null){
		    sqlTableName = sqlTableName.toUpperCase();
		}
		ResultSet rs = dbMetaData.getTables(catalog, schema, sqlTableName, null);
		while(rs.next()) {
			Table table = createTable(conn, rs, fieldNameList);
			return table;
		}
		throw new RuntimeException("not found table with give name:"+sqlTableName);
	}
	
	private Table createTable(Connection conn, ResultSet rs) throws SQLException {
		return createTable(conn, rs, null);
	}
	
	private Table createTable(Connection conn, ResultSet rs, List<String> fieldNameList) throws SQLException {
		String realTableName = null;
		try {
			realTableName = rs.getString("TABLE_NAME");
			Table table = new Table();
			table.setTableName(realTableName);			
			retrieveTableFields(table, fieldNameList);
			return table;
		}catch(SQLException e) {
			throw new RuntimeException("create table object error,tableName:"+realTableName,e);
		}
	}
	
	private void retrieveTableFields(Table table, List<String> fieldNameList) throws SQLException 
	{
	      _log.debug("-------setColumns(" + table.getTableName() + ")");

	      List primaryKeys = getTablePrimaryKeys(table);
	      table.setPrimaryKeyColumns(primaryKeys);
	      List<Field> columns = getTableColumns(table, fieldNameList,primaryKeys);

	      table.setFields(columns);

	      // In case none of the columns were primary keys, issue a warning.
	      if (primaryKeys.size() == 0) {
	         _log.warn("WARNING: The JDBC driver didn't report any primary key columns in " + table.getTableName());
	      }
	}

	/**
	 * 
	 * @param table
	 * @param fieldNameList if empty, get all fields of the table, else get the specified fields.
	 * @param primaryKeys
	 * @param indices
	 * @param uniqueIndices
	 * @param uniqueColumns
	 * @return
	 * @throws SQLException
	 */
	private List<Field> getTableColumns(Table table, List<String> fieldNameList,List primaryKeys) throws SQLException {
		// get the columns
	    List<Field> columns = new LinkedList<Field>();
	    
	    if (fieldNameList == null || fieldNameList.size() <= 0) {
	    	// get all fields from table
		    ResultSet columnRs = getColumnsResultSet(table);
		    while (columnRs.next()) {
		    	Field field = getField(columnRs, table, primaryKeys);
		    	columns.add(field);
		    }
		    columnRs.close();
	    }
	    else {
	    	// get specified fields from table
	    	int size = fieldNameList.size();
	    	for (int i = 0; i < size; i++) {
	    		String fieldName = fieldNameList.get(i);
	    		ResultSet columnRs = getColumnsResultSet(table, fieldName);
	    		if (columnRs.next()) {
		    		Field field = getField(columnRs, table, primaryKeys);
		    		if (field == null) {
		    			_log.error("getTableColumns - fail get field with field name " + fieldName);
		    			continue;
		    		}
		    		columns.add(field);
	    		}
	    		columnRs.close();
	    	}
	    	
	    }
	    
		return columns;
	}

	private ResultSet getColumnsResultSet(Table table) throws SQLException {
		return getColumnsResultSet(table, null);
	}
	
	private ResultSet getColumnsResultSet(Table table, String fieldName) throws SQLException {
		ResultSet columnRs = getMetaData().getColumns(catalog, schema, table.getTableName().toUpperCase(), fieldName);
		return columnRs;
	}

	private List getTablePrimaryKeys(Table table) throws SQLException {
		// get the primary keys
	      List primaryKeys = new LinkedList();
	      ResultSet primaryKeyRs = getMetaData().getPrimaryKeys(catalog, schema, table.getTableName().toUpperCase());
	      while (primaryKeyRs.next()) {
	         String columnName = primaryKeyRs.getString("COLUMN_NAME");
	         _log.debug("primary key:" + columnName);
	         primaryKeys.add(columnName);
	      }
	      primaryKeyRs.close();
		return primaryKeys;
	}
	
	private Field getField(ResultSet columnRs, Table table,List primaryKeys) throws SQLException
	{
		if (columnRs == null) {
			return null;
		}
		int sqlType = columnRs.getInt("DATA_TYPE");
		String sqlTypeName = columnRs.getString("TYPE_NAME");
        String columnName = columnRs.getString("COLUMN_NAME");
        String columnDefaultValue = columnRs.getString("COLUMN_DEF");
        String columnComment = columnRs.getString("REMARKS");
        // if columnNoNulls or columnNullableUnknown assume "not nullable"
        boolean isNullable = (DatabaseMetaData.columnNullable == columnRs.getInt("NULLABLE"));
        int size = columnRs.getInt("COLUMN_SIZE");
        boolean isPk = primaryKeys.contains(columnName);
        int decimalDigits = columnRs.getInt("DECIMAL_DIGITS");
        Field field = new Field(
              sqlType,
              sqlTypeName,
              columnName,
              size,
              isPk,
              isNullable,
              columnDefaultValue,
              decimalDigits,
              columnComment);
        return field;
	}
	private DatabaseMetaData getMetaData() throws SQLException {
		return getConnection().getMetaData();
	}
	
}
