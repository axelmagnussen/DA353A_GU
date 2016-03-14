package library;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import collections.AVLTree;

public class Factory {
	/**
	 * Method that reads the file filename and stores the data (the members)
	 * in a data-structure called AVLTree.
	 * @param filename to be read
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	public static AVLTree<String, LibraryMember> readMembers(String filename, String encoding) throws UnsupportedEncodingException, FileNotFoundException {
		
		AVLTree<String, LibraryMember> members = new AVLTree<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), encoding))) {
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
			System.err.println(e);
		}
		return members;
	}
}
