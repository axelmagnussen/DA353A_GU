package library;

import java.util.Date;
import java.util.Iterator;

import collections.AVLTree;
import media.Media;

/**
 * Class representing a member of the Library
 */
public class LibraryMember {

	private String id;
	private String name;
	private String phone;
	private AVLTree<Date, Media> borrowedMedia;

	/**
	 * Create a new LibraryMember with the specified data.
	 * 
	 * @param id
	 *            Member id
	 * @param name
	 *            Name of member
	 * @param phone
	 *            Phone number of media
	 * @param borrowedMedia
	 *            Initial collection of borrowed media
	 */
	public LibraryMember(String id, String name, String phone, AVLTree<Date, Media> borrowedMedia) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.borrowedMedia = borrowedMedia;
	}

	/**
	 * Put the media into the LibraryMembers collection of borrowed media as
	 * borrowed on the specified date.
	 * 
	 * @param date
	 *            Mark as borrowed at this date.
	 * @param media
	 *            The media to be marked as borrowed.
	 */
	void put(Date date, Media media) {
		borrowedMedia.put(date, media);
	}

	/**
	 * Remove the specified date from the collection of the LibrayMembers
	 * borrowed items.
	 * 
	 * @param date
	 *            Date to remove.
	 * @return The borrowed media.
	 */
	public Media remove(Date date) {
		return borrowedMedia.remove(date);
	}

	/**
	 * 
	 * @return Iterator over the dates of the LibraryMembers borrowed media.
	 */
	public Iterator<Media> iterator() {
		return borrowedMedia.iterator();
	}

	/**
	 * 
	 * @return The number of items in the LibraryMemebers collection of borrowed
	 *         media.
	 */
	int size() {
		return borrowedMedia.size();
	}

	/**
	 * 
	 * @return The name of the LibraryMember
	 */
	public String getName() {
		return this.name;
	}

}
