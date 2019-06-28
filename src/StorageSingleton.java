import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class StorageSingleton {
	private static StorageSingleton instance;
	private Connection db;
	
	// Avoid allowing public creation outside of the getInstance()
	private StorageSingleton() {
		// Setup the database
		try {
			Class.forName("org.sqlite.JDBC");
				
			db = DriverManager.getConnection("jdbc:sqlite:db_gameon");					
		}
		catch (Exception e) {
			e.printStackTrace();
			return ;
		}
	}
	
	public static StorageSingleton getInstance() {
		if(instance == null)
			instance = new StorageSingleton();
		return instance;
	}
	
	public ArrayList<String> getFoodList() {
		ArrayList<String> l = new ArrayList<String>();
		try {
			Statement s = db.createStatement();
			ResultSet rs = s.executeQuery("SELECT name FROM food_items");
			
			while(rs.next()) 
				l.add(rs.getString("name"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return l;
	}
	
	public ArrayList<String> getGameList() {
		ArrayList<String> l = new ArrayList<String>();
		try {
			Statement s = db.createStatement();
			ResultSet rs = s.executeQuery("SELECT title FROM board_games");
			
			while(rs.next()) 
				l.add(rs.getString("title"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return l;
	}
	
	public ArrayList<String> getCustomerList() {
		ArrayList<String> l = new ArrayList<String>();
		try {
			Statement s = db.createStatement();
			ResultSet rs = s.executeQuery("SELECT name FROM customers");
			
			while(rs.next()) 
				l.add(rs.getString("name"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return l;
	}
	
	public ArrayList<String> getFoodWithoutAllergen(String allergen) {
		ArrayList<String> l = new ArrayList<String>();
		try {
			Statement s = db.createStatement();
			ResultSet rs = s.executeQuery("SELECT name FROM food_items EXCEPT SELECT name FROM food_items INNER JOIN allergies ON food_items.upc = allergies.upc AND allergen LIKE '"+allergen+"'");
			
			while(rs.next()) 
				l.add(rs.getString("name"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return l;
	}
	public ArrayList<String> getGenres() {
		ArrayList<String> l = new ArrayList<String>();
		try {
			Statement s = db.createStatement();
			ResultSet rs = s.executeQuery("SELECT DISTINCT genre FROM genre");
			
			while(rs.next()) 
				l.add(rs.getString("genre"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return l;
	}
	public ArrayList<String> getFromGenre(String genre) {
		ArrayList<String> l = new ArrayList<String>();
		try {
			Statement s = db.createStatement();
			ResultSet rs = s.executeQuery("SELECT title FROM genre INNER JOIN board_games ON board_games.upc = genre.upc AND genre='"+genre+"'");
			
			while(rs.next()) 
				l.add(rs.getString("title"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return l;
	}

	public ArrayList<String> getFavouriteFood(String selectedValue) {
		ArrayList<String> l = new ArrayList<String>();
		try {
			Statement s = db.createStatement();
			ResultSet rs = s.executeQuery("SELECT food_items.name FROM favourite_food INNER JOIN food_items ON favourite_food.upc = food_items.upc INNER JOIN customers ON customers.email = favourite_food.email AND customers.name='"+selectedValue+"'");
			
			while(rs.next()) 
				l.add(rs.getString("name"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return l;
	}

	public ArrayList<String> getFavouriteGames(String selectedValue) {
		ArrayList<String> l = new ArrayList<String>();
		try {
			Statement s = db.createStatement();
			ResultSet rs = s.executeQuery("SELECT board_games.title FROM favourite_game INNER JOIN board_games ON favourite_game.upc = board_games.upc INNER JOIN customers ON customers.email = favourite_game.email AND customers.name='"+selectedValue+"'");
			
			while(rs.next()) 
				l.add(rs.getString("title"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return l;
	}
	
	// Currently assuming only one source
	public String getOnlineRating(String selectedValue) {
		ArrayList<String> l = new ArrayList<String>();
		try {
			Statement s = db.createStatement();
			ResultSet rs = s.executeQuery("SELECT rating FROM online_rating INNER JOIN board_games ON online_rating.upc = board_games.upc AND board_games.title='"+selectedValue+"'");
			
			while(rs.next()) 
				l.add(rs.getString("rating"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return (l.size() > 0) ? l.get(0) : ""; 
	}
}
