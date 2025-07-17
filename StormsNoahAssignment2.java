/* This assignment is designed to demonstrate object-oriented programming principles, inheritance and polymorphism. 
 * It reads a list of actors from a file and creates objects of different types (Hero, Villain, Monster, and Droid) based on 
 * the data. All these actor types inherit from a common base class, Actor, which defines shared properties and behaviors like 
 * name, type, and the motto method. Inheritance is used to promote code reuse and to model the natural hierarchy between 
 * general actors and their more specific roles. Polymorphism allows the program to store and process all actor objects in a 
 * single Actor array, while still invoking the correct version of the motto method at runtime based on each actor’s actual type. 
 * This eliminates the need for type-checking logic and keeps the code clean, flexible, and scalable. The program displays a 
 * full cast list and then creates a movie using only Heroes and Villains. This is to showcase how different objects can be 
 * handled uniformly through polymorphic behavior.*/
package packageJava;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class StormsNoahAssignment2 {

	public static void main(String[] args) throws IOException {
		
		Scanner readFile = new Scanner(new File("Actors.txt"));
		
		int numOfActors = readFile.nextInt(); // Grab number of actors on first line of file
		System.out.println(numOfActors);
		
		Actor[] actorArray = new Actor[numOfActors];
		
		// Loop through file
		for(int i = 0; i < numOfActors; i++) {
			String type = readFile.next();
			String name = readFile.nextLine().trim();
			
			// Determine which type of actor is being read to create the correct object 
			switch (type) {
				case "Hero":
					actorArray[i] = new Hero(name);
					break;
				case "Villain":
					actorArray[i] = new Villain(name);
					break;
				case "Monster": 
					actorArray[i] = new Monster(name);
					break;
				case "Droid":
					actorArray[i] = new Droid(name);
					break;
			}
		}
		
		System.out.println("-------------------------------------------------------------------------------");
		System.out.printf("%s\t\t%s\t\t%s\n", "Actor Name", "Type", "Motto To Live By");
		System.out.println("-------------------------------------------------------------------------------");
		for(int i = 0; i < numOfActors; i++) {
			System.out.printf("%-15s\t\t", actorArray[i].getName());
			System.out.printf("%s\t\t", actorArray[i].getType());
			System.out.printf("%s\t\t\n", actorArray[i].motto());
		}
		
		Movie adventureMovie = new Movie(); // Movie object created for Hero/Villain 
		adventureMovie.selectCast(actorArray);
		adventureMovie.printMovieDetails();
		
		readFile.close();
		
	} // End main
	
} // End class

class Actor{
	private String name;
	private String type;
	
	public Actor(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String motto() {	
		return "No Motto";
	}
	
} // End Actor

class Hero extends Actor {
	
	public Hero(String name) {
		super(name, "Hero");
	}

	@Override
	public String motto() {
		return "To the rescue! KAPOW!! BAM!! POW!!";
	}
	
} // End Hero

class Villain extends Actor {
	
	public Villain(String name) {
		super(name, "Villain");
	}
	
	@Override
	public String motto() {
		return "You'll never stop me! Haaaaaaa!";
	}
	
} // End Villain

class Monster extends Actor {
	
	public Monster(String name) {
		super(name, "Monster");
	}
	
	@Override
	public String motto() {
		return "RRAAAUUGGHH GRROWR!!!";
	}
	
} // End Monster

class Droid extends Actor {
	
	public Droid(String name) {
		super(name, "Droid");
	}
	
	@Override
	public String motto() {
		return "Beep Beep Bloop Boop Beep!";
	}
	
} // End Droid




class Movie {
	private int numHeroes;
	private int numVillains;
	private Actor[] actorsInMovie;
	
	public Movie() {
		
	}
	
	// This method selects the cast from incoming array (Heroes/Villains) to store in the movie array
	public void selectCast(Actor[] actors) {	
		for(Actor type : actors) {
			if(type instanceof Hero) { // If object type is Hero, add hero total
				numHeroes++;
			}
			if(type instanceof Villain) { // If object type is Villain, add villain total
				numVillains++;
			}
		}
		
		actorsInMovie = new Actor[numHeroes + numVillains]; // Allocate memory for actorsInMovie array
		
		int actorIndex = 0; // actorsInMovie array needs index since it will be most likely a different size than original actor array
		
		// Loop through incoming array to find Hero/Villain actors to store in actorsInMovie array
		for(int i = 0; i < actors.length; i++) {
			if((actors[i] instanceof Hero) || (actors[i] instanceof Villain)) { // If Hero/Villain, place in array, increment index
				actorsInMovie[actorIndex] = actors[i];
				actorIndex++;
			}
		}
		
	} // End selectCast
	
	// This method prints the details for the Movie
	public void printMovieDetails() {
		
		System.out.println("\n-------------------------------------");
		System.out.println("Heroes and Villain Movie");
		System.out.println("-------------------------------------");
		
		System.out.printf("Number of Heroes: %s\n", numHeroes);
		System.out.printf("Number of Villains: %s\n\n", numVillains);	
		
		// Loop through array to display
		for(int i = 0; i < actorsInMovie.length; i++) {
			System.out.printf("%-10s---\t%-10s\n", actorsInMovie[i].getType(), actorsInMovie[i].getName());
		}
		
	} // End printMovieDetails
	
	
} // End Movie
