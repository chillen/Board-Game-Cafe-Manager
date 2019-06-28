import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Genres extends JPanel {
	JList<String> genres;
	JList<String> games;
	
	public Genres() {
		setBorder(BorderFactory.createTitledBorder("Viewing Genres"));
		
		JLabel description = new JLabel("Click to view all games of that genre: ");
		final GridBagLayout g = new GridBagLayout();
		setLayout(g);
		final GridBagConstraints c = new GridBagConstraints();
		
		c.gridwidth=2;
		g.setConstraints(description, c);
		add(description);
		
		StorageSingleton db = StorageSingleton.getInstance();

		genres = new JList<String>(db.getGenres().toArray(new String[0]));
		games = new JList<String>();
		
		c.gridy=2;
		c.gridx=0;
		c.gridwidth=1;
		c.weightx=1;
		c.weighty=1;
		c.fill=c.BOTH;
		c.ipady=200;
		games.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		genres.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		g.setConstraints(genres, c);
		add(genres);
		
		c.gridx=1;
		g.setConstraints(games, c);
		add(games);
		
		ListSelectionListener lll = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				update();
			}
		};
		
		genres.addListSelectionListener(lll);
		DefaultListModel m = new DefaultListModel();
		for (String s : (db.getGenres().toArray(new String[0])))
			m.addElement(s);
		genres.setModel(m);
		
		update();
	}
	
	public void update() {
		StorageSingleton db = StorageSingleton.getInstance();
		DefaultListModel n = new DefaultListModel();
		
		for (String s : (db.getFromGenre(genres.getSelectedValue()).toArray(new String[0]))) {
			n.addElement(s);		
		}
		games.setModel(n);
	}
}
