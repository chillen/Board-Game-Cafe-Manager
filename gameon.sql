----------------------------------------------
--Data for the Gaming Cafe Management System
--Connor Hillen, 100852453
--2014-12-08
----------------------------------------------

-- Drop any of my tables if they exist

drop table if exists customers;
drop table if exists food_items;
drop table if exists allergies;
drop table if exists board_games;
drop table if exists customer_food_rating;
drop table if exists customer_game_rating;
drop table if exists online_rating;
drop table if exists genre;
drop table if exists favourite_food;
drop table if exists favourite_game;

--Setup schemas 


--customers

create table if not exists customers(
      email text primary key NOT NULL,
      name text NOT NULL
);
--food_items
create table if not exists food_items(
      upc integer primary key NOT NULL,
      description text NOT NULL,
      name text NOT NULL,
      quantity integer NOT NULL,
      price real NOT NULL,
      category text NOT NULL
);
--allergies
create table if not exists allergies(
	  upc integer NOT NULL,
      allergen text NOT NULL,
	  foreign key(upc) references food_items(upc),
	  primary key (upc,allergen)
);
--board_game
create table if not exists board_games(
      acquisition_date date NOT NULL,
      playing_time integer, -- number of minutes it takes to play, rounded up
	  max_players integer, 
	  min_players integer,
	  title text NOT NULL,
	  upc integer primary key NOT NULL,
	  publisher text,
	  suggested_age integer, -- suggested age to play, minimum
	  description text,
	  publish_year integer
);
--customer_food_rating
create table if not exists customer_food_rating(
	email text NOT NULL,
	upc integer NOT NULL,
	rating integer NOT NULL, -- Some rating [0,10]
	foreign key(email) references customers(email),
	foreign key(upc) references food_items(upc),
	primary key (upc,email)
);
--customer_game_rating
create table if not exists customer_game_rating(
	email text NOT NULL,
	upc integer NOT NULL,
	rating integer NOT NULL, -- Some rating [0,10]
	foreign key(email) references customers(email),
	foreign key(upc) references board_games(upc),
	primary key (upc,email)
);
--online_rating
create table if not exists online_rating(
	source text NOT NULL,
	upc integer NOT NULL,
	rating integer NOT NULL, -- Some rating [0,10]
	foreign key(upc) references board_games(upc)
	primary key (source,upc)
);
--genre
create table if not exists genre(
      genre text NOT NULL,
	  upc integer NOT NULL,
	  foreign key(upc) references board_games(upc),
	  primary key (genre,upc)
);
--favourite_food
create table if not exists favourite_food(
	email text NOT NULL,
	upc integer NOT NULL,
	foreign key(email) references customers(email),
	foreign key(upc) references food_items(upc),
	primary key (email, upc)
);
--favourite_game
create table if not exists favourite_game(
	email text NOT NULL,
	upc integer NOT NULL,
	foreign key(email) references customers(email),
	foreign key(upc) references board_games(upc),
	primary key (email, upc)
);



begin transaction;

-- A few dummy customers
INSERT INTO customers(email, name) VALUES('bobby@1ups.com', 'Grandalf');
INSERT INTO customers(email, name) VALUES('jim@darkmagic.com', 'Jim Darkmagic');
INSERT INTO customers(email, name) VALUES('trog@dor.com', 'The Burninator');
INSERT INTO customers(email, name) VALUES('rawr@sauce.com', 'The Dragon');

-- Insert food items
INSERT INTO food_items(name, description, quantity, price, category, upc) VALUES('Spring Rolls', 'Crisp veggies in flaky shell', 3, 8.69, 'Appetizer', 1112345679);
INSERT INTO food_items(name, description, quantity, price, category, upc) VALUES('Hummus Plate', 'Served with pita and red peppers', 8, 7.39, 'Appetizer', 1112628679);
INSERT INTO food_items(name, description, quantity, price, category, upc) VALUES('Pepperoni', 'Tomato sauce, Pepperoni, Mozzarella', 1, 8.99, 'Flatbread Pizza', 112345659);
INSERT INTO food_items(name, description, quantity, price, category, upc) VALUES('Sweet Chili Chicken', 'Sweet Chili sauce, Chicken breast, Red Onions, Red Peppers, Mozzarella', 1, 9.99, 'Flatbread Pizza', 119345659);
INSERT INTO food_items(name, description, quantity, price, category, upc) VALUES('Italian Chicken', 'Pesto, chicken, sundried tomatoes, arugula, cheese', 1, 10.99, 'Sandwich', 1612245679);
INSERT INTO food_items(name, description, quantity, price, category, upc) VALUES('Espresso', 'Warm shot of energy.', 1, 2.59, 'Beverage', 123225679);
INSERT INTO food_items(name, description, quantity, price, category, upc) VALUES('Juice', 'Orange, cranberry', 1, 3.49, 'Beverage', 11129186679);
INSERT INTO food_items(name, description, quantity, price, category, upc) VALUES('Soft Drink', 'Coke, Sprite, Ginger Ale, Iced Tea', 1, 2.89, 'Beverage', 1185686679);

