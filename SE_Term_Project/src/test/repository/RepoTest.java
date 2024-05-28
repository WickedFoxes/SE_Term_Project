package test.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import main.domain.Admin;
import main.domain.Dev;
import main.domain.Project;
import main.domain.ProjectLeader;
import main.domain.Tester;
import main.domain.User;
import main.repository.AccountRepo;
import main.repository.ProjectRepo;

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
	
	public void initUsers(AccountRepo repo, ProjectRepo projectRepo) {
		Admin admin = new Admin("admin", "admin");
		ProjectLeader pl1 = new ProjectLeader("pl1", "pl1");
		ProjectLeader pl2 = new ProjectLeader("pl2", "pl2");
		Tester tester1 = new Tester("tester1", "tester1");
		Tester tester2 = new Tester("tester2", "tester2");
		Tester tester3 = new Tester("tester3", "tester3");
		Tester tester4 = new Tester("tester4", "tester4");
		Tester tester5 = new Tester("tester5", "tester5");
		Dev dev1 = new Dev("dev1", "dev1");
		Dev dev2 = new Dev("dev2", "dev2");
		Dev dev3 = new Dev("dev3", "dev3");
		Dev dev4 = new Dev("dev4", "dev4");
		Dev dev5 = new Dev("dev5", "dev5");
		Dev dev6 = new Dev("dev6", "dev6");
		Dev dev7 = new Dev("dev7", "dev7");
		Dev dev8 = new Dev("dev8", "dev8");
		Dev dev9 = new Dev("dev9", "dev9");
		Dev dev10 = new Dev("dev10", "dev10");
		
		admin.setId(0);
		pl1.setId(1);
		pl2.setId(2);
		tester1.setId(3);
		tester2.setId(4);
		tester3.setId(5);
		tester4.setId(6);
		tester5.setId(7);
		dev1.setId(8);
		dev2.setId(9);
		dev3.setId(10);
		dev4.setId(11);
		dev5.setId(12);
		dev6.setId(13);
		dev7.setId(14);
		dev8.setId(15);
		dev9.setId(16);
		dev10.setId(17);
		
		repo.add(admin);
		repo.add(pl1);
		repo.add(pl2);
		repo.add(tester1);
		repo.add(tester2);
		repo.add(tester3);
		repo.add(tester4);
		repo.add(tester5);
		repo.add(dev1);
		repo.add(dev2);
		repo.add(dev3);
		repo.add(dev4);
		repo.add(dev5);
		repo.add(dev6);
		repo.add(dev7);
		repo.add(dev8);
		repo.add(dev9);
		repo.add(dev10);
		
		Project project1 = new Project("proejct1");
		
		projectRepo.add(project1);
		projectRepo.add(project1, tester1);
		projectRepo.add(project1, tester2);
		projectRepo.add(project1, tester3);
		projectRepo.add(project1, dev1);
		projectRepo.add(project1, dev2);
		projectRepo.add(project1, dev3);
		projectRepo.add(project1, dev4);
		projectRepo.add(project1, dev5);
		projectRepo.add(project1, dev6);
	}
}
