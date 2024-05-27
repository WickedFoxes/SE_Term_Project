package main.view;

public interface Mediator {
	public void changeView(SwingView view, String targetViewName);
	public String getCurrentViewName();
}
