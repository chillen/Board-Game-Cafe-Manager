import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JPanel {
	private JButton admin 	= new JButton("Adminstration");
	private JButton food  	= new JButton("Food Management");
	private JButton user  	= new JButton("User Management");
	private JButton games  	= new JButton("Games Management");
	
	public MainFrame() {
		 this.setLayout(new GridLayout(2,2));
		 admin 	= new JButton("Adminstration");
		 food  	= new JButton("Food Management");
		 user  	= new JButton("User Management");
		 games  = new JButton("Games Management");
		 add(admin);
		 add(food);
		 add(user);
		 add(games);
	}
	
	public JButton getAdmin() 	{ return admin; }
	public JButton getFood() 	{ return food;  }
	public JButton getUser() 	{ return user;  }
	public JButton getGames() 	{ return games; }
	
}
