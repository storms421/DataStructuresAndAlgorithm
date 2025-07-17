/* This assignment demonstrates the use of abstraction, interfaces, and polymorphism in Java by using 
* different bird types and their unique behaviors such as swimming, running, and flying. The interfaces 
* (Swimmer, Runner, Flying) define specific abilities that allow the birds to implement only the behaviors 
* they support. The program can treat all birds as generic Bird objects while invoking behavior based on 
* their actual types and implemented interfaces.  */
package packageJava;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class StormsNoahAssignment3 {

	public static void main(String[] args) throws IOException {
		
		int birdIndex = 0;
		
		Scanner readFile = new Scanner(new File("Birds.txt"));
		
		ArrayList<Bird> birds = new ArrayList<>(); // Create Bird ArrayList
		
		birdIndex = readFile.nextInt(); // Grab number of birds at top of file
		System.out.println(birdIndex);
		
		// Loop through file and pull values to place in respective attribute
		for(int i = 0; i < birdIndex; i++) {
			String type = readFile.next();
			String name =  readFile.next();
			int runSpeed = readFile.nextInt();
			int swimSpeed = readFile.nextInt();
			int flySpeed = readFile.nextInt();
			
			// Create the correct Bird object based on type
			switch(type) {
				case "penguin":
					birds.add(new Penguin(name, runSpeed, swimSpeed));
					break;
				case "ostrich":
					birds.add(new Ostrich(name, runSpeed, swimSpeed));
					break;
				case "sootytern":
					birds.add(new SootyTern(name, runSpeed, flySpeed));
					break;
				case "loon":
					birds.add(new Loon(name, swimSpeed, flySpeed));
					break;
			} // end switch
		} //end loop
		
		
		readFile.close();
		
		displayBirds(birds);
		
		System.out.println("\n----------------------------------------------------------");
		System.out.println("BIRDS THAT CAN SWIM");
		System.out.println("----------------------------------------------------------");
		System.out.printf("Bird\tType\tSwimming Speed\n");
		System.out.println("----------------------------------------------------------");
		
		ArrayList<Bird> swimmers = findSwimmers(birds);
		
		for(Bird s : swimmers) {
			System.out.printf("%-10s%-10s%d\n", s.getName(), s.getType(), ((Swimmer)s).swim());
		}
		
		System.out.println("\n----------------------------------------------------------");
		System.out.println("SWIMMING RACE");
		System.out.println("----------------------------------------------------------");
		System.out.println("Swimming birds get ready ... get set ... go!");
		Bird fastestSwimmer = swimmingRace(swimmers);
		System.out.printf("The winner is %s the %s swimming at %d mph", fastestSwimmer.getName(), fastestSwimmer.getType(), ((Swimmer)fastestSwimmer).swim());
		

	} // end Main
	
	// This method displays the birds
	public static void displayBirds(ArrayList<Bird> birds) {

		System.out.println("Birds and Abilities");
		System.out.println("----------------------------------------------------------");
		
		for(Bird b : birds) {
			
			int swim = 0;
			int run = 0;
			int fly = 0;
			
			// See what each bird can do and pull that value, otherwise stays 0
			if(b instanceof Swimmer) {
				swim = ((Swimmer)b).swim();
			}
			if(b instanceof Runner) {
				run = ((Runner)b).run();
			}
			if(b instanceof Flying) {
				fly = ((Flying)b).fly();
			}
			
			System.out.printf("%s is a %s\n%s\n", b.getName(), b.getType(), b.strangeFact());
			System.out.printf("Swim Speed: %d\tRun Speed: %d\tFlying Altitude: %d\n", swim, run, fly);
		}
		
	} // end displayBirds

	
	// This method finds birds that can swim and adds to the swimmers array list
	public static ArrayList<Bird> findSwimmers(ArrayList<Bird> birds) {
		
		ArrayList<Bird> swimmers = new ArrayList<>();
		
		for(Bird b : birds) {
			// If bird swims, add to swimmers array list
			if(b instanceof Swimmer) {
				swimmers.add(b);
			}
		}
		
		return swimmers;
		
	} // end findSwimmers
	
	
	// This method finds which bird swims the fastest
	public static Bird swimmingRace(ArrayList<Bird> swimmingBirds) {
		
		int fastestSwimSpeed = 0;
		int fastestSwimmerIndex = 0;
		
		for(int i = 0; i < swimmingBirds.size(); i++) {
			int swimSpeed = ((Swimmer)swimmingBirds.get(i)).swim(); // Cast bird to Swimmer interface for swim speed
			
			// If current swim speed faster than fastest swim speed, then make new fastest swim speed
			if(swimSpeed > fastestSwimSpeed) {
				fastestSwimSpeed = swimSpeed;
				fastestSwimmerIndex = i;
			}
		}
		
		return swimmingBirds.get(fastestSwimmerIndex); // Return fastest swimmer
		
	} // end swimmingRace

} // end class



