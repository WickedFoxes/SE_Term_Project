package main.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import main.domain.Comment;
import main.domain.Dev;
import main.domain.Issue;
import main.domain.Project;
import main.domain.Tester;
import main.domain.enumeration.Authority;
import main.domain.enumeration.Priority;
import main.domain.enumeration.State;

public class SwingIssueDetailView extends SwingView implements ReturnableView {
	private JLabel stateLabel, priorityLabel, descriptionLabel, reportedDateLabel, reporterLabel, assigneeLabel, fixerLabel, commentLabel;
    private JComboBox<State> stateComboBox;
    private JComboBox<Priority> priorityComboBox;
    private JTextField titleTextField, descriptionTextField, reportedDateTextField;
    private JComboBox<Tester> reporterComboBox;
    private JComboBox<Dev> assigneeComboBox, fixerComboBox;
    private JButton returnButton, saveButton, recommendButton;
    private CommentPanel commentPanel;
    
    private State[] states = new State[] { State.NEW, State.ASSIGNED, State.FIXED, State.RESOLVED, State.CLOSED, State.REOPENED };
    private DisabledItemComboBoxModel<State> stateModel_PL, stateModel_Dev, stateModel_Tester;
    private DefaultComboBoxModel<State> defaultStateModel;
    
	public SwingIssueDetailView(Mediator mediator) {
		super(mediator,  new Dimension(700, 850));
		
		stateLabel = new JLabel("State");
		priorityLabel = new JLabel("Priority");
		descriptionLabel = new JLabel("Description");
		reportedDateLabel = new JLabel("Reported Date");
		reporterLabel = new JLabel("Reporter");
		assigneeLabel = new JLabel("Assignee");
		fixerLabel = new JLabel("Fixer");
		commentLabel = new JLabel("Comment");
		
		stateComboBox = new JComboBox<State>(states);
		stateComboBox.setEnabled(false);
		stateComboBox.addActionListener(new ActionListener() {
			private State lastSelected = (State)stateComboBox.getItemAt(-1);
			@Override
			public void actionPerformed(ActionEvent e) {
            	if(!(stateComboBox.getModel() instanceof DisabledItemComboBoxModel<State>)) return;
            	State selected = (State) stateComboBox.getSelectedItem();
                if (((DisabledItemComboBoxModel<State>)stateComboBox.getModel()).isDisabled(selected)) {
                	stateComboBox.setSelectedItem(lastSelected);
                    return;
                }
                if(selected == State.FIXED) {
                	
                }
                lastSelected = selected;
			}
        });
		defaultStateModel = new DefaultComboBoxModel<State>(states);
		stateModel_PL = new DisabledItemComboBoxModel<State>(states, new State[] { State.NEW, State.ASSIGNED, State.FIXED, State.REOPENED });
		stateModel_Dev = new DisabledItemComboBoxModel<State>(states, new State[] { State.NEW, State.RESOLVED, State.CLOSED, State.REOPENED });
		stateModel_Tester = new DisabledItemComboBoxModel<State>(states, new State[] { State.NEW, State.ASSIGNED, State.CLOSED, State.REOPENED });
		
		priorityComboBox = new JComboBox<Priority>(new Priority[] {Priority.BLOCKER, Priority.CRITICAL, Priority.MAJOR, Priority.MINOR, Priority.TRIVAL});
		priorityComboBox.setEnabled(false);
		reporterComboBox = new JComboBox<Tester>();
		reporterComboBox.setEnabled(false);
		assigneeComboBox = new JComboBox<Dev>();
		assigneeComboBox.setEnabled(false);
		assigneeComboBox.addActionListener(new ActionListener() {
			private Dev lastSelected = (Dev)assigneeComboBox.getItemAt(-1);
            @Override
            public void actionPerformed(ActionEvent e) {
            	Dev selected = (Dev) assigneeComboBox.getSelectedItem();
                if (selected != null) {
                	stateComboBox.setSelectedItem(State.ASSIGNED);
                    lastSelected = selected;
                    return;
                }
            }
        });
		fixerComboBox = new JComboBox<Dev>();
		fixerComboBox.setEnabled(false);

		titleTextField = new JTextField(); 
		titleTextField.setEnabled(false);
		descriptionTextField = new JTextField();
		descriptionTextField.setEnabled(false);
		reportedDateTextField = new JTextField();
		reportedDateTextField.setEnabled(false);
		
		commentPanel = new CommentPanel();
		returnButton = new JButton("Return");
		saveButton = new JButton("Save");
		recommendButton = new JButton("Rcmmnd");
		
		titleTextField.setBounds(180, 30, 320, 40);
		stateLabel.setBounds(180, 70, 50, 40);
		priorityLabel.setBounds(340, 70, 50, 40);
		stateComboBox.setBounds(180, 100, 140, 40);
		priorityComboBox.setBounds(340, 100, 160, 40);
		
		descriptionLabel.setBounds(180, 140, 100, 40);
		descriptionTextField.setBounds(180, 170, 320, 70);
		
		reportedDateLabel.setBounds(180, 240, 100, 40);
		reportedDateTextField.setBounds(180, 270, 320, 40);
		
		reporterLabel.setBounds(180, 310, 100, 40);
		reporterComboBox.setBounds(180, 340, 320, 40);
		
		assigneeLabel.setBounds(180, 380, 100, 40);
		assigneeComboBox.setBounds(180, 410, 320, 40);
		recommendButton.setBounds(510, 410, 90, 40);
		
		fixerLabel.setBounds(180, 450, 100, 40);
		fixerComboBox.setBounds(180, 480, 320, 40);
		commentLabel.setBounds(180, 520, 100, 40);
		commentPanel.setBounds(180, 550, 320, 200);
		
		returnButton.setBounds(50, 30, 100, 40);
		saveButton.setBounds(50, 80, 100, 40);
		
		add(stateLabel);
		add(priorityLabel);
		add(descriptionLabel);
		add(reportedDateLabel);
		add(reporterLabel);
		add(assigneeLabel);
		add(fixerLabel);
		add(commentLabel);
		add(stateComboBox);
		add(priorityComboBox);
		add(titleTextField);
		add(descriptionTextField);
		add(reportedDateTextField);
		add(reporterComboBox);
		add(assigneeComboBox);
		add(fixerComboBox);
		add(commentPanel);
		add(returnButton);
		add(saveButton);
		add(recommendButton);
	}
	
