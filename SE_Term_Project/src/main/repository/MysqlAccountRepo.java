package main.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import main.domain.Admin;
import main.domain.Dev;
import main.domain.Issue;
import main.domain.ProjectLeader;
import main.domain.Tester;
import main.domain.User;
import main.domain.enumeration.Authority;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;

public class MysqlAccountRepo implements AccountRepo{
	private String jdbc_name = "org.sqlite.JDBC";
	private String jdbc_connect = "jdbc:sqlite:SE_Term_Project.sqlite3";
	
	public void setJDBC(String name, String con) {
		this.jdbc_name = name;
		this.jdbc_connect = con;
	}
	
	@Override
	public void add(User user) {
        Connection connection = null;
        PreparedStatement pstm = null;
        String sql = "insert into Account (\"accountid\", \"password\", \"authority\") "
        		+ "values (?, ?, ?) ";

        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, user.getAccountID());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getAuthority().name());
            
            int res = pstm.executeUpdate();
            if(res > 0 ) {
                System.out.println("DB입력 성공");
            }else {
                System.out.println("DB입력 실패");
            }

            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
		
	}

	@Override
	public boolean contains(String accountID) {
        Connection connection = null;
        PreparedStatement pstm = null;
        
        boolean flag = false;
        String sql = "select * from Account where accountid=?";
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, accountID);
            
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()) {
            	flag = true;
            }
            
            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return flag;
	}

	@Override
	public User find(String accountID) {
        Connection connection = null;
        PreparedStatement pstm = null;
        
        User user = null;
        String sql = "select * from Account where accountid=?";
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, accountID);
            
            ResultSet rs = pstm.executeQuery();
            int id;
            String accountId, pw;
            
            if(rs.next()) {
            	id = rs.getInt(1);
            	accountId = rs.getString(2);
                pw = rs.getString(3);
                
                if(rs.getString(4).equals("PL"))
                	user = new ProjectLeader(id, accountId, pw);
                else if(rs.getString(4).equals("TESTER"))
                	user = new Tester(id, accountId, pw);
                else if(rs.getString(4).equals("DEV"))
                	user = new Dev(id, accountId, pw);
                else if(rs.getString(4).equals("ADMIN"))
                	user = new Admin(id, accountId, pw);
            }
            
            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return user;
	}

	@Override
	public List<User> findAll(Authority authority) {
        Connection connection = null;
        PreparedStatement pstm = null;
        List<User> users = new ArrayList<>();
        
        String sql = "select * from Account where authority=?";
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, authority.name());
            
            ResultSet rs = pstm.executeQuery();
            int id;
            String accountId, pw;
            User temp;
            while(rs.next()) {
            	temp = null;
            	
            	id = rs.getInt(1);
            	accountId = rs.getString(2);
                pw = rs.getString(3);
                
                if(authority == Authority.PL)
                	temp = new ProjectLeader(id, accountId, pw);
                else if(authority == Authority.TESTER)
                	temp = new Tester(id, accountId, pw);
                else if(authority == Authority.DEV)
                	temp = new Dev(id, accountId, pw);
                else if(authority == Authority.ADMIN)
                	temp = new Admin(id, accountId, pw);
            	
                users.add(temp);
            }
            
            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
		return users;
	}

}
