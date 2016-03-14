package library;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import collections.AVLTree;
import media.Media;

/**
 * This class represents the user's own user page where he/she can 
 * borrow, return, look up, display media objects. The class uses a MediaTable
 * to display the borrowed media and a SearchTable to display all media existing
 * in the library.
 * 
 * @author Ninjakids
 * @since 2016-03-13
 */
@SuppressWarnings("serial")
public class UserPage extends JPanel {

	private LibraryController controller;

	private JLabel welcomeXlbl = new JLabel();
	private JLabel txtFlbl = new JLabel("Media-ID:");
	private JLabel borrowedlbl = new JLabel("Borrowed Media:");

	private Font welcomeFont = new Font("Segoe UI", Font.BOLD, 20);
	private Font thisFont = new Font("Segoe UI", Font.PLAIN, 14);
	private Font boldFont = new Font("Segoe UI", Font.BOLD, 16);

	public JTextField mediaIDTxtF = new JTextField();

	public JButton infoBtn = new JButton("Show Detailed Information"); 
	public JButton borrowBtn = new JButton("Borrow");
	public JButton returnBtn = new JButton("Return");
	public JButton signOutBtn = new JButton("Sign Out");
	public JButton listBtn = new JButton("Library Media");

	private MediaTable mediaTable;

	private JScrollPane scroll; // Used to encapsulate the MediaTable

	private LibraryMember currentUser; // Current user logged in at the system

	/**
	 * Constructor that initializes the user page with various components
	 * such as buttons, tables, a controller, and labels.
	 * @param libC controller used for controlling the user page
	 */
	public UserPage(LibraryController libC) {

		this.controller = libC;
		currentUser = controller.getCurrentUser();

		// Initializes a MediaTable
		mediaTable = new MediaTable(currentUser.getBorrowedMedia());

		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(20,20,20,20));

		String message = controller.getCurrentUser().getName();
		welcomeXlbl.setText("Welcome " + message + "!");
		welcomeXlbl.setFont(welcomeFont);
		welcomeXlbl.setSize(400, 20);

		txtFlbl.setFont(thisFont);
		txtFlbl.setSize(60, 20);

		mediaIDTxtF.setColumns(20);

		// Panel with "Enter Media-ID"-label and text field
		JPanel inputPanel = new JPanel(); 
		inputPanel.add(txtFlbl);
		inputPanel.add(Box.createRigidArea(new Dimension(25,0))); // Adds empty space in inputPanel
		inputPanel.add(mediaIDTxtF);

		// "Show Detailed Info"
		JPanel infoPanel = new JPanel();
		infoPanel.add(infoBtn); 

		JPanel upperPanel = new JPanel(new GridLayout(3,1,0,20)); // rows, cols, hGap, vGap
		upperPanel.add(welcomeXlbl);
		upperPanel.add(inputPanel);
		upperPanel.add(infoPanel);

		this.addButtonListeners(); // layout + button listeners
		
		this.add(upperPanel, BorderLayout.NORTH);
		
		borrowedlbl.setFont(boldFont);
		
		scroll = new JScrollPane(mediaTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(540, 100));
		
		JPanel btnPanel = getButtonPanel();
		btnPanel.setBorder(new EmptyBorder(0,0,20,0));
		
		JPanel centerPanel = new JPanel();
		centerPanel.add(btnPanel);
		centerPanel.add(borrowedlbl);
		centerPanel.add(scroll);
		
		this.add(centerPanel, BorderLayout.CENTER);
		
		mediaTable.getSelectionModel().addListSelectionListener(new SelectionListener()); // adds action-listener to MediaTable
	}

	/**
	 * Method that sets button sizes but its main task is adding button listeners 
	 * to the buttons. Lambda expressions are used for adding actionListeners.
	 */
	private void addButtonListeners() {

		infoBtn.setPreferredSize(new Dimension(540, 35));
		infoBtn.addActionListener(e -> {	
			try {
				// trim used for deleting any leading and trailing white spaces
				controller.showDetailedInfo(Integer.parseInt(mediaIDTxtF.getText().trim()));

			} catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Media-id must be a number!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		borrowBtn.setPreferredSize(new Dimension(120, 35));
		borrowBtn.addActionListener(e -> {
			try {
				controller.borrow(Integer.parseInt(mediaIDTxtF.getText().trim()));

			} catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Media-id must be a number!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		returnBtn.setPreferredSize(new Dimension(120, 35));
		returnBtn.addActionListener(e -> {
			try {
				controller.returnMedia(Integer.parseInt(mediaIDTxtF.getText().trim()));

			} catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Media-id must be a number!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		signOutBtn.setPreferredSize(new Dimension(120, 35));
		signOutBtn.addActionListener(e -> {
			controller.logOut();
		});

		listBtn.setPreferredSize(new Dimension(120, 35));
		listBtn.addActionListener(e -> {
			LibraryApp.showLibrary(controller.getAllMedia());
		});
	}

	/**
	 * Method that returns a panel with buttons in it.
	 * @return JPanel with buttons
	 */
	private JPanel getButtonPanel() {

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(listBtn);
		buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
		buttonPanel.add(borrowBtn);
		buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
		buttonPanel.add(returnBtn);
		buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
		buttonPanel.add(signOutBtn);

		return buttonPanel;
	}

	/**
	 * This class is used for getting values from the MediaTable (JTable)
	 * when user clicks on a certain row. Whenever user clicks on a certain 
	 * row and that row does have values, an idNbr is set in the text-field
	 * mediaIDTxtF.
	 *  
	 * @author Ninjakids
	 * @since 2016-03-13
	 */
	private class SelectionListener implements ListSelectionListener {
		
		public void valueChanged(ListSelectionEvent e) {
			
			if (e.getValueIsAdjusting()) {
				return; // mouse button not released yet
			}
			int row = mediaTable.getSelectedRow();
			if (row < 0) {
				return; // true when clearSelection
			}
			Media selMedia = getSelectedMedia(row); // What media was clicked?

			if(selMedia != null) {
				mediaIDTxtF.setText(selMedia.getId() + "");
			}
			mediaTable.clearSelection();
		}
	}

	/**
	 * Method that returns media object associated with the value of row. An
	 * iterator (in order) is used for traversing, updating a local variable 
	 * index which will in the end return the correct media object.  
	 * @param row
	 * @return media object associated with row
	 */
	private Media getSelectedMedia(int row) {
		
		AVLTree<Date, Media> media = currentUser.getBorrowedMedia();
		if(media != null) {
			Iterator<Media> it = media.iterator();
			int index = 0;
			while(it.hasNext()) {
				Media current = it.next();
				if(index == row) {
					return current;
				} 
				else {
					index++;
				}
			}
		}
		return null;
	} 
}
