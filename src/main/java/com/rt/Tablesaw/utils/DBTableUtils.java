package com.rt.Tablesaw.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import tech.tablesaw.api.ShortColumn;
import tech.tablesaw.api.Table;

public class DBTableUtils {
	
	/** Class Logger */
	private static final Logger LOGGER = Logger.getLogger(DBTableUtils.class.getName());

	public Connection connectToDB(String dbName){
		String DB_URL = "jdbc:mysql://localhost:3306/" + dbName;
		try {
			Connection conn = DriverManager.getConnection(DB_URL, "root", "");
			return conn;
		} catch (SQLException e) {
			LOGGER.severe("Error while connecting to DB");
			return null;
		}
	}
	
	public Table getTableFromDB(Connection conn, String tableName) throws SQLException {
		String sql = "SELECT * FROM " + tableName;
		try (Statement stmt = conn.createStatement()) {
			try (ResultSet results = stmt.executeQuery(sql)) {
				return Table.create(results, tableName);
			}
		}
	}
	
	private void generateRandomRecords(Connection conn, int totalRecords) {
		String query = "insert into bush_approval (date, approval, who) values (?, ?, ?)";
		
		try {
			for(int cnt=0 ; cnt<totalRecords ; cnt++){
				PreparedStatement preparedStmt;
				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setDate(1, Generator.getRandomDate());
				preparedStmt.setInt(2, Generator.getRandomApproval());
				preparedStmt.setString(3, Generator.getRandomWho());
				
				preparedStmt.execute();
			}
		} catch (SQLException e) {
			LOGGER.severe("Error executing statement");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.severe("Error closing connection");
				e.printStackTrace();
			}
		}
		
	}
	
	public void addRecordsToDB(Connection conn, int totalRecords){
		long start = System.currentTimeMillis();
		
		generateRandomRecords(conn, totalRecords);
		
		long end = System.currentTimeMillis();
		
		System.out.println("Record Generation Time Elapsed: " + Generator.formatElapsedTime(start, end));
	}
	
	public void getTableBasicInfo(Table table){
		System.out.println("---------- TABLE Basic Info ----------");
		System.out.println("Name:  		\t"			+ table.name());
		//System.out.println("Shape: 		\t" 		+ table.shape());
		//System.out.println("ColumnCount:	\t" 	+ table.columnCount());
		//System.out.println("Print: \n" 		+ table.print());
		//System.out.println("Print HTML: \n" + table.printHtml());
		System.out.println("Structure: \n" + table.structure());
		System.out.println("Summary: \n" + table.summary());
		//System.out.println("Column Names [LIST]: \n" + table.columnNames());
		//System.out.println("isEMPTY: \t" + table.isEmpty());
	}
	
	public void getColumnBasicInfo(ShortColumn column){
		System.out.println("---------- COLUMN Basic Info ----------");
//		System.out.println("ByteSize: \t" + column.byteSize());
//		System.out.println("ColumnWidth: \t" + column.columnWidth());
//		System.out.println("CountMissing: \t" + column.countMissing());
//		System.out.println("CountUnique: \t" + column.countUnique());
//		System.out.println("First: \t" + column.first());
//		System.out.println("FirstElement: \t" + column.firstElement());
//		System.out.println("ID: \t" + column.id());
//		System.out.println("Kurtosis: \t" + column.kurtosis());
		System.out.println("Metadata: \t" + column.metadata());
//		System.out.println("PopulationVariance: \t" + column.populationVariance());
//		System.out.println("Product: \t" + column.product());
		System.out.println("Min: 	\t" + column.min());
		System.out.println("Max: 	\t" + column.max());
		System.out.println("Median: \t" + column.median());
		System.out.println("Mean: 	\t" + column.mean());
	}
}
