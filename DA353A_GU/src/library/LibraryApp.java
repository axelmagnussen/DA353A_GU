package library;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import collections.HashtableOH;
import media.Media;

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
		frame.setSize(600,400); 
		frame.setLocationRelativeTo(null);

		frame.repaint();             
		frame.revalidate();          		
	}

	public static void showLibrary(HashtableOH<Integer, Media> allMedia) {
		JFrame libFrame = new JFrame("Library");
		libFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		libFrame.setContentPane(new JScrollPane (new SearchTable(allMedia)));
		libFrame.setSize(500, 400); // width, height
		libFrame.setVisible(true);
		libFrame.setLocation(1280,315); // centers window
		libFrame.setResizable(false);	
		
		libFrame.setIconImage(new ImageIcon("src/files/book.png").getImage());
	}
}
