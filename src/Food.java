import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.*;
import javax.swing.*;

import javax.swing.*;

public class Food extends JPanel {
	JList<String> list;
	public Food() {
		setBorder(BorderFactory.createTitledBorder("Viewing Food"));
		final GridBagLayout g = new GridBagLayout();
		setLayout(g);
		final GridBagConstraints c = new GridBagConstraints();
		
		
		// Get the list of all food
		
		StorageSingleton db = StorageSingleton.getInstance();
		
		list = new JList<String>((db.getFoodList().toArray(new String[0])));
		
		c.gridy=1;
		c.weightx=1;
		c.weighty=1;
		c.fill=c.BOTH;
		g.setConstraints(list, c);
		add(list);
	}
	
	public void update() {
		StorageSingleton db = StorageSingleton.getInstance();
		list.removeAll();
		DefaultListModel m = new DefaultListModel();
		for (String s : (db.getFoodList().toArray(new String[0])))
			m.addElement(s);
		list.setModel(m);
	}
}
