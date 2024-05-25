package main.view;

import java.awt.event.ActionListener;

public interface ReturnableView {
	public void setReturnListener(ActionListener listener);
	public void requestChangeView(String targetViewName);
}
