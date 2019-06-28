import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Customers extends JPanel {
	JList<String> customers;
	JList<String> favourite_food;
	JList<String> favourite_games;
	
	public Customers() {
		setBorder(BorderFactory.createTitledBorder("Viewing Customers"));
		
		JLabel description = new JLabel("Click to view all details of the customer: ");
		final GridBagLayout g = new GridBagLayout();
		setLayout(g);
		final GridBagConstraints c = new GridBagConstraints();
		
		c.gridwidth=2;
		g.setConstraints(description, c);
		add(description);
		
		StorageSingleton db = StorageSingleton.getInstance();

		customers = new JList<String>(db.getCustomerList().toArray(new String[0]));
		favourite_food = new JList<String>();
		favourite_games = new JList<String>();
		
		customers.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		favourite_food.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		favourite_games.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		c.gridy=1;
		c.gridx=0;
		c.gridwidth=1;
		c.gridheight=4;
		c.weightx=1;
		c.weighty=1;
		c.fill=c.BOTH;

		g.setConstraints(customers, c);
		add(customers);
		c.gridheight=1;
		c.ipady=1;
		c.gridx=1;
		c.gridy=1;
		
		JLabel ffood = new JLabel("Favourite Food:");
		g.setConstraints(ffood, c);
		add(ffood);
		
		c.ipady=100;
		c.gridx=1;
		c.gridy=2;
		g.setConstraints(favourite_food, c);
		add(favourite_food);
		
		c.gridx=1;
		c.gridy=3;
		c.ipady=1;

		JLabel fgames = new JLabel("Favourite Games:");
		g.setConstraints(fgames, c);
		add(fgames);
		
		c.gridx=1;
		c.gridy=4;
		c.ipady=100;

		g.setConstraints(favourite_games, c);
		add(favourite_games);
		
		ListSelectionListener lll = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				update();
			}
		};
		
		customers.addListSelectionListener(lll);
		DefaultListModel m = new DefaultListModel();
		for (String s : (db.getCustomerList().toArray(new String[0])))
			m.addElement(s);
		customers.setModel(m);
		
		update();
	}
	
	public void update() {
		StorageSingleton db = StorageSingleton.getInstance();
		DefaultListModel n = new DefaultListModel();
		DefaultListModel m = new DefaultListModel();
		
		for (String s : (db.getFavouriteFood(customers.getSelectedValue()).toArray(new String[0]))) {
			n.addElement(s);		
		}
		for (String s : (db.getFavouriteGames(customers.getSelectedValue()).toArray(new String[0]))) {
			m.addElement(s);		
		}
		favourite_food.setModel(n);
		favourite_games.setModel(m);
	}
}
