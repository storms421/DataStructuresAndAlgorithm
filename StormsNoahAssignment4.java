/* This program is meant to provide additional practice for creating and manipulating classes.
 * Additionally, this program helps with understanding how to use arrays that are not completely
 * full with gaps between indexes. 
 * Key topics: Nested Objects, Arrays, ArrayLists, Comparable, and Collections
 */
package packageJava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class StormsNoahAssignment4 {

	public static void main(String[] args) throws IOException {
		
		Scanner trainFile = new Scanner(new File("Trains.txt"));
		
		int numberTracks = trainFile.nextInt();
		
		Railroad railroad = new Railroad(numberTracks);
		
		System.out.println("Loading trains onto tracks in sorting yard...");
		System.out.println();
		
		// Pull specific info from file and place into respective variables from each line 
		while(trainFile.hasNextLine()) {
			int trackNumber = trainFile.nextInt();
			int engineNumber = trainFile.nextInt();
			String company = trainFile.next();
			int numberRailCars = trainFile.nextInt();
			String type = trainFile.next();
			String destinationCity = trainFile.nextLine().trim();
			
			Train train = new Train(engineNumber, company, numberRailCars, type, destinationCity);
			
			// Place train in correct track in sorting yard
			railroad.addTrainToSortingYard(trackNumber, train);
			
		}
		
		trainFile.close();
		
		railroad.displaySortingYard();
		
		printTrainReport(railroad);
		
		
	} // end main
	
	// This method prints a sorted list of trains (by rail cars) from an array list
	public static void printTrainReport (Railroad railroad) {
		
		// Stores trains in the sorting yard
		ArrayList<Train> sortedTrains = new ArrayList<>();
		
		int numberTracks = railroad.getNumberTracks();
		
		// Loop through to find if there is a train on the track
		for(int i = 0; i < numberTracks; i++) {
			// If train on current sorting yard track, place train into array list
			if(railroad.getTrainSortingYard(i) != null) {
				sortedTrains.add(railroad.getTrainSortingYard(i));
			}
		}
		
		// Sort trains stored in array list 
		// Can use Collections sort method since Train class implements Comparable
		Collections.sort(sortedTrains);
		
		System.out.println("*************************************************************************************");
		System.out.println("\t\t\t\tTRAIN REPORT");
		System.out.println("\t\t\t(Ordered by Number of Rail Cars");
		System.out.println("*************************************************************************************");
		System.out.printf("Engine Company\t\t Rail Cars\t Type\t\t Departing To\n");
		System.out.println("-------------------------------------------------------------------------------------");
		
		for(Train t : sortedTrains) {
			System.out.println(t.toString());
		}
		
	} // end printTrainReport
	
} // end class


class Railroad {
	
	private int numberTracks;
	private Train[] sortingYard;
	
	public Railroad(int numberTracks) {
		
		this.numberTracks = numberTracks;
		sortingYard = new Train[numberTracks];
	
	} // end constructor
	
	
	public int getNumberTracks() {
		
		return numberTracks;
		
	} // end getNumberTracks
	
	
	public void addTrainToSortingYard(int trackNumber, Train train) {
		
		sortingYard[trackNumber] = train;

	} // end addTrainToSortingYard
	
	public Train getTrainSortingYard(int trackNumber) {
		
		return sortingYard[trackNumber];
		
	} // end getTrainSortingYard
	
	public void displaySortingYard() {
		
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.printf("Track\t Engine Company\t\t Rail Cars\t Type\t\t Destination\n");
		System.out.println("----------------------------------------------------------------------------------------");

		for(int i = 0; i < numberTracks; i++) {
			// If no train on track, display dashes
			if(sortingYard[i] == null) {
				System.out.printf("%d\t---\t---\t\t---\t\t---\t\t---\n", i);
			}
			// If train, display train info
			else {
				System.out.printf("%d\t%s\n", i, sortingYard[i].toString());
			}
		}
		
	} // end displaySortingYard
	
} // end Railroad



class Train implements Comparable<Train> {
	
	private int engineNumber;
	private String company;
	private int numberRailCars;
	private String type;
	private String destinationCity;
	
	
	public Train(int engineNumber, String company, int numberRailCars, String type, String destinationCity) {
		
		this.engineNumber = engineNumber;
		this.company = company;
		this.numberRailCars = numberRailCars;
		this.type = type;
		this.destinationCity = destinationCity;
		
	} //end constructor
	
	
	public int getEngineNumber() {
		return engineNumber;
	}
	
	public String getCompany() {
		return company;
	}
	
	public int getNumberRailCars() {
		return numberRailCars;
	}
	
	public String getType() {
		return type;
	}
	
	public String getDestinationCity() {
		return destinationCity;
	}
	
	// Method overrides toString method in Object class
	@Override
	public String toString() {
		
		return String.format("%d\t%s\t\t%d\t\t%-10s\t%s", engineNumber, company, numberRailCars, type, destinationCity);
		
	} // end toString
	
	/* This method allows for it to be possible to sort an array of trains by 
	* numbers of rail cars when calling Collections.sort method */
	public int compareTo(Train otherTrain) {
		
		if(this.numberRailCars < otherTrain.numberRailCars) {
			return 1;
		}
		else if(this.numberRailCars > otherTrain.numberRailCars) {
			return -1;
		}
		else {
			return 0;
		}
		
	} //end compareTo
	
} // end Train

