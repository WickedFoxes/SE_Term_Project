package main.model;

import main.repository.IssueRepo;

public class IssueModel extends Model {
	private IssueRepo repo;
	public IssueModel(SystemManager s, IssueRepo r) {
		super(s);
		this.repo = r;
	}

	
}
