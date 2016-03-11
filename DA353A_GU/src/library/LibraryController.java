package library;

import javax.swing.JOptionPane;

public class LibraryController 
{
	private Library library;
	private LibraryMember currentUser;

	public LibraryController() {
		library = new Library("src/files/Lantagare.txt", "src/files/Media.txt");
	}
	
	public void setUserInput(String idNbr) {
		if(library.memberExists(idNbr)) 
		{
			this.setCurrentUser(library.getMember(idNbr));
			
			JOptionPane.showMessageDialog(null, "User does exist :)", "Info", JOptionPane.INFORMATION_MESSAGE);
			LibraryApp.showUserPage();
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "User does not exist! Contact staff or try again.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void setCurrentUser(LibraryMember currentUser) {
		this.currentUser = currentUser;
	}

	public LibraryMember getCurrentUser() {
		return this.currentUser;
	}
}
