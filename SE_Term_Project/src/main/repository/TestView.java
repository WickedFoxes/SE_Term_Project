package main.repository;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TestView extends JFrame{
    private JLabel j1, j2;
    private JTextField jt1, jt2;
    private JButton btn;
	
	public TestView() {
        super("J프레임 테스트");
        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new GridLayout(3,2));
        j1 = new JLabel("ID: ");  
        j2 = new JLabel("PW: ");
        jt1 = new JTextField();
        jt2 = new JTextField();
        
        btn = new JButton("Login");
        
        add(j1);
        add(jt1);
        add(j2);
        add(jt2);
        add(btn);
        
        setVisible(true);
	}
	
	public String getID() {
		return j1.getText();
	}
	
	public String getPW() {
		return j2.getText();
	}
	
	public void setListener(ActionListener listener) {
		btn.addActionListener(listener);
	}
	
	
    public static void main(String[] args) { 	 
    	TestView t = new TestView();
    }
}
