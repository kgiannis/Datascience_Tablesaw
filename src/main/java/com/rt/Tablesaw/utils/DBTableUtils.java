package com.rt.Tablesaw.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import tech.tablesaw.api.Table;

public class DBTableUtils {
	
	/** Class Logger */
	private static final Logger LOGGER = Logger.getLogger(DBTableUtils.class.getName());

	
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
	
}
