package library;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import collections.HashtableOH;
import media.Media;

/**
 * This class represents a table (JTable) with media-objects in it.
 * @author Ninjakids
 */
@SuppressWarnings("serial")
public class SearchTable extends JTable {
	
	private Font font = new Font("Segoe UI", Font.PLAIN, 16);

	/**
	 * 
	 * @param media
	 */
	public SearchTable(HashtableOH<Integer, Media> media) 
	{
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
		model.addColumn("Type");
		model.addColumn("ID");
		model.addColumn("Title");
		model.addColumn("Year");
		model.addColumn("Borrowed?");
		
		this.getColumnModel().getColumn(0).setPreferredWidth(1);
		this.getColumnModel().getColumn(1).setPreferredWidth(1);
		this.getColumnModel().getColumn(2).setPreferredWidth(250);
		this.getColumnModel().getColumn(3).setPreferredWidth(1);
		this.getColumnModel().getColumn(4).setPreferredWidth(1);

		if(media != null)
		{
			Iterator<Media> medIt = media.values();
			while (medIt.hasNext()) {
				Media next = medIt.next();
				
				// Append a row
				model.addRow(new Object[] {next.getType(next), next.getId(), next.getTitle(), next.getYear(), next.isBorrowed()});
			}
		}
		
		this.setFocusable(false);
		this.setRowSelectionAllowed(true);

		/**
		 * This class is used for changing color in the JTable.
		 * @author Ninjakids
		 */
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
}
