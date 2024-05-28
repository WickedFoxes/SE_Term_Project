package main.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class SwingView extends JPanel{
	protected Mediator mediator;
	private Dimension preferredSize;
	
	public SwingView(Mediator mediator, Dimension size) {
        super();
        this.mediator = mediator;
        this.preferredSize = size;
        setLayout(null);
	}
	
	public Dimension getPreferredSize() {
		return preferredSize;
	}
	
	public void showMessagePopup(String title, String content, int messageType) {
		JOptionPane.showMessageDialog(this, content, title, messageType);
	}
	
	public void requestChangeView(String targetViewName) {
		mediator.changeView(this, targetViewName);
	}
	
	public String requestGetCurrentViewName() {
		return mediator.getCurrentViewName();
	}
	
	public abstract List<String> getAccessableViewNames();
	protected void refresh() {}
}
