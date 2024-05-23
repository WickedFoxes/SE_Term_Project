package main.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
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
        idLabel.setBounds(50, 50, 50, 50);
        pwLabel = new JLabel("PW");
        pwLabel.setBounds(50, 100, 50, 50);
        idTextField = new JTextField();
        idTextField.setBounds(100, 50, 170, 50);
        pwTextField = new JTextField();
        pwTextField.setBounds(100, 100, 170, 50);
        loginButton = new JButton("Login");
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
}

//private void addComponent(Component c, int x, int y, int w, int h) {
//con.weightx = 1;
//con.weighty = 1;
//con.gridx = x;
//con.gridy = y;
//con.gridwidth = w;
//con.gridheight = h;
//con.fill = GridBagConstraints.BOTH;
//grid.setConstraints(c, con);
//panel.add(c);
//}
