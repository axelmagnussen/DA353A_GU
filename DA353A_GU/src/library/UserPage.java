package library;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserPage extends JPanel implements ActionListener {
	private LibraryController libraryController;
	private JPanel pnl = new JPanel();
	private JLabel welcomeXlbl = new JLabel();
	private JLabel txtFlbl = new JLabel("Media ID:");
	private Font welcomeFont = new Font ("SansSerif", Font.PLAIN, 16);
	private Font thisFont = new Font ("SansSerif", Font.PLAIN, 14);
	public JTextField mediaIDTxtF = new JTextField();
	public JButton borrowBtn = new JButton("Borrow");
	public JButton returnBtn = new JButton("Return");
	public JButton signOutBtn = new JButton("Sign Out");
	
	public UserPage(LibraryController libC) {
		
		this.libraryController = libC;
		
		setLayout(null);
		
		String message = libraryController.getCurrentUser().getName();
		welcomeXlbl.setText("Welcome " + message);
		welcomeXlbl.setFont(welcomeFont);
		welcomeXlbl.setBounds(150, 20, 100, 25);
		welcomeXlbl.setSize(400,20);
		add(welcomeXlbl);
		
		txtFlbl.setFont(thisFont);
		txtFlbl.setBounds(30,60,80,25);
		txtFlbl.setSize(170,20);
		add(txtFlbl);
		
		mediaIDTxtF.setBounds(200,90,80,25);
		add(mediaIDTxtF);
		
		borrowBtn.setBounds(20, 120, 80, 25);
		borrowBtn.setSize(70, 30);
		add(borrowBtn);
		borrowBtn.addActionListener(this);
		
		returnBtn.setBounds(120,120, 80, 25);
		returnBtn.setSize(70, 30);
		add(returnBtn);
		returnBtn.addActionListener(this);
				
		signOutBtn.setBounds(200, 120, 80, 25);
		signOutBtn.setSize(70, 30);
		add(signOutBtn);
		signOutBtn.addActionListener(this);
	}

	
	public void actionPerformed(ActionEvent e) {
		
		
	}	
}
