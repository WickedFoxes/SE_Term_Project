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
		infoLabel.setBounds(200, 20, 200, 50);
        idLabel = new JLabel("ID");
        idLabel.setBounds(100, 70, 50, 50);
        pwLabel = new JLabel("PW");
        pwLabel.setBounds(95, 130, 50, 50);
        authorityLabel = new JLabel("Authority");
        authorityLabel.setBounds(80, 190, 100, 50);
        idTextField = new JTextField();
        idTextField.setBounds(150, 70, 200, 50);
        pwTextField = new JTextField();
        pwTextField.setBounds(150, 130, 200, 50);
        Authority[] options = {Authority.PL, Authority.DEV, Authority.TESTER};
        authorityComboBox = new JComboBox<Authority>(options);
        authorityComboBox.setBounds(150, 190, 200, 50);
        okButton = new JButton("OK");
        okButton.setBounds(140, 270, 100, 40);
        returnButton = new JButton("Return");
        returnButton.setBounds(260, 270, 100, 40);
    
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
	
	@Override
	public void setReturnListener(ActionListener listener) {
		returnButton.addActionListener(listener);
	}
	
	@Override
	public List<String> getAccessableViewNames() {
		List<String> list = new ArrayList<String>();
		list.add("ProjectListView");
		return list;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
