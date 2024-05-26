package main.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

public class SwingIssueDetailView extends SwingView implements ReturnableView {

	public SwingIssueDetailView(Mediator mediator) {
		super(mediator,  new Dimension(700, 600));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setReturnListener(ActionListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getReturnViewName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean needNullifyProject() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean needNullifyIssue() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getAccessableViewNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
