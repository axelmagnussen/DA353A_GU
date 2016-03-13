package library;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 
 * The class LogInPage creates a window for memebers to use when loging in to the libary
 * 
 * @author Ninjakids
 * 
 *
 */
@SuppressWarnings("serial")
public class LogInPage extends JPanel implements ActionListener {

	private JLabel welcomelbl = new JLabel("WELCOME!");
	private JLabel label = new JLabel("Enter ID:");
	private Font thisFont = new Font ("Segoe UI", Font.PLAIN, 16);
	public JTextField logInTxtF = new JTextField();
	public JButton logInBtn = new JButton("Sign In");

	private LibraryController libraryController;

	/**
	 * This is a constructor in which the textfield, button and labels are created and added
	 * @param lC
	 */
	public LogInPage(LibraryController lC) {

		logInTxtF.setText("681102-9999");
		this.libraryController = lC;

		this.setLayout(null);

		// Welcome
		welcomelbl.setFont(thisFont);
		welcomelbl.setBounds(165, 20, 80, 25);
		welcomelbl.setSize(400, 20);
		this.add(welcomelbl);

		// "Enter ID: "-text
		label.setFont(thisFont);
		label.setBounds(90, 70, 80, 25);
		label.setSize(180, 20);
		this.add(label);

		// Text field for input of id number
		logInTxtF.setBounds(160,70,160,25);
		this.add(logInTxtF);

		// Log in button
		logInBtn.setBounds(230, 100, 80, 25);
		logInBtn.setSize(90, 30);
		this.add(logInBtn);

		logInBtn.addActionListener(this);
	}

	/**
	 * This is a public method that logs in the user if he is a library member
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logInBtn) {
			libraryController.logIn(logInTxtF.getText().trim());
		}
	}
}
