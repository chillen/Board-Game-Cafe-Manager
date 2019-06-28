import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.*;

public class Allergies extends JPanel {
	JList<String> list;
	JTextField allerg;
	
	public Allergies() {
		setBorder(BorderFactory.createTitledBorder("Viewing Allergens"));
		JLabel description = new JLabel("Type an allergen below to find all food without it. ");
		JLabel all = new JLabel("Allergen (lactose or gluten or peanuts): ");
		JButton sub = new JButton("Submit");
		allerg = new JTextField();
		final GridBagLayout g = new GridBagLayout();
		setLayout(g);
		final GridBagConstraints c = new GridBagConstraints();
		
		c.gridwidth=2;
		g.setConstraints(description, c);
		add(description);
		
		c.gridwidth=1;
		c.gridx=0; c.gridy=1; c.fill=c.BOTH;
		g.setConstraints(all, c);
		add(all);
		
		c.gridx=1; c.gridy=1;
		g.setConstraints(allerg, c);
		add(allerg);
		
		StorageSingleton db = StorageSingleton.getInstance();

		list = new JList<String>();
		
		c.gridy=2;
		c.gridx=0;
		c.gridwidth=2;
		c.weightx=1;
		c.weighty=1;
		c.fill=c.BOTH;
		c.ipady=200;
		g.setConstraints(list, c);
		add(list);
		
		c.ipady=0;
		c.fill=c.HORIZONTAL;
		c.gridy=3;
		g.setConstraints(sub, c);
		add(sub);
		
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();				
			}
		});
	}
	
	public void update() {
		StorageSingleton db = StorageSingleton.getInstance();
		list.removeAll();
		DefaultListModel m = new DefaultListModel();
		for (String s : (db.getFoodWithoutAllergen(allerg.getText()).toArray(new String[0])))
			m.addElement(s);
		list.setModel(m);
	}
}
