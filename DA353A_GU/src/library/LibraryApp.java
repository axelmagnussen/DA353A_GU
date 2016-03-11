package library;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class LibraryApp {

	private static JFrame frame;
	private static LibraryController lc;

	public static void main(String[] args) {
		lc = new LibraryController();
		
		frame = new JFrame();
		showLoginPage();
	}
	
	public static void showLoginPage() {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new LogInPage(lc));
		frame.setSize(430, 200); // width, height
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); // centers window
		frame.setResizable(false);	
		
		frame.setIconImage(new ImageIcon("src/files/book.png").getImage());
	}

	public static void showUserPage() {
		
		frame.setContentPane(new UserPage(lc)); 
		frame.setSize(500,400); 
		frame.setLocationRelativeTo(null);

		frame.repaint();             
		frame.revalidate();          		
	}
}
