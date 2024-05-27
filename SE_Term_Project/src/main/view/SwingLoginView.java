package main.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SwingLoginView extends SwingView{
	private JLabel idLabel, pwLabel;
    private JTextField idTextField, pwTextField;
    private JButton loginButton;
    
	public SwingLoginView(Mediator mediator) {
        super(mediator, new Dimension(500, 250));

        idLabel = new JLabel("ID");
        pwLabel = new JLabel("PW");
        idTextField = new JTextField();
        pwTextField = new JTextField();
        loginButton = new JButton("Login");
        
        idLabel.setBounds(50, 50, 50, 50);
        pwLabel.setBounds(50, 100, 50, 50);
        idTextField.setBounds(100, 50, 170, 50);
        pwTextField.setBounds(100, 100, 170, 50);
        loginButton.setBounds(300, 50, 120, 100);
    
        add(idLabel);
        add(pwLabel);
        add(idTextField);
        add(pwTextField);
        add(loginButton);

        setVisible(true);
	}
	
	public String getID() {
		return idTextField.getText();
	}
	
	public String getPW() {
		return pwTextField.getText();
	}
	
	public void setLoginListener(ActionListener listener) {
		loginButton.addActionListener(listener);
	}

	@Override
	public List<String> getAccessableViewNames() {
		List<String> list = new ArrayList<String>();
		list.add("LoginView");
		list.add("ProjectListView");
		return list;
	}

	@Override
	public void refresh() {
		idTextField.setText("tester1");
		pwTextField.setText("tester1");
	}
}
