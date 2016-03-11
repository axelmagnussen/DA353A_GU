package media;

/**
 * Class representing a DVD
 */
public class DVD extends Media {

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
    }
}
