package library;

import javax.swing.JOptionPane;

public class LibraryController 
{
	public LibraryController() {
		Library.readMedia("src/files/Media.txt");
		Library.readMembers("src/files/Lantagare.txt");
	}
	
	public void setUserInput(String idNbr) {
		if(Library.memberExists(idNbr)) 
		{
			JOptionPane.showMessageDialog(null, "User does exist :)", "Info", JOptionPane.INFORMATION_MESSAGE);
			LibraryApp.showUserPage();
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "User does not exist! Contact staff or try again.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}