	public Priority getPriority() {
		return (Priority)priorityComboBox.getSelectedItem();
	}
	
	public State getState() {
		return (State)stateComboBox.getSelectedItem();
	}
	
	public Dev getAssignee() {
		return (Dev)assigneeComboBox.getSelectedItem();
	}
	
	public String getCommentContent() {
		return commentPanel.getCommentContent();
	}
	
	public void updateFixer(Dev fixer) {
		fixerComboBox.setSelectedItem(fixer);
	}
	
	public void updateComboBoxs(List<Dev> devs, List<Tester> testers) {
		reporterComboBox.removeAllItems();
		reporterComboBox.addItem(null);
		for(Tester tester : testers) reporterComboBox.addItem(tester); 
		
		assigneeComboBox.removeAllItems();
		assigneeComboBox.addItem(null);
		fixerComboBox.removeAllItems();
		fixerComboBox.addItem(null);
		for(Dev dev : devs) {
			assigneeComboBox.addItem(dev);
			fixerComboBox.addItem(dev); 
		}
	}
	
	public void updateIssueData(Issue issue, Authority authority) {
 		titleTextField.setText(issue.getTitle());
 		descriptionTextField.setText(issue.getDescription());
 		reportedDateTextField.setText(issue.getReportedDate().toGMTString());
 		priorityComboBox.setSelectedItem(issue.getPriority());
 		reporterComboBox.setSelectedItem(issue.getReporter());
 		assigneeComboBox.setSelectedItem(issue.getAssignee());
 		fixerComboBox.setSelectedItem(issue.getFixer());
 		stateComboBox.setSelectedItem(issue.getState());
 		updateComboBoxsSelectablility(issue, authority);
	}
	
