package main.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import main.domain.Admin;
import main.domain.Dev;
import main.domain.Project;
import main.domain.ProjectLeader;
import main.domain.Tester;
import main.domain.User;
import main.domain.enumeration.Authority;

public class MysqlProjectRepo implements ProjectRepo{
	private String jdbc_name = "org.sqlite.JDBC";
	private String jdbc_connect = "jdbc:sqlite:SE_Term_Project.sqlite3";
	
	public void setJDBC(String name, String con) {
		this.jdbc_name = name;
		this.jdbc_connect = con;
	}
	
	@Override
	public Project add(Project project) {
        Connection connection = null;
        PreparedStatement pstm = null;
        int id = 0;
        
        String sql = "insert into Project (\"name\", \"created_date\") "
        		+ "values (?, ?) ";
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

//            pstm = connection.prepareStatement(sql);
            pstm = (PreparedStatement) connection.prepareStatement(
            		sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, project.getName());
            pstm.setTimestamp(2, project.getCreatedDate());
            
            int res = pstm.executeUpdate();
            if(res > 0 ) {
                System.out.printf("DB입력 성공");
            }else {
                System.out.println("DB입력 실패");
            }
            
            ResultSet rs = pstm.getGeneratedKeys();
            if(rs.next()) id = rs.getInt(1);

            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        project.setId(id);
        return project;
	}

	@Override
	public void add(Project project, User user) {
		Connection connection = null;
        PreparedStatement pstm = null;
        String sql = "insert into ProjectJoin (\"project_id\", \"account_id\") "
        		+ "values (?, ?) ";

        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, project.getId());
            pstm.setInt(2, user.getId());
            
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
	public boolean contains(Project project, User user) {
		Connection connection = null;
        PreparedStatement pstm = null;
        
        boolean flag = false;
        String sql = "select * from ProjectJoin where project_id=? and account_id=?";
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, project.getId());
            pstm.setInt(2, user.getId());
            
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
	public List<User> findAll(Project project, Authority authority) {
        Connection connection = null;
        PreparedStatement pstm = null;
        List<User> users = new ArrayList<>();
        
        String sql = "SELECT Account.id, Account.accountid, Account.password, Account.authority "
        		+ "	FROM Account INNER JOIN ProjectJoin ON Account.id = ProjectJoin.account_id "
        		+ "	WHERE Account.authority = ? and ProjectJoin.project_id = ?";
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, authority.name());
            pstm.setInt(2, project.getId());
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
            	User temp = null;
            	
            	int id = rs.getInt(1);
                String accountid = rs.getString(2);
                String pw = rs.getString(3);
                
                if(authority == Authority.PL)
                	temp = new ProjectLeader(accountid, pw);
                else if(authority == Authority.TESTER)
                	temp = new Tester(accountid, pw);
                else if(authority == Authority.DEV)
                	temp = new Dev(accountid, pw);
                else if(authority == Authority.ADMIN)
                	temp = new Admin(accountid, pw);
            	temp.setId(id);
            	users.add(temp);
            }
            
            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
		return users;
	}

	@Override
	public List<Project> findAll(User user) {
        Connection connection = null;
        PreparedStatement pstm = null;
        List<Project> projects = new ArrayList<>();
        
        String sql = "SELECT Project.id, Project.name, Project.created_date"
        		+ "	FROM Project INNER JOIN ProjectJoin ON Project.id = ProjectJoin.project_id "
        		+ "	WHERE ProjectJoin.account_id=?";
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, user.getId());
            
            ResultSet rs = pstm.executeQuery();
            int id;
            String title;
            Timestamp createdDate;
            Project temp;
            
            while(rs.next()) {
            	id = rs.getInt(1);
            	title = rs.getString(2);
            	createdDate = rs.getTimestamp(3);
            	
            	temp = new Project(id, title, createdDate);
            	projects.add(temp);
            }
            
            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
		return projects;
	}

}
