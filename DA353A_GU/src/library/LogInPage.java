package library;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LogInPage extends JPanel implements ActionListener {

	private JLabel welcomelbl = new JLabel("WELCOME!");
	private JLabel label = new JLabel("Enter ID:");
	private Font thisFont = new Font ("SansSerif", Font.PLAIN, 14);
	public JTextField logInTxtF = new JTextField();
	public JButton logInBtn = new JButton("Sign In");

	private LibraryController libraryController;

	//Konstruktor
	public LogInPage(LibraryController lC) {

		logInTxtF.setText("681102-9999");
		this.libraryController = lC;

		this.setLayout(null);

		//Welcome
		welcomelbl.setFont(thisFont);
		welcomelbl.setBounds(165, 20, 80, 25);
		welcomelbl.setSize(400, 20);
		this.add(welcomelbl);

		//"Enter ID: "-texten
		label.setFont(thisFont);
		label.setBounds(90, 70, 80, 25);
		label.setSize(170, 20);
		this.add(label);

		//Textfält för att skriva in personnummer
		logInTxtF.setBounds(150,70,160,25);
		this.add(logInTxtF);

		//Inloggnigsknapp
		logInBtn.setBounds(220, 100, 80, 25);
		logInBtn.setSize(90, 30);
		this.add(logInBtn);

		logInBtn.addActionListener(this);

	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logInBtn) {
			libraryController.setUserInput(logInTxtF.getText());
		}
	}


	//Testsar
	public static void main(String[] args) {
		LibraryController lController = new LibraryController();
		LogInPage logInP = new LogInPage(lController);

		JFrame frame = new JFrame("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(logInP);
		frame.setSize(400, 190);
		frame.setVisible(true);
		frame.setLocation(500, 200);
	}
}
