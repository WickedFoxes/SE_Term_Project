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
import main.domain.Comment;
import main.domain.Dev;
import main.domain.Issue;
import main.domain.ProjectLeader;
import main.domain.Tester;
import main.domain.User;
import main.domain.enumeration.Authority;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;

public class MysqlCommentRepo implements CommentRepo{
	private String jdbc_name = "org.sqlite.JDBC";
	private String jdbc_connect = "jdbc:sqlite:SE_Term_Project.sqlite3";
	
	private int getAccount(String id) {
        Connection connection = null;
        PreparedStatement pstm = null;
        
        int account_id = -1;
        String sql = "select * from Account where userid=?";
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);
            
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()) {
            	account_id = rs.getInt(1);
            }
            
            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return account_id;
	}
	private User getUser(int id) {		
        Connection connection = null;
        PreparedStatement pstm = null;
        
        User user = null;
        String sql = "select * from Account where id=?";
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()) {
                String userid = rs.getString(2);
                String pw = rs.getString(3);
                
//                if(rs.getString(4).equals("PL"))
//                	user = new ProjectLeader(userid, pw);
//                else if(rs.getString(4).equals("TESTER"))
//                	user = new Tester(userid, pw);
//                else if(rs.getString(4).equals("DEV"))
//                	user = new Dev(userid, pw);
//                else if(rs.getString(4).equals("ADMIN"))
//                	user = new Admin(userid, pw);
            }
            
            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return user;
	}
	@Override
	public void add(User user, Issue issue, Comment comment) {
        Connection connection = null;
        PreparedStatement pstm = null;
        String sql = "insert into Comment (\"content\", \"created_date\", \"writer\", \"issue_id\") "
        		+ "values (?, datetime(\"now\"), ?, ? ) ";

        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, comment.getContent());
            //pstm.setInt(2, getAccount(user.getId()));
            pstm.setInt(3, issue.getId());
            
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
	public List<Comment> find(Issue issue) {
        Connection connection = null;
        PreparedStatement pstm = null;
        List<Comment> comments = new ArrayList<>();
        
        String sql = "select * from Comment where issue_id=?";
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, issue.getId());
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
            	Comment temp = new Comment();
            	temp.setContent(rs.getString(2));
            	temp.setWrittenDate(rs.getTimestamp(3));
            	
            	User user = getUser(rs.getInt(4));
            	temp.setWriter(user);
            	
            	comments.add(temp);
            }
            
            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
		return comments;
	}

}
