package media;
/**
 * The class book is a scheme for how the different parameters are in
 * an order
 * @author Ninjakids
 *
 */
public class Book extends Media{
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
	
	public Book(int id, String author, String title, int year, boolean isBorrowed){
		
		super(id, title, year, isBorrowed);
	}
	
	

}
