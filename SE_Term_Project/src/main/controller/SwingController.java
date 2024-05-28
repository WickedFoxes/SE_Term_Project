package main.controller;

import main.model.Model;
import main.model.Observer;
import main.view.SwingView;

public abstract class SwingController {
	Model model;
	SwingView view;
	public SwingController() {
		setObserver();
	}
	
	private void setObserver() {
		model.subscribe(new Observer() {
			public void update() {
				if(!view.getAccessableViewNames().contains(view.requestGetCurrentViewName())) return;
				
			}
		});
	}
	
	
}
