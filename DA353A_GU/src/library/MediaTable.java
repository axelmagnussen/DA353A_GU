package library;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import collections.AVLTree;
import media.Book;
import media.Media;

/**
 * 
 * @author 
 *
 */
@SuppressWarnings("serial")
public class MediaTable extends JTable {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");
	@SuppressWarnings("unused")
	private AVLTree<Date, Media> media;

	private Font font = new Font("Segoe UI", Font.PLAIN, 16);

	public MediaTable(AVLTree<Date, Media> media) 
	{
		this.media = media;
		this.setFont(font);
		
		this.setModel(new DefaultTableModel() 
		{
			@Override
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		});
		DefaultTableModel model = (DefaultTableModel) getModel();

		// Create a couple of columns
		model.addColumn("Loan-Date");
		model.addColumn("Type");
		model.addColumn("ID");
		model.addColumn("Title");
		model.addColumn("Year");
		
		this.getColumnModel().getColumn(0).setPreferredWidth(110);
		this.getColumnModel().getColumn(1).setPreferredWidth(10);
		this.getColumnModel().getColumn(2).setPreferredWidth(30);
		this.getColumnModel().getColumn(3).setPreferredWidth(110);
		this.getColumnModel().getColumn(4).setPreferredWidth(30);

		if(media != null)
		{
			Iterator<Media> it = media.iterator();
			while (it.hasNext()) {
				Media next = it.next();
				
				// Append a row
				model.addRow(new Object[] {sdf.format(next.getBorrowDate()), next.getType(), next.getId(), next.getTitle(), next.getYear()});
			}
		}
		
		this.setFocusable(false);
		this.setRowSelectionAllowed(true);

		class CT extends DefaultTableCellRenderer 
		{
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				if (row%3 == 0) {
					setBackground(new Color(249, 255, 229));
				}
				else {
					setBackground(new Color(235, 247, 250));
				}

				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		}

		for (int i = 0; i < model.getColumnCount(); ++i) 
		{
			this.setDefaultRenderer(this.getColumnClass(i), new CT());
		}
	}

	public static void main(String[] args) 
	{
		AVLTree<Date, Media> media = new AVLTree<Date, Media>();
		for (int i = 0; i < 10; i++) 
		{
			Date date = new Date();
			Book book = new Book(784512, "Helena", "Wolf", 2016, true);
			book.setBorrowDate(date);
			media.put(book.getBorrowDate(), book);

			try 
			{
				Thread.sleep(1);
			} 
			catch(InterruptedException e) 
			{
				e.printStackTrace();
			}
		}

		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new JScrollPane(new MediaTable(media)));
		f.setSize(700, 480); // width, height
		f.setVisible(true);
		f.setLocationRelativeTo(null); // centers window
		f.setResizable(false);
	}
}