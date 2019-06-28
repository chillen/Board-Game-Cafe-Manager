import javax.swing.*;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * 
 * @author connorhillen
 * <p> The GameOn app assists Gaming Cafes with their inventory and
 * 	   customer services. 
 * <p> This application manages 10 different queries:
 * <ol>
 * 	<li> Adding a new customer
 * 	<li> Adding a new food item
 * 	<li> Adding a new board game
 * 	<li> Customer rating a board game
 * 	<li> Customer adding board game to favourites
 * 	<li> View games by genre
 * 	<li> View games by player count
 * 	<li> View food based on allergy restrictions
 * 	<li> Customer rating a food item
 * 	<li> Customer plan a meal within a budget
 * </ol>
 * <p> The interface is simple. Display a few buttons. Administration,
 * 	   Search Foods, Search Games, Manage Users. These each open dialogue
 *     boxes to perform different operations.
 */

public class GameOnApp extends JFrame {
	public GameOnApp(final String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		GridBagLayout g = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill=constraints.BOTH;
		setLayout(g);
		constraints.weightx=1;
		JButton viewGames = new JButton("View Games List");
		JButton viewFood = new JButton("View Food List");
		JButton viewCustomers = new JButton("View Customer List");
		JButton viewAllergens = new JButton("Allergy Check");
		JButton viewGenres = new JButton("Genres");

		constraints.gridx = 1; constraints.gridy = 0;
		g.setConstraints(viewGames, constraints);
		add(viewGames);

		constraints.gridx = 2; constraints.gridy = 0;
		g.setConstraints(viewFood, constraints);
		add(viewFood);
		
		constraints.gridx = 3; constraints.gridy = 0;
		g.setConstraints(viewCustomers, constraints);
		add(viewCustomers);
		
		constraints.gridx = 4; constraints.gridy = 0;
		g.setConstraints(viewAllergens, constraints);
		add(viewAllergens);
		
		constraints.gridx = 5; constraints.gridy = 0;
		g.setConstraints(viewGenres, constraints);
		add(viewGenres);
		
		final CardLayout c = new CardLayout();
		final JPanel j = new JPanel();
		j.setLayout(c);
		final Food food = new Food();
		final Customers customers = new Customers();
		final Games games = new Games();
		final Allergies allergies = new Allergies();
		final Genres genres = new Genres();
		j.add("viewGames", games);
		j.add("viewFood", food);
		j.add("viewCustomers", customers);
		j.add("viewAllergens", allergies);
		j.add("viewGenres", genres);
		
		constraints.gridx = 0; constraints.gridy = 1;
		constraints.gridwidth=6; constraints.gridheight=5;
		constraints.weighty=1; constraints.fill=constraints.BOTH;
		g.setConstraints(j,constraints);
		add(j);
		
		viewFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.update();
				c.show(j,"viewFood");
			}
		});
		viewCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customers.update();
				c.show(j,"viewCustomers");
			}
		});
		viewGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				games.update();
				c.show(j,"viewGames");
			}
		});	
		viewAllergens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.show(j,"viewAllergens");
			}
		});
		viewGenres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.show(j,"viewGenres");
			}
		});
		pack();
	}
	
	public static void main(String[] args) {
		GameOnApp app = new GameOnApp("Game ON! Gaming Cafe Management");
		
		app.setVisible(true);
	}
}
