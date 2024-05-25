package main.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.domain.enumeration.Authority;

public class SwingAccountCreationView extends SwingView implements ReturnableView {
	private JLabel infoLabel, idLabel, pwLabel, authorityLabel;
    private JTextField idTextField, pwTextField;
    private JComboBox<Authority> authorityComboBox;
    private JButton okButton, returnButton;

    
	public SwingAccountCreationView(Mediator mediator) {
		super(mediator, new Dimension(500, 400));
		
		infoLabel = new JLabel("Create Account");
		idLabel = new JLabel("ID");
		pwLabel = new JLabel("PW");
		authorityLabel = new JLabel("Authority");
		idTextField = new JTextField();
		pwTextField = new JTextField();
		Authority[] options = {Authority.PL, Authority.DEV, Authority.TESTER};
        authorityComboBox = new JComboBox<Authority>(options);
        okButton = new JButton("OK");
        returnButton = new JButton("Return");
        
        infoLabel.setBounds(200, 20, 200, 50);
        idLabel.setBounds(100, 70, 50, 50);
        pwLabel.setBounds(95, 130, 50, 50);
        authorityLabel.setBounds(80, 190, 100, 50);
        idTextField.setBounds(150, 70, 200, 50);
        pwTextField.setBounds(150, 130, 200, 50);
        authorityComboBox.setBounds(150, 190, 200, 50);
        okButton.setBounds(140, 260, 100, 40);
        returnButton.setBounds(260, 260, 100, 40);
    
        add(infoLabel);
        add(idLabel);
        add(pwLabel);
        add(authorityLabel);
        add(idTextField);
        add(pwTextField);
        add(authorityComboBox);
        add(okButton);
        add(returnButton);
	}
	
	public String getID() {
		return idTextField.getText();
	}
	
	public String getPW() {
		return pwTextField.getText();
	}
	
	public Authority getAuthority() {
		return (Authority)authorityComboBox.getSelectedItem();
	}
	
	public void setOKListener(ActionListener listener) {
		okButton.addActionListener(listener);
	}
	
	@Override
	public void setReturnListener(ActionListener listener) {
		returnButton.addActionListener(listener);
	}
	
	@Override
	public String getReturnViewName() { return "ProjectListView"; }
	
	@Override
	public boolean needNullifyProject() { return false; }

	@Override
	public boolean needNullifyIssue() {	return false; }
	
	@Override
	public List<String> getAccessableViewNames() {
		List<String> list = new ArrayList<String>();
		list.add("ProjectListView");
		return list;
	}

	@Override
	public void refresh() {
		idTextField.setText("");
		pwTextField.setText("");
		authorityComboBox.setSelectedIndex(2);
	}
}
