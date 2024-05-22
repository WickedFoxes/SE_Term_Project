package main.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	
	public void setJDBC(String name, String con) {
		this.jdbc_name = name;
		this.jdbc_connect = con;
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
                String accountid = rs.getString(2);
                String pw = rs.getString(3);
                Authority auth = Authority.valueOf(rs.getString(4));
                
                if(auth == Authority.PL)
                	user = new ProjectLeader(accountid, pw);
                else if(auth == Authority.TESTER)
                	user = new Tester(accountid, pw);
                else if(auth == Authority.DEV)
                	user = new Dev(accountid, pw);
                else if(auth == Authority.ADMIN)
                	user = new Admin(accountid, pw);
                
                user.setId(id);
            }
            
            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return user;
	}
	@Override
	public void add(Issue issue, Comment comment) {
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
            pstm.setInt(2, comment.getWriter().getId());
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
	public List<Comment> findAll(Issue issue) {
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
            	String content = rs.getString(2);
            	Timestamp writtenDate = rs.getTimestamp(3);
            	User tempuser = getUser(rs.getInt(4));
            	int issue_id = rs.getInt(5);
            	Comment temp = new Comment(content, tempuser);
            	temp.setWrittenDate(writtenDate);
            	
            	if(issue_id != issue.getId()) continue;
            	
            	comments.add(temp);
            }
            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
		return comments;
	}

}
