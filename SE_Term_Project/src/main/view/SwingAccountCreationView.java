package main.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SwingAccountCreationView extends SwingView {
	private JLabel idLabel, pwLabel;
    private JTextField idTextField, pwTextField;
    private JButton okButton, cancelButton;
    
	public SwingAccountCreationView(Mediator mediator) {
		super(mediator, new Dimension(500, 250));
		
        idLabel = new JLabel("ID");
        idLabel.setBounds(50, 50, 50, 50);
        pwLabel = new JLabel("PW");
        pwLabel.setBounds(50, 100, 50, 50);
        idTextField = new JTextField();
        idTextField.setBounds(100, 50, 170, 50);
        pwTextField = new JTextField();
        pwTextField.setBounds(100, 100, 170, 50);
        okButton = new JButton("OK");
        okButton.setBounds(300, 150, 120, 100);
        cancelButton = new JButton("OK");
        cancelButton.setBounds(300, 150, 120, 100);
    
        add(idLabel);
        add(pwLabel);
        add(idTextField);
        add(pwTextField);
        add(okButton);
        add(cancelButton);
	}
	
	
	@Override
	public List<String> getAccessableViewNames() {
		List<String> list = new ArrayList<String>();
		list.add("ProjectListView");
		return list;
	}
}
