package library;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LogInPage extends JFrame implements ActionListener {
	private JPanel thePanel = new JPanel();
	private JLabel label = new JLabel("Ange personnummer:");
	private Font thisFont = new Font ("SansSerif", Font.PLAIN, 14);
	public JTextField logInTxtF = new JTextField();
	public JButton logInBtn = new JButton("Logga In");
	
	private LibraryController libraryController;
	
	//Konstruktor
	public LogInPage(LibraryController lC) {

		this.libraryController = lC;

		thePanel.setLayout(null);

		//"Ange personnummer: "-texten
		label.setFont(thisFont);
		label.setBounds(40, 40, 80, 25);
		label.setSize(170, 20);
		thePanel.add(label);
		
		//Textfält för att skriva in personnummer
		logInTxtF.setBounds(180,40,160,25);
		thePanel.add(logInTxtF);
		
		//Inloggnigsknapp
		logInBtn.setBounds(250, 80, 80, 25);
		logInBtn.setSize(90, 30);
		thePanel.add(logInBtn);
		
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
		
		JFrame frame = new JFrame("Inloggning");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(logInP.thePanel);
		frame.setSize(400, 190);
		frame.setVisible(true);
		frame.setLocation(500, 200);
	}
}
