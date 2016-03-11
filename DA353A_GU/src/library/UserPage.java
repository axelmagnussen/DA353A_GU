package library;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class UserPage extends JPanel implements ActionListener {

	private LibraryController libraryController;
	private JLabel welcomeXlbl = new JLabel();
	private JLabel txtFlbl = new JLabel("Media ID:");
	private Font welcomeFont = new Font("SansSerif", Font.PLAIN, 16);
	private Font thisFont = new Font("SansSerif", Font.PLAIN, 14);
	public JTextField mediaIDTxtF = new JTextField();
	public JButton borrowBtn = new JButton("Borrow");
	public JButton returnBtn = new JButton("Return");
	public JButton signOutBtn = new JButton("Sign Out");

	public UserPage(LibraryController libC) {

		this.libraryController = libC;

		setLayout(null);

		String message = libraryController.getCurrentUser().getName();
		welcomeXlbl.setText("Welcome " + message + "!");
		welcomeXlbl.setFont(welcomeFont);
		welcomeXlbl.setBounds(110, 20, 100, 25);
		welcomeXlbl.setSize(400, 20);
		add(welcomeXlbl);

		txtFlbl.setFont(thisFont);
		txtFlbl.setBounds(100, 70, 80, 25);
		txtFlbl.setSize(60, 20);
		add(txtFlbl);

		mediaIDTxtF.setBounds(170, 70, 140, 25);
		add(mediaIDTxtF);

		borrowBtn.setBounds(50, 120, 90, 25);
		borrowBtn.setSize(90, 35);
		add(borrowBtn);
		borrowBtn.addActionListener(e -> {
			libraryController.borrow(Integer.parseInt(mediaIDTxtF.getText()));
		});

		returnBtn.setBounds(160, 120, 90, 35);
		returnBtn.setSize(90, 35);
		add(returnBtn);
		returnBtn.addActionListener(this);

		signOutBtn.setBounds(270, 120, 90, 35);
		signOutBtn.setSize(90, 35);
		add(signOutBtn);
		signOutBtn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

	}
}
