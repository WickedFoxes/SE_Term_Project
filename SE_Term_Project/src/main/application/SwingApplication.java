package main.application;

import main.view.SwingLoginView;
import main.view.SwingProjectListView;

public class SwingApplication implements Application{
	public void run() {
		//SwingLoginView view = new SwingLoginView();
		SwingProjectListView view = new SwingProjectListView();
	}
}
