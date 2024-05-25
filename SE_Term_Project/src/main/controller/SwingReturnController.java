package main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.model.Model;
import main.view.ReturnableView;

public class SwingReturnController extends SwingController {
	private ReturnableView view;
	private Model model;
	
	public SwingReturnController(ReturnableView view, Model model) {
		this.view = view;
		this.view.setReturnListener(new ReturnButtonListener(view.getReturnViewName()));
		this.model = model;
	}
	
	private class ReturnButtonListener implements ActionListener{
		private String returnViewName;
		public ReturnButtonListener(String returnViewName) {
			this.returnViewName = returnViewName;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(view.needNullifyIssue()) model.setIssue(null);
			if(view.needNullifyProject()) model.setProject(null);
			view.requestChangeView(returnViewName);
		}
	}
}
