package main.view;

public interface Mediator {
	public void notify(SwingView view, String targetViewName);
}
