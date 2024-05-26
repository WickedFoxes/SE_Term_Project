package main.view;

import javax.swing.JFrame;

public class SwingIssueFilterPopup extends JFrame {
	private SwingIssueFilterView viewPanel;
	public SwingIssueFilterPopup(SwingMainView mainView, SwingIssueFilterView view) {
		this.viewPanel = view;
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(mainView);
        setResizable(false);
        setSize(viewPanel.getPreferredSize());
		add(viewPanel);
	}
	
	public void showPopup() {
		setVisible(true);
	}
	
	public void closePopup() {
		setVisible(false);
	}
}
