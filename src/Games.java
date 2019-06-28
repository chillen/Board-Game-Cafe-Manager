import java.awt.*;
import javax.swing.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Games extends JPanel {
	JList<String> list;
	JLabel onlineRatings;

	public Games() {
		setBorder(BorderFactory.createTitledBorder("Viewing Games"));
		JLabel description = new JLabel("Click to view online ratings");
		onlineRatings = new JLabel("Online Rating: ");
		final GridBagLayout g = new GridBagLayout();
		setLayout(g);
		final GridBagConstraints c = new GridBagConstraints();
		
		c.gridwidth=2;
		g.setConstraints(description, c);
		add(description);
		c.gridx = 0;
		c.gridy = 1;
		g.setConstraints(onlineRatings, c);
		add(onlineRatings);
		// Get the list of all food
		
		StorageSingleton db = StorageSingleton.getInstance();
		
		list = new JList<String>((db.getGameList().toArray(new String[0])));
		
		c.gridy=2;
		c.weightx=1;
		c.weighty=1;
		c.gridheight=2;
		c.fill=c.BOTH;
		
		g.setConstraints(list, c);
		add(list);
		
		ListSelectionListener lll = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				update();
			}
		};
		
		list.addListSelectionListener(lll);
		DefaultListModel m = new DefaultListModel();
		for (String s : (db.getGameList().toArray(new String[0])))
			m.addElement(s);
		list.setModel(m);
		
		update();
	}
	public void update() {
		StorageSingleton db = StorageSingleton.getInstance();
		onlineRatings.setText("Online Rating: " + db.getOnlineRating(list.getSelectedValue()));
	}
}
