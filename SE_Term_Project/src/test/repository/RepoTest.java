package test.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import main.domain.User;

public class RepoTest {
	private String jdbc_name = "org.sqlite.JDBC";
	private String jdbc_connect = "jdbc:sqlite:SE_Term_Project.sqlite3";
	
	public void setJDBC(String name, String con) {
		this.jdbc_name = name;
		this.jdbc_connect = con;
	}
	public void clearDB() {
        Connection connection = null;
        PreparedStatement pstm = null;
        
        User user = null;
        String sql = "DROP TABLE Account; "
        		+ "DROP TABLE Comment; "
        		+ "DROP TABLE Issue; "
        		+ "DROP TABLE Project; "
        		+ "DROP TABLE ProjectJoin;";
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            Statement stmt = connection.createStatement();
            int result = stmt.executeUpdate(sql);
            
            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
	}
	public void createDB(String sqlfile) {
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader( new FileReader( new File( sqlfile ) ) );
			String strLine = "";
			while( ( strLine = br.readLine() ) != null ) {
				buffer.append(strLine);
				buffer.append("\n");
			}
		} catch (Exception e) {	
			e.printStackTrace();
			System.exit(0);
		}
		
        Connection connection = null;
        
        String sql = buffer.toString();
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            Statement stmt = connection.createStatement();
            int result = stmt.executeUpdate(sql);
            
            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
	}
}
