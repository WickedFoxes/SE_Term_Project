package main.view;

import javax.swing.JFrame;

public class SwingView extends JFrame{
	public SwingView(int x, int y) {
        super("Issue Handle Program");
        setSize(x, y);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
