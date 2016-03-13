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

@SuppressWarnings("serial")
public class SearchTable extends JTable {
	
	@SuppressWarnings("unused")
	private HashtableOH<Integer, Media> media;

	private Font font = new Font("Segoe UI", Font.PLAIN, 16);

	public SearchTable(HashtableOH<Integer, Media> media) 
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
		model.addColumn("ID");
		model.addColumn("Title");
		model.addColumn("Year");

		if(media != null)
		{
			Iterator<Integer> intIt = media.keys();

			Iterator<Media> medIt = media.values();
			while (medIt.hasNext()) {
				Media next = medIt.next();
				
				// Append a row
				model.addRow(new Object[] {intIt.next(), next.getId(), next.getTitle(), next.getYear()});
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
}