	private void updateComboBoxsSelectablility(Issue issue, Authority authority) {
		State currentSelectedState = (State) stateComboBox.getSelectedItem();
		if(authority == Authority.PL) updateComboBoxsSelectablility_PL(issue);
		else if(authority == Authority.DEV) updateComboBoxsSelectablility_Dev(issue);
		else if(authority == Authority.TESTER) updateComboBoxsSelectablility_Tester(issue);
		stateComboBox.setSelectedItem(currentSelectedState);
	}
	
	private void updateComboBoxsSelectablility_PL(Issue issue) {
		priorityComboBox.setEnabled(true);

		if(issue.getState() == State.NEW) {
			stateComboBox.setEnabled(false);
			assigneeComboBox.setEnabled(true);
		}
		else if(issue.getState() == State.RESOLVED) {
			stateComboBox.setModel(stateModel_PL);
			stateComboBox.setRenderer(new DisabledItemComboBoxRenderer(stateModel_PL));
			stateComboBox.setEnabled(true);
			assigneeComboBox.setEnabled(false);
		}
		else {
			stateComboBox.setModel(defaultStateModel);
			stateComboBox.setRenderer(new DefaultListCellRenderer());
			stateComboBox.setEnabled(false);
			assigneeComboBox.setEnabled(false);
		}
	}
	
	private void updateComboBoxsSelectablility_Dev(Issue issue) {
		priorityComboBox.setEnabled(false);
		assigneeComboBox.setEnabled(false);
		
		if(issue.getState() == State.ASSIGNED) {
			stateComboBox.setModel(stateModel_Dev);
			stateComboBox.setRenderer(new DisabledItemComboBoxRenderer(stateModel_Dev));
			stateComboBox.setEnabled(true);
		}
		else {
			stateComboBox.setModel(defaultStateModel);
			stateComboBox.setRenderer(new DefaultListCellRenderer());
			stateComboBox.setEnabled(false);
		}
	}
	
	private void updateComboBoxsSelectablility_Tester(Issue issue) {
		priorityComboBox.setEnabled(false);
		assigneeComboBox.setEnabled(false);
		
		if(issue.getState() == State.FIXED) {
			stateComboBox.setModel(stateModel_Tester);
			stateComboBox.setRenderer(new DisabledItemComboBoxRenderer(stateModel_Tester));
			stateComboBox.setEnabled(true);
		}
		else {
			stateComboBox.setModel(defaultStateModel);
			stateComboBox.setRenderer(new DefaultListCellRenderer());
			stateComboBox.setEnabled(false);
		}
	}
	 
	public void updateComments(List<Comment> comments) {
		commentPanel.updateComments(comments);
	}
	
	public void updateRecommendButtonVisibility(boolean isVisible) {
		recommendButton.setVisible(isVisible);
	}
	
	public void setWriteListener(ActionListener listener) {
		commentPanel.setWriteListener(listener);
	}	
	
	public void setStateComboBoxListener(ActionListener listener) {
		stateComboBox.addActionListener(listener);
	}
	
	public void setRecommendListener(ActionListener listener) {
		recommendButton.addActionListener(listener);
	}

	public void setSaveListener(ActionListener listener) {
		saveButton.addActionListener(listener);
	}
	
	@Override
	public void setReturnListener(ActionListener listener) {
		returnButton.addActionListener(listener);
	}

	@Override
	public String getReturnViewName() { return "IssueListView"; }

	@Override
	public boolean needNullifyProject() { return false; }

