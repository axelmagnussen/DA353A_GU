package library;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import collections.HashtableOH;
import media.Media;

/**
 * This is the main class for starting the application. 
 * @author Ninjakids
 * @since 2016-03-13
 */
public class LibraryApp {

	private static JFrame frame;
	private static LibraryController lc;

	/**
	 * Main method that starts the application by initiates a controller
	 * and displays the login page.
	 * @param args
	 */
	public static void main(String[] args) {
		lc = new LibraryController();
		
		frame = new JFrame();
		showLoginPage();
	}
	
	/**
	 * Method that shows the login page.
	 */
	public static void showLoginPage() {
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setContentPane(new LogInPage(lc));
		frame.setSize(430, 200); // width, height
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); // centers window
		frame.setResizable(false);	
		
		frame.setIconImage(new ImageIcon("src/files/book.png").getImage());
	}

	/**
	 * Method that shows the user page.
	 */
	public static void showUserPage() {
		
		frame.setContentPane(new UserPage(lc)); 
		frame.setSize(600,500); 
		frame.setLocationRelativeTo(null);
		
		frame.repaint();            
		frame.revalidate();          		
	}

	/**
	 * Method that displays all media in a new window. 
	 * @param allMedia
	 */
	public static void showLibrary(HashtableOH<Integer, Media> allMedia) {
		
		JFrame libFrame = new JFrame("Library");
		libFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		libFrame.setContentPane(new JScrollPane(new SearchTable(allMedia)));
		libFrame.setSize(550, 400); // width, height
		libFrame.setVisible(true);
		libFrame.setLocation(1280,315); // centers window
		libFrame.setResizable(true);	
		
		libFrame.setIconImage(new ImageIcon("src/files/book.png").getImage());
	}
}
