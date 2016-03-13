package media;

/**
 * Class representing a DVD object.
 * @author Ninjakids
 */
public class DVD extends Media {

	private String[] actors;

	/**
	 * Create a new DVD media with the specified parameters.
	 * 
	 * @param id
	 *            Id of the media
	 * @param title
	 *            Title
	 * @param year
	 *            Year of publication
	 * @param actors
	 *            List of actors
	 * @param isBorrowed
	 *            If the media is borrowed
	 */
	public DVD(int id, String title, int year, String[] actors, boolean isBorrowed) {
		super(id, title, year, isBorrowed);
		this.actors = actors;
	}

	/**
	 * Gets list array of actors.
	 * @return actors
	 */
	public String[] getActors() {
		return actors;
	}
	
	/**
	 * Gets an appropriate string of all the actors.
	 * @return string of all actors
	 */
	public String getList() {
		String msg = "";
		for(int i = 0; i < actors.length; i++) {
			if(i+1 < actors.length) {
				msg += actors[i] + ", ";
			}
			else {
				msg += actors[i];
			}
		}
		return msg;
	}
	
	/**
	 * Returns an appropriate string of a book object.
	 * @return string
	 */
	@Override
	public String toString() {
		return "DVD\nId: " + super.getId() + "\nActor(s): " + this.getList() + "\nTitle: " 
				+ super.getTitle() + "\nYear: " 
				+ super.getYear() + "\nBorrowed? " + super.isBorrowed();
	}
}
