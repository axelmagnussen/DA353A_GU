package library;

import javax.swing.JFrame;

public class LibraryApp {

	private static JFrame frame;
	private static LibraryController lc;

	public static void main(String[] args) {
		lc = new LibraryController();
		showLoginPage();
	}
	
	private static void showLoginPage() {
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new LogInPage(lc));
		frame.setSize(400, 190); // width, height
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); // centers window
		frame.setResizable(false);		
	}

	public static void showUserPage() {
		
		frame.setContentPane(new UserPage(lc)); 
		frame.setSize(400,200); 
		frame.setLocationRelativeTo(null);

		frame.repaint();             
		frame.revalidate();          		
	}

}
