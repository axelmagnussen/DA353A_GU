package library;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import collections.AVLTree;
import collections.HashtableOH;
import media.Book;
import media.DVD;
import media.Media;

/**
 * This class represents a library with media objects and library members. 
 * The media and the members are stored in two text-files, one for members 
 * and one for media objects.
 * 
 * The data-structures used for the library is an AVLTree for storing
 * library members and a HashtableOH for storing media objects. 
 * 
 * @author Ninjakids
 * @since 2016-03-13
 */
public class Library {
	
	private HashtableOH<Integer, Media> media;
	private AVLTree<String, LibraryMember> members;

	/**
	 * Constructor that initializes the main library with members and media
	 * objects.
	 * @param memberFile
	 * @param mediaFile
	 */
	public Library(String memberFile, String mediaFile) {
		try {
			members = Factory.readMembers(memberFile, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// Encoding fel?
		} catch (FileNotFoundException e) {
			// Kunde inte hitta filen
		}
		readMedia(mediaFile);
	}
	
	/**
	 * Returns true if a member's idNbr can be found in the library.
	 * @param idNbr
	 * @return false if idNbr could not be found
	 */
	public boolean memberExists(String idNbr) {
		if (members.contains(idNbr)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if idNbr could be found in the library (in the 
	 * HashtableOH).
	 * @param idNbr
	 * @return false if idNbr could not be found
	 */
	public boolean idExists(int idNbr) {
		if(media.containsKey(idNbr)) {
			return true;
		}
		return false;
	}

	/**
	 * Method that reads the file filename and stores the data (the members)
	 * in a data-structure called AVLTree.
	 * @param filename to be read
	 */
	private void readMembers(String filename) {
		
		members = new AVLTree<String, LibraryMember>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
			String[] parts;
			String text = br.readLine();

			String id, name, phone;

			while (text != null) {
				parts = text.split(";");
				id = parts[0];
				name = parts[1];
				phone = parts[2];

				members.put(id, new LibraryMember(id, name, phone));
				text = br.readLine();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Method that reads the file filename and stores the data (the medias)
	 * in a data-structure called HashtableOH.
	 * @param filename to be read
	 */
	public void readMedia(String filename) {
		
		media = new HashtableOH<Integer, Media>(40);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
			String[] parts;
			String text = br.readLine();

			String type, author, title;
			int id, year;

			while (text != null) {
				parts = text.split(";");
				type = parts[0];
				id = Integer.parseInt(parts[1]);

				if (type.equals("Bok")) {
					author = parts[2];
					title = parts[3];
					year = Integer.parseInt(parts[4]);

					media.put(id, new Book(id, author, title, year, false));
				} else if (type.equals("Dvd")) {
					title = parts[2];
					year = Integer.parseInt(parts[3]);

					String[] actors = new String[parts.length - 4];
					for (int i = 4, j = 0; i < parts.length; i++, j++) {
						actors[j] = parts[i];
					}
					media.put(id, new DVD(id, title, year, actors, false));
				}
				text = br.readLine();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Returns the HashtableOH with media.
	 * @return a hashtable with media
	 */
	public HashtableOH<Integer, Media> getMedia() {
		return media;
	}

	/**
	 * Returns the AVLTree with members.
	 * @return an AVLTree with members
	 */
	public AVLTree<String, LibraryMember> getMembers() {
		return members;
	}

	/**
	 * Return the member associated with idNbr.
	 * @param idNbr
	 * @return null if idNbr could not be found
	 */
	public LibraryMember getMember(String idNbr) {
		return members.get(idNbr);
	}
	
	/**
	 * Return the media object associated with idNbr.
	 * @param idNbr
	 * @return null if idNbr could not be found
	 */
	public Media getMedia(int idNbr) {
		return media.get(idNbr);
	}
}
