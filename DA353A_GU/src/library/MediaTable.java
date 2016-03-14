package library;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import collections.AVLTree;
import media.Media;

/**
 * This class represents a table (JTable) with borrowed media objects in it.
 * @author Ninjakids
 * @since 2016-03-13
 */
@SuppressWarnings("serial")
public class MediaTable extends JTable {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");

	private Font font = new Font("Segoe UI", Font.PLAIN, 16);

	/**
	 * Constructor that initiates a JTable with five columns.
	 * @param media
	 */
	public MediaTable(AVLTree<Date, Media> media) 
	{
		this.setFont(font);

		// Makes sure that user cannot edit values in the JTable
		this.setModel(new DefaultTableModel() 
		{
			@Override
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		});
		DefaultTableModel model = (DefaultTableModel) getModel();

		// Creates a couple of columns
		model.addColumn("Loan-Date");
		model.addColumn("Type");
		model.addColumn("ID");
		model.addColumn("Title");
		model.addColumn("Year");

		// Sets widths of the various columns
		this.getColumnModel().getColumn(0).setPreferredWidth(110);
		this.getColumnModel().getColumn(1).setPreferredWidth(10);
		this.getColumnModel().getColumn(2).setPreferredWidth(30);
		this.getColumnModel().getColumn(3).setPreferredWidth(110);
		this.getColumnModel().getColumn(4).setPreferredWidth(30);

		// Creates rows with data in them and adds them to the JTable
		if(media != null)
		{
			Iterator<Media> it = media.iterator();
			while (it.hasNext()) {
				Media next = it.next();

				// Appends a row
				model.addRow(new Object[] {sdf.format(next.getBorrowDate()), next.getType(), next.getId(), next.getTitle(), next.getYear()});
			}
		}

		this.setFocusable(false);
		this.setRowSelectionAllowed(true); // Rows -> click-able

		/**
		 * This class is used for changing color in the JTable (only way
		 * to change a JTable's rows and columns' color).
		 * @author Ninjakids
		 * @since 2016-03-13
		 */
		class CT extends DefaultTableCellRenderer {

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

		// Adds the coloring option to each column.
		for (int i = 0; i < model.getColumnCount(); ++i) {
			this.setDefaultRenderer(this.getColumnClass(i), new CT());
		}
	}
}