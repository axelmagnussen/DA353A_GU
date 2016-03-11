package library;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import collections.AVLTree;
import collections.HashtableOH;
import media.Book;
import media.DVD;
import media.Media;

public class Library {
	private static HashtableOH<Integer, Media> media;
	private static AVLTree<String, LibraryMember> members;

	public static boolean memberExists(String idNbr) {
		if (members.contains(idNbr)) {
			return true;
		}
		return false;
	}

	public static void readMembers(String filename) {
		members = new AVLTree<String, LibraryMember>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));) {
			String[] parts;
			String text = br.readLine();

			String id, name, phone;

			while (text != null) {
				parts = text.split(";");
				id = parts[0];
				name = parts[1];
				phone = parts[2];

				members.put(id, new LibraryMember(id, name, phone, null));
				text = br.readLine();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void readMedia(String filename) {
		media = new HashtableOH<Integer, Media>(40);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
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
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static HashtableOH<Integer, Media> getMedia() {
		return media;
	}

	public static AVLTree<String, LibraryMember> getMembers() {
		return members;
	}

}
