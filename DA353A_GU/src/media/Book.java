package media;
/**
 * This class represents a book-object.
 * @author Ninjakids
 */
public class Book extends Media {

	private String author;

	/**
	 * Create a new DVD media with the specified parameters.
	 * 
	 * @param id
	 *            Id of the media
	 * @param author
	 * 			  The author of the book
	 * @param title
	 *            Title
	 * @param year
	 * 
	 * @param isBorrowed
	 *            If the media is borrowed
	 */

	public Book(int id, String author, String title, int year, boolean isBorrowed) {
		super(id, title, year, isBorrowed);
		this.author = author;
	}

	/**
	 * Gets author of book.
	 * @return author of book
	 */
	public String getAuthor() {
		return this.author;
	}

	/**
	 * Returns a appropriate string of a book object.
	 * @return string
	 */
	@Override
	public String toString() {
		return "Book\nId: " + super.getId() + "\nAuthor: " + this.author + "\nTitle: " 
				+ super.getTitle() + "\nYear: " 
				+ super.getYear() + "\nBorrowed? " + super.isBorrowed();
	}
}
