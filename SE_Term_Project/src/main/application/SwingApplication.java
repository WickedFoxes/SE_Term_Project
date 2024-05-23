package main.application;

import main.view.SwingLoginView;

public class SwingApplication implements Application{
	public void run() {
		SwingLoginView view = new SwingLoginView();
	}
}
