package library;

import java.util.Date;

import javax.swing.JOptionPane;

import collections.HashtableOH;
import media.Media;

public class LibraryController {

	private Library library;
	private LibraryMember currentUser;

	public LibraryController() {
		library = new Library("src/files/Lantagare.txt", "src/files/Media.txt");
	}

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

	public void setCurrentUser(LibraryMember currentUser) {
		this.currentUser = currentUser;
	}

	public LibraryMember getCurrentUser() {
		return this.currentUser;
	}

	public void borrow(int idNbr) 
	{
		if(library.idExists(idNbr)) 
		{
			Media media = library.getMedia(idNbr);
			if(media.isBorrowed())
			{
				JOptionPane.showMessageDialog(null, "Media is already borrowed!", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else 
			{
				Date date = new Date();
				media.setBorrowDate(date);
				System.out.println(media);
				System.out.println(date);
				currentUser.put(date, media);
				LibraryApp.showUserPage();
			}
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Entered id is not associated with a media!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void logOut() {
		LibraryApp.showLoginPage();
		this.setCurrentUser(null);
	}

	public void returnMedia(int idNbr) {
		Media media;
		if ((media = library.getMedia(idNbr)) != null) {
			Date date = media.getBorrowDate();
			media.setBorrowed(false);
			currentUser.remove(date);
			LibraryApp.showUserPage();

		} 
		else {
			JOptionPane.showMessageDialog(null, "Entered id is not associated with a media!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public HashtableOH<Integer,Media> getAllMedia() {
		return library.getMedia();
	}


}