	@Override
	public boolean needNullifyIssue() { return true; }

	@Override
	public List<String> getAccessableViewNames() { 
		List<String> list = new ArrayList<String>();
		list.add("IssueDetailView");
		list.add("IssueListView");
		return list;
	}

	@Override
	public void refresh() {
		commentPanel.clearCommentTextField();
	}
	
	private class CommentPanel extends JPanel {
		private JPanel panel;
		private JTextField commentTextField;
		private JButton writeButton;
	    private JScrollPane scrollPane;
	    
		private GridBagLayout grid;
		private GridBagConstraints contstraints;
		private List<JLabel> commentLabels;
		
		public CommentPanel() {
			setLayout(null);
			setVisible(true);
		
			panel = new JPanel();
			commentTextField = new JTextField();
			writeButton = new JButton("←");
			commentLabels = new ArrayList<JLabel>();
			grid = new GridBagLayout();
			contstraints = new GridBagConstraints();
			contstraints.fill = GridBagConstraints.HORIZONTAL;
			contstraints.anchor = GridBagConstraints.PAGE_START;
			contstraints.insets = new Insets(5, 5, 5, 5);
			panel.setLayout(grid);
			scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setViewportView(panel);

			scrollPane.setBounds(0, 0, 320, 150);
			commentTextField.setBounds(0, 160, 250, 40);
			writeButton.setBounds(270, 160, 50, 40);
			
			add(scrollPane);
			add(commentTextField);
			add(writeButton);
		}
		
		public String getCommentContent() {
			return commentTextField.getText();
		}
		
		public void clearCommentTextField() {
			commentTextField.setText("");
		}
		
		public void updateComments(List<Comment> comments) {
			JLabel commentLabel;
			String content;
			for(JLabel label : commentLabels) {
				remove(label);
				revalidate();
				repaint();
			}
			commentLabels.clear();
			
			for(int i = 0; i < comments.size(); i++) {
				content = comments.get(i).getContent() + "[@" + comments.get(i).getWriter() + ", "+comments.get(i).getWrittenDate().toGMTString() + "]";
				commentLabel = new JLabel(content);
				commentLabel.setPreferredSize(new Dimension(280, 40));
				commentLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
				addGrid(commentLabel, 0, i, 1, 1, 1, 0);
				commentLabels.add(commentLabel);
			}
			clearCommentTextField();
		}
		
		public void setWriteListener(ActionListener listener) {
			writeButton.addActionListener(listener);
		}
		
		private void addGrid(Component c, int gridx, int gridy, int gridwidth, int gridheight, int weightx, int weighty) {
			contstraints.gridx = gridx;
			contstraints.gridy = gridy;
			contstraints.gridwidth = gridwidth;
			contstraints.gridheight = gridheight;
			contstraints.weightx = weightx;
			contstraints.weighty = weighty;
			grid.setConstraints(c, contstraints);
			panel.add(c);
			panel.revalidate();
			panel.repaint();
		}
	}

	private class DisabledItemComboBoxModel<E> extends DefaultComboBoxModel<E> {
	    private Set<E> disabledItems = new HashSet<>();
	    
	    public DisabledItemComboBoxModel(E[] items, E[] disableds) {
	        super(items);
	        for(E e : disableds) disabledItems.add(e);
	    }

	    public boolean isDisabled(Object value) {
	        return disabledItems.contains(value);
	    }
	}

	private class DisabledItemComboBoxRenderer extends DefaultListCellRenderer {
	    private DisabledItemComboBoxModel<?> model;

	    public DisabledItemComboBoxRenderer(DisabledItemComboBoxModel<?> model) {
	        this.model = model;
	    }

	    @Override
	    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        if (model.isDisabled(value)) {
	            c.setForeground(Color.GRAY);
	        } else {
	            c.setForeground(Color.BLACK);
	        }
	        return c;
	    }
	}
}
