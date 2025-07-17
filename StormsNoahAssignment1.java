/* Assignment 1 is about filling arrays with values and then manipulating them using only the two of them. 
 * Also, this assignment is a way to get comfortable with writing and reading from files. */
package packageJava;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class StormsNoahAssignment1 {

	public static void main(String[] args) throws IOException{
		
		// Two arrays filled with values
		int[] array1 = {13, 18, 1, 4, 8, 16, 11};
		int[] array2 = {9, 10 , 5, 12, 3, 7, 14};
		
		// Display arrays
		System.out.println("Array 1: Original");
		System.out.println("-----------------");
		displayArrays(array1, 1);
		
		System.out.println("Array 2: Original");
		System.out.println("-----------------");
		displayArrays(array2, 2);
		
		// Organize odd/even into respective arrays
		swapArrays(array1, array2);
		
		// Sort arrays before writing to file
		sortArray(array1);
		sortArray(array2);
		
		System.out.println("Array 1: Sorted with Even Values");
		System.out.println("--------------------------------");
		displayArrays(array1, 1);
		
		System.out.println("Array 2: Sorted with Odd Values");
		System.out.println("-------------------------------");
		displayArrays(array2, 2);	
		
		int indexCount = 0;
		
		indexCount = writeToFile(array1, array2, indexCount); // Retrieves a total index for our finalArray
		
		System.out.println("\nThe amount of numbers written to file was: " + indexCount);
		
		System.out.println("\nFinal Array");
		System.out.println("-------------------------------");
		
		
		int[] finalArray = new int[indexCount];
		
		// Get values stored into our final array
		readFile(finalArray);
		
		displayArrays(finalArray, 3);
		
	} // End main
	
	//This method displays the arrays to the console
	public static void displayArrays(int[] array, int num) {

		for(int i = 0; i < array.length; i++){
			System.out.println("array" + num + "[" + i + "]  = " + array[i]);
		}
		
		System.out.println("");
	
	} // End displayArrays
	
	// This method sorts array order numerically (Selection Sort)
	public static void sortArray(int[] array) {
		
		int n = array.length;
		// Loop array looking for smallest value
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i; // Current "smallest" value being held
            for (int j = i + 1; j < n; j++) {
            	// If smaller value found, update minIndex
                if (array[j] < array[minIndex]) {
                    minIndex = j; 
                }
            }
            // Swap smallest value found with current index
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        
	} // End sortArray
	
	// This method swaps values based on it being even or odd (array1 holds even, array2 holds odd)
	public static void swapArrays(int[] array1, int[] array2) {
		
		for(int i = 0; i < 6; i++) { 
			// If odd, search array2
			if(array1[i] % 2 == 1) {
				for(int j = 0; j < array2.length; j++) {
					// If even, swap with array1
					if(array2[j] % 2 == 0) {
						int temp = array1[i];
						array1[i] = array2[j];
						array2[j] = temp;
						break;
					} // End inner-if
				} // End inner-loop
			} // End outer-if
		} // End outer-loop
		
	} // End swapArrays
	
	// This method writes the merged values of two sorted arrays to a file in sorted order
	public static int writeToFile(int[] array1, int[] array2, int indexCount) throws IOException{
		
		File fileName = new File("assignment1.txt"); // File name to be written to
		
		PrintWriter outputFile = new PrintWriter(new FileWriter("assignment1.txt")); // Create writer to file
		
		System.out.println("File is in directory: " + fileName.getAbsolutePath());
		
		System.out.println("\nShow values being written to file");
		System.out.println("---------------------------------");
		
		// Track current index
		int a1 = 0, a2 = 0;

        // While both arrays have values, compare, then write to file
        while (a1 < array1.length && a2 < array2.length) {
            if (array1[a1] < array2[a2]) { // Array2 bigger, write array1
                System.out.println("Writing to file: " + array1[a1]);
                outputFile.println(array1[a1++]);
            } else { // Array1 bigger, write array2
                System.out.println("Writing to file: " + array2[a2]);
                outputFile.println(array2[a2++]);
            }
            indexCount++;
        }

	     // Write remaining elements from array1
	     while (a1 < array1.length) {
	    	 System.out.println("Writing to file: " + array1[a1]);
	         outputFile.println(array1[a1++]);
	         indexCount++;
	     }
	
	     // Write remaining elements from array2
	     while (a2 < array2.length) {
	    	 System.out.println("Writing to file: " + array2[a2]);
	    	 outputFile.println(array2[a2++]);
	         indexCount++;
	     }
	     
	     outputFile.close();
	     
	     return indexCount; // Return total for values written to file
					
	} // End writeToFile
	
	// This method gets the file, reads it, then stores it into array
	public static void readFile(int[] array) throws IOException{
		
		Scanner readFile = new Scanner(new File("C:/Users/Noah Storms/eclipse-workspace/CS1450/assignment1.txt"));
			
		int index = 0;
		
		// While file not empty, loop
		while(readFile.hasNextLine()) {
			int num = Integer.parseInt(readFile.nextLine()); // Grab integer from file
			array[index] = num; // Store value into array
			index++; // Increase index
		}
			
		readFile.close();	
	
	} // End readFile

} // End class
