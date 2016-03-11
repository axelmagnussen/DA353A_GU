package library;

import java.util.Date;

import javax.swing.JOptionPane;

import media.Media;

public class LibraryController {
    private Library library;
    private LibraryMember currentUser;

    public LibraryController() {
        library = new Library("src/files/Lantagare.txt", "src/files/Media.txt");
    }

    public void setUserInput(String idNbr) {
        if (library.memberExists(idNbr)) {
            this.setCurrentUser(library.getMember(idNbr));

            JOptionPane.showMessageDialog(null, "User does exist :)", "Info", JOptionPane.INFORMATION_MESSAGE);
            LibraryApp.showUserPage();
        } else {
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

    public void borrow(int idNbr) {

        Media media;
        if ((media = library.getMedia(idNbr)) != null) {
            // if (media.isBorrowed()) {
            // JOptionPane.showMessageDialog(null, "Media is already borrowed!",
            // "Error", JOptionPane.ERROR_MESSAGE);
            // }
            // Date date = new Date();
            // media.setBorrowDate(date);
            // media.setBorrowed(true);
            // currentUser.put(date, media);
            currentUser.put(new Date(), media);
        } else {
            JOptionPane.showMessageDialog(null, "Entered id is not associated with a media!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // public void unBorrow(int idNbr) {
    // Media media;
    // if ((media = library.getMedia(idNbr)) != null) {
    // Date date = media.getBorrowDate();
    // media.setBorrowed(false);
    // currentUser.remove(date);
    // } else {
    // JOptionPane.showMessageDialog(null, "Entered id is not associated with a
    // media!", "Error",
    // JOptionPane.ERROR_MESSAGE);
    // }
    // }

}