-- Manage allergies for those food items
INSERT INTO allergies(upc, allergen) VALUES(1112345679, 'Gluten');
INSERT INTO allergies(upc, allergen) VALUES(1112345679, 'Peanut');
INSERT INTO allergies(upc, allergen) VALUES(1112628679, 'Gluten');
INSERT INTO allergies(upc, allergen) VALUES(119345659, 'Gluten');
INSERT INTO allergies(upc, allergen) VALUES(119345659, 'Lactose');
INSERT INTO allergies(upc, allergen) VALUES(1612245679, 'Lactose');
INSERT INTO allergies(upc, allergen) VALUES(1612245679, 'Gluten');

-- Manage favourite foods and ratings
INSERT INTO customer_food_rating(upc,rating,email) VALUES(119345659, 10, "jim@darkmagic.com");
INSERT INTO customer_food_rating(upc,rating,email) VALUES(119345659, 9, "trog@dor.com");
INSERT INTO favourite_food(upc,email) VALUES(119345659, "trog@dor.com");
INSERT INTO favourite_food(upc,email) VALUES(123225679, "jim@darkmagic.com");
INSERT INTO customer_food_rating(upc,rating,email) VALUES(123225679, 3, "bobby@1ups.com");


-- A few board games
INSERT INTO board_games(title, description, publisher, publish_year, suggested_age, playing_time, min_players, max_players, UPC, acquisition_date) VALUES("Dixit Odyssey", "Game play in Dixit Odyssey matches that of Dixit: Each turn one player is the storyteller. This player secretly chooses one card in his hand, then gives a word or sentence to describe this card – but not too obviously. Each other player chooses a card in hand that matches this word/sentence and gives it to the storyteller.", "ADC Blackfire Entertainment", 2011, 8, 30, 4,12, 3558380016168, '2014-01-01 00:00:00');
INSERT INTO board_games(title, description, publisher, publish_year, suggested_age, playing_time, min_players, max_players, UPC, acquisition_date) VALUES("Twilight Struggle", "In 1945, unlikely allies toppled Hitler's war machine, while humanity's most devastating weapons forced the Japanese Empire to its knees in a storm of fire. Where once there stood many great powers, there then stood only two. The world had scant months to sigh its collective relief before a new conflict threatened. Unlike the titanic struggles of the preceding decades, this conflict would be waged not primarily by soldiers and tanks, but by spies and politicians, scientists and intellectuals, artists and traitors. Twilight Struggle is a two-player game simulating the forty-five year dance of intrigue, prestige, and occasional flares of warfare between the Soviet Union and the United States. The entire world is the stage on which these two titans fight to make the world safe for their own ideologies and ways of life. The game begins amidst the ruins of Europe as the two new 'superpowers' scramble over the wreckage of the Second World War, and ends in 1989, when only the United States remained standing.", "GMT Games", 2005, 13, 180, 2,2, 817054010004 , '2014-02-01 00:00:00');
INSERT INTO board_games(title, description, publisher, publish_year, suggested_age, playing_time, min_players, max_players, UPC, acquisition_date) VALUES("Terra Mystica", "Terra Mystica is a game with very little luck that rewards strategic planning. Each player governs one of the 14 groups. With subtlety and craft, the player must attempt to rule as great an area as possible and to develop that group's skills. There are also four religious cults in which you can progress. To do all that, each group has special skills and abilities.", "Feuerland Spiele", 2012, 12, 100, 2,5, 681706712406 , '2014-03-01 00:00:00');
INSERT INTO board_games(title, description, publisher, publish_year, suggested_age, playing_time, min_players, max_players, UPC, acquisition_date) VALUES("Through the Ages: A Story of Civilization", "Through the Ages is a civilization building game. Each player attempts to build the best civilization through careful resource management, discovering new technologies, electing the right leaders, building wonders and maintaining a strong military. Weakness in any area can be exploited by your opponents. The game takes place throughout the ages beginning in the age of antiquity and ending in the modern age.", "Czech Board Games", 2006, 14, 240, 2,4, 718122262403 , '2014-03-01 00:00:00');
INSERT INTO board_games(title, description, publisher, publish_year, suggested_age, playing_time, min_players, max_players, UPC, acquisition_date) VALUES("Munchkin", "Go down in the dungeon. Kill everything you meet. Backstab your friends and steal their stuff. Grab the treasure and run.", "Steve Jackson Games", 2001, 12, 90, 3,6, 837654321539 , '2014-04-01 00:00:00');

