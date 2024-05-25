package main.view;

import java.awt.event.ActionListener;

public interface LogoutableView {
	public void setLogoutListener(ActionListener listener);
	public void requestChangeView(String targetViewName);
}