abstract class Bird {
	
	private String name;
	private String type;
	
	public Bird() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	// This being abstract forces all subclasses to implement this method
	public abstract String strangeFact();
	
} // End Bird


class Penguin extends Bird implements Runner, Swimmer {
	
	private int runSpeed;
	private int swimSpeed;
	
	public Penguin(String name, int runSpeed, int swimSpeed) {
		setName(name);
		setType("Penguin");
		this.runSpeed = runSpeed;
		this.swimSpeed = swimSpeed;
	}
	
	@Override
	public int run() {
		return runSpeed;
	}

	@Override
	public int swim() {
		return swimSpeed;
	}
	
	@Override
	public String strangeFact() {
		return "I can't fly but I'm the fastest swimmer and deepest diver and can stay underwater up to 20 minutes at a time.";
	}
	
} // end Penguin

class Ostrich extends Bird implements Runner, Swimmer {
	private int runSpeed;
	private int swimSpeed;
	
	
	public Ostrich(String name, int runSpeed, int swimSpeed) {
		setName(name);
		setType("Ostrich");
		this.runSpeed = runSpeed;
		this.swimSpeed = swimSpeed;
	}
	
	@Override
	public int run() {
		return runSpeed;
	}

	@Override
	public int swim() {
		return swimSpeed;
	}
	
	@Override
	public String strangeFact() {
		return "Who needs flying when you're the biggest bird on earth! I can be up to 9 feet tall and weigh up to 300 pounds - bring it on!";
	}
	
} // end Ostrich


class SootyTern extends Bird implements Runner, Flying {
	private int runSpeed;
	private int flySpeed;
	
	public SootyTern(String name, int runSpeed, int flySpeed) {
		setName(name);
		setType("SootyTern");
		this.runSpeed = runSpeed;
		this.flySpeed = flySpeed;
	}
	
	@Override
	public int run() {
		return runSpeed;
	}

	@Override
	public int fly() {
		return flySpeed;
	}
	
	@Override
	public String strangeFact() {
		return "Strange as it may sound, I spend most of my life at sea and I can't swim but I can nap while flying!";
	}
	
} // end SootyTern



class Loon extends Bird implements Swimmer, Flying {
	private int swimSpeed;
	private int flySpeed;
	
	public Loon(String name, int swimSpeed, int flySpeed) {
		setName(name);
		setType("Loon");
		this.swimSpeed = swimSpeed;
		this.flySpeed = flySpeed;
	}

	@Override
	public int fly() {
		return flySpeed;
	}

	@Override
	public int swim() {
		return swimSpeed;
	}
	
	@Override
	public String strangeFact() {
		return "My legs are so far back on my body that I cannot walk on land, so I push myself along the ground on my chest.";
	}
	
} // end Loon


// These interfaces allow multiple classes to share common behavior without being tightly bound to a specific class hierarchy.
// This makes it easier to scale, test, and maintain applications by programming to a contract rather than a specific implementation.

interface Swimmer {
	
	public int swim();
	
} // end Swimmer


interface Runner {
	
	public int run();
	
} // end Runner

interface Flying {
	
	public int fly();
	
} // end Flying