-- Set the genre of these games, and add a few ratings
-- Munchkin
INSERT INTO genre(upc, genre) VALUES(837654321539, 'Card');
INSERT INTO genre(upc, genre) VALUES(837654321539, 'Fantasy');
INSERT INTO genre(upc, genre) VALUES(837654321539, 'Fighting');
INSERT INTO genre(upc, genre) VALUES(837654321539, 'Humour');
INSERT INTO genre(upc, genre) VALUES(837654321539, 'Card Draft');

INSERT INTO online_rating(upc,rating,source) VALUES(837654321539, 6, "BoardGameGeek");
INSERT INTO customer_game_rating(upc,rating,email) VALUES(837654321539, 8, "jim@darkmagic.com");
INSERT INTO customer_game_rating(upc,rating,email) VALUES(837654321539, 9, "trog@dor.com");
INSERT INTO favourite_game(upc,email) VALUES(837654321539, "trog@dor.com");

-- Twilight
INSERT INTO genre(upc, genre) VALUES(817054010004, 'Political');
INSERT INTO genre(upc, genre) VALUES(817054010004, 'Strategy');
INSERT INTO genre(upc, genre) VALUES(817054010004, 'Wargame');
INSERT INTO genre(upc, genre) VALUES(817054010004, 'Bluffing');

INSERT INTO online_rating(upc,rating,source) VALUES(817054010004, 8, "BoardGameGeek");
INSERT INTO favourite_game(upc,email) VALUES(817054010004, "trog@dor.com");

-- Dixit
INSERT INTO genre(upc, genre) VALUES(3558380016168, 'Bluffing');
INSERT INTO genre(upc, genre) VALUES(3558380016168, 'Humour');
INSERT INTO genre(upc, genre) VALUES(3558380016168, 'Party');
INSERT INTO genre(upc, genre) VALUES(3558380016168, 'Voting');

INSERT INTO online_rating(upc,rating,source) VALUES(3558380016168, 8, "BoardGameGeek");
INSERT INTO favourite_game(upc,email) VALUES(3558380016168, "jim@darkmagic.com");
INSERT INTO customer_game_rating(upc,rating,email) VALUES(3558380016168, 10, "jim@darkmagic.com");

-- Terra
INSERT INTO genre(upc, genre) VALUES(681706712406, 'Economic');
INSERT INTO genre(upc, genre) VALUES(681706712406, 'Civilization');
INSERT INTO genre(upc, genre) VALUES(681706712406, 'Fantasy');
INSERT INTO genre(upc, genre) VALUES(681706712406, 'Strategy');
INSERT INTO genre(upc, genre) VALUES(681706712406, 'Territory');

INSERT INTO online_rating(upc,rating,source) VALUES(681706712406, 8, "BoardGameGeek");
INSERT INTO customer_game_rating(upc,rating,email) VALUES(681706712406, 5, "jim@darkmagic.com");

-- Through the Ages
INSERT INTO genre(upc, genre) VALUES(718122262403, 'Strategy');
INSERT INTO genre(upc, genre) VALUES(718122262403, 'Civilization');
INSERT INTO genre(upc, genre) VALUES(718122262403, 'Economic');
INSERT INTO genre(upc, genre) VALUES(718122262403, 'Card Draft');

INSERT INTO online_rating(upc,rating,source) VALUES(718122262403, 8, "BoardGameGeek");

end transaction;
