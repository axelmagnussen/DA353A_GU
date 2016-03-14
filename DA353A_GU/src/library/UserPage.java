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

	public JButton infoBtn = new JButton("Show Detailed Information"); //NYTT
	public JButton borrowBtn = new JButton("Borrow");
	public JButton returnBtn = new JButton("Return");
	public JButton signOutBtn = new JButton("Sign Out");
	public JButton listBtn = new JButton("Library Media");

	private MediaTable mediaTable;

	private JScrollPane scroll;

	private LibraryMember currentUser;

	/**
	 * 
	 * @param libC
	 */
	public UserPage(LibraryController libC) {

		this.controller = libC;
		currentUser = controller.getCurrentUser();

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

		JPanel inputPanel = new JPanel();
		inputPanel.add(txtFlbl);
		inputPanel.add(Box.createRigidArea(new Dimension(25,0))); // Adds empty space in inputPanel
		inputPanel.add(mediaIDTxtF);

		JPanel infoPanel = new JPanel();
		infoPanel.add(infoBtn); //NYTT

		JPanel upperPanel = new JPanel(new GridLayout(3,1,0,20));
		upperPanel.add(welcomeXlbl);
		upperPanel.add(inputPanel);
		upperPanel.add(infoPanel); //NYTT

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
		
		mediaTable.getSelectionModel().addListSelectionListener(new SelectionListener());
	}

	private void addButtonListeners() {

		infoBtn.setPreferredSize(new Dimension(540, 35));
		infoBtn.addActionListener(e -> {	
			try {
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
	 * 
	 * @return
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
	 * 
	 * @author Ninjakids
	 * @since 2016-03-13
	 */
	private class SelectionListener implements ListSelectionListener 
	{
		public void valueChanged(ListSelectionEvent e) 
		{
			if (e.getValueIsAdjusting()) 
			{
				return; // mouse button not released yet
			}
			int row = mediaTable.getSelectedRow();
			if (row < 0) 
			{
				return; // true when clearSelection
			}
			System.out.println("Selected row: " + row);
			Media selMedia = getSelectedMedia(row);
			System.out.println("Selected media: " + selMedia);

			if(selMedia != null)
			{
				mediaIDTxtF.setText(selMedia.getId() + "");
			}
			mediaTable.clearSelection();
		}
	}

	/**
	 * 
	 * @param row
	 * @return
	 */
	private Media getSelectedMedia(int row) 
	{
		AVLTree<Date, Media> media = currentUser.getBorrowedMedia();
		if(media != null) 
		{
			Iterator<Media> it = media.iterator();
			int index = 0;
			while(it.hasNext()) 
			{
				Media current = it.next();
				if(index == row) 
				{
					return current;
				} 
				else 
				{
					index++;
				}
			}
		}
		return null;
	} 
}
