package test;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Test {

	public static void main(String[] args) {
		JFrame frm = new JFrame();
		frm.setPreferredSize(new Dimension(300, 100));
		frm.setLayout(new BorderLayout());
		JTextField tf = new JTextField();
		tf.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// Sök efter vad som har skrivits in och uppdatera?
				System.out.println(tf.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}

		});
		frm.add(tf, BorderLayout.CENTER);
		frm.pack();
		frm.setVisible(true);

	}
}
