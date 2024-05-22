package main.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import main.domain.Admin;
import main.domain.Dev;
import main.domain.FilterOption;
import main.domain.Issue;
import main.domain.Project;
import main.domain.ProjectLeader;
import main.domain.Tester;
import main.domain.User;
import main.domain.enumeration.Authority;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;

public class MysqlIssueRepo implements IssueRepo{
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
	public void add(Project project, Issue issue) {
        Connection connection = null;
        PreparedStatement pstm = null;
        String sql = "insert into Issue (\"title\", \"description\", \"priority\", \"state\", \"reporter\", \"assignee\", \"fixer\", \"project_id\"  ,\"reportedDate\") "
        		+ "values (?, ?, ?, ?, ?, ?, ?, ?, datetime(\"now\")) ";

        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, issue.getTitle());
            pstm.setString(2, issue.getDescription());
            pstm.setString(3, issue.getPriority().name());
            pstm.setString(4, issue.getState().name());
            if(issue.getReporter() == null)
            	pstm.setNull(5, Types.INTEGER);
            else
            	pstm.setInt(5, issue.getReporter().getId());
            
            if(issue.getAssignee() == null)
            	pstm.setNull(6, Types.INTEGER);
            else
            	pstm.setInt(6, issue.getAssignee().getId());
            
            if(issue.getFixer() == null)
            	pstm.setNull(7, Types.INTEGER);
            else
            	pstm.setInt(7, issue.getAssignee().getId());
            
            pstm.setInt(8, project.getId());
            
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
	public List<Issue> findAll(Project project, User user) {
        Connection connection = null;
        PreparedStatement pstm = null;
        List<Issue> issues = new ArrayList<>();
        
        Authority auth = user.getAuthority();
        
        String sql = "select * from Issue where project_id=?";
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, project.getId());
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
            	Issue temp = new Issue(rs.getString(2), rs.getString(3));
            	
            	temp.setId(rs.getInt(1));
            	temp.setReportedDate(rs.getTimestamp(4));
            	temp.setPriority(Priority.valueOf(rs.getString(5)));
            	temp.setState(State.valueOf(rs.getString(6)));
            	
            	Tester reporter = (Tester)getUser(rs.getInt(8));
            	Dev assignee = (Dev)getUser(rs.getInt(9));
            	Dev fixer = (Dev)getUser(rs.getInt(10));
            	
            	if(reporter != null)
            		temp.setReporter(reporter);
            	if(assignee != null)
            		temp.setAssignee(assignee);
            	if(fixer != null)
            		temp.setFixer(fixer);
            	
            	// user authority check
            	if(auth == Authority.DEV) {
            		if(assignee == null) continue;
            		if(assignee.getId() != user.getId()) continue;
            	}
            	if(auth == Authority.TESTER) {
            		if(reporter == null) continue;
            		if(reporter.getId() != user.getId()) continue;
            	}
            	
            	issues.add(temp);
            }
            
            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
		return issues;
	}

	@Override
	public List<Issue> findAll(Project project, User user, FilterOption option) {
        Connection connection = null;
        PreparedStatement pstm = null;
        List<Issue> issues = new ArrayList<>();
        
        Dev filter_assignee = option.getAssignee();
        Tester filter_reporter = option.getReporter();
        State filter_state = option.getState();
        
        Authority auth = user.getAuthority();
        
        String sql = "select * from Issue where project_id=?";
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, project.getId());
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()) {
            	Issue temp = new Issue(rs.getString(2), rs.getString(3));
            	
            	temp.setId(rs.getInt(1));
            	temp.setReportedDate(rs.getTimestamp(4));
            	temp.setPriority(Priority.valueOf(rs.getString(5)));
            	temp.setState(State.valueOf(rs.getString(6)));
            	
            	Tester reporter = (Tester)getUser(rs.getInt(8));
            	Dev assignee = (Dev)getUser(rs.getInt(9));
            	Dev fixer = (Dev)getUser(rs.getInt(10));
            	
            	if(reporter != null)
            		temp.setReporter(reporter);
            	if(assignee != null)
            		temp.setAssignee(assignee);
            	if(fixer != null)
            		temp.setFixer(fixer);
            	
            	// user authority check
            	if(auth == Authority.DEV) {
            		if(assignee == null) continue;
            		if(assignee.getId() != user.getId()) continue;
            	}
            	if(auth == Authority.TESTER) {
            		if(reporter == null) continue;
            		if(reporter.getId() != user.getId()) continue;
            	}
            	
            	// filtering option check
            	if(filter_assignee != null) {
            		if(assignee == null) continue;
            		if(filter_assignee.getId() != assignee.getId()) continue;
            	}
            	if(filter_reporter != null) {
            		if(reporter == null) continue;
            		if(filter_reporter.getId() != (reporter.getId())) continue;
            	}
            	if(filter_state != null)
            		if(filter_state != temp.getState()) continue;
            	
            	issues.add(temp);
            }
            connection.close(); 
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
		return issues;
	}
	
	@Override
	public void setState(Issue issue, State state) {
        Connection connection = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE Issue SET state=? WHERE id=?";
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, state.name().toString());
            pstm.setInt(2, issue.getId());
            
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
	public void setAssignee(Issue issue, Dev assignee) {
        Connection connection = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE Issue SET assignee=? WHERE id=?";
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, assignee.getId());
            pstm.setInt(2, issue.getId());
            
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
	public void setFixer(Issue issue, Dev fixer) {
        Connection connection = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE Issue SET fixer=? WHERE id=?";
        
        try {
			Class.forName(jdbc_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
            connection = DriverManager.getConnection(jdbc_connect);

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, issue.getId());
            pstm.setInt(2, fixer.getId());
            
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
}
