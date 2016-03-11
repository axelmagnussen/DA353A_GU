package library;

import java.util.Date;

import collections.AVLTree;
import media.Media;

public class LibraryMember {

	private String id;
	private String name;
	private String phone;
	private AVLTree<Date, Media> borrowedMedia;

	public LibraryMember(String id, String name, String phone, AVLTree<Date, Media> borrowedMedia) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.borrowedMedia = borrowedMedia;

	}
	
	public String getName() {
		return this.name;
	}

}
