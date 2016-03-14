package library;

import java.util.Date;

import javax.swing.JOptionPane;

import collections.HashtableOH;
import media.Book;
import media.DVD;
import media.Media;

/**
 * Class that controls the flow of the application. 
 * @author Ninjakids
 * @since 2016-03-13
 */
public class LibraryController {

	private Library library;
	private LibraryMember currentUser;

	/**
	 * Constructor that initializes the library.
	 */
	public LibraryController() {
		library = new Library("src/files/Lantagare.txt", "src/files/Media.txt");
	}

	/**
	 * Method that checks if idNbr matches any of the library's
	 * members' IDs. If idNbr exists the user page is displayed.
	 * @param idNbr
	 */
	public void logIn(String idNbr) {

		if(idNbr.equalsIgnoreCase("k0bran")) {
			System.exit(0);
		}
		else if(library.memberExists(idNbr)) {
			this.setCurrentUser(library.getMember(idNbr));
			LibraryApp.showUserPage();
		} 
		else {
			JOptionPane.showMessageDialog(null, "User does not exist! Contact staff or try again.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Sets the current user who is currently logged in at the system.
	 * @param currentUser
	 */
	public void setCurrentUser(LibraryMember currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * Returns the user who is currently logged in at the system.
	 * @return currentUser
	 */
	public LibraryMember getCurrentUser() {
		return this.currentUser;
	}

	/**
	 * Method that checks if idNbr exists in library. If it does
	 * it records the current time and adds the media object associated
	 * with idNbr to the current user's borrowed media collection.
	 * @param idNbr
	 */
	public void borrow(int idNbr) {

		if(library.idExists(idNbr)) {
			Media media = library.getMedia(idNbr);
			if(media.isBorrowed()) {
				JOptionPane.showMessageDialog(null, "Media is already borrowed!", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				Date date = new Date();
				media.setBorrowDate(date);
				System.out.println(media);
				System.out.println(date);
				currentUser.put(date, media);
				LibraryApp.showUserPage();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Entered id is not associated with a media!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Method that logs the current user out of the system.
	 */
	public void logOut() {
		this.setCurrentUser(null);
		LibraryApp.showLoginPage();
	}

	/**
	 * Method that checks if idNbr exists and if the media associated with
	 * idNbr exists in the current user's borrowed media collection.
	 * @param idNbr
	 */
	public void returnMedia(int idNbr) {

		if(library.idExists(idNbr)) {
			Media media = library.getMedia(idNbr);
			if(!currentUser.hasMedia(media)) {
				JOptionPane.showMessageDialog(null, "You are not borrowing that media!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			else {
				Date date = media.getBorrowDate();
				media.setBorrowed(false);
				currentUser.remove(date);
				LibraryApp.showUserPage();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Entered id is not associated with a media!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Method that returns a hashtable of all media in library (used
	 * by SearchTable and UserPage).
	 * @return hashtable of all media
	 */
	public HashtableOH<Integer,Media> getAllMedia() {
		return library.getMedia();
	}

	/**
	 * Method that checks if idNbr exists and if it does it displays detailed
	 * information about the media associated with idNbr.
	 * @param idNbr
	 */
	public void showDetailedInfo(int idNbr) {

		if(library.idExists(idNbr)) {
			Media media = library.getMedia(idNbr);
			if(media.getType().equals("Book")) {
				Book book = (Book) media;
				JOptionPane.showMessageDialog(null, book.toString(), "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				DVD dvd = (DVD) media;
				JOptionPane.showMessageDialog(null, dvd.toString(), "Info", JOptionPane.INFORMATION_MESSAGE);
			}

		}
		else {
			JOptionPane.showMessageDialog(null, "Entered id is not associated with a media!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
