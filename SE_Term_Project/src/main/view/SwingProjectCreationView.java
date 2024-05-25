package main.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.domain.Dev;
import main.domain.enumeration.Authority;

public class SwingProjectCreationView extends SwingView implements ReturnableView{
	private JLabel infoLabel, nameLabel;
    private JTextField nameTextField;
    private JComboBox<Dev> devComboBox;
    private JButton createButton, returnButton;
    
	public SwingProjectCreationView(Mediator mediator) {
		super(mediator, new Dimension(500, 300));
		
        idLabel = new JLabel("ID");
        idLabel.setBounds(100, 50, 50, 50);
        pwLabel = new JLabel("PW");
        pwLabel.setBounds(100, 150, 50, 50);
        idTextField = new JTextField();
        idTextField.setBounds(150, 50, 200, 50);
        pwTextField = new JTextField();
        pwTextField.setBounds(150, 250, 200, 50);
        createButton = new JButton("Create");
        createButton.setBounds(300, 150, 120, 100);
        returnButton = new JButton("Return");
        returnButton.setBounds(300, 150, 120, 100);
    
        add(idLabel);
        add(pwLabel);
        add(idTextField);
        add(pwTextField);
        add(createButton);
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
