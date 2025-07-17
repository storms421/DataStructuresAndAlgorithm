/* This assignment is done to understand stacks, generic classes, and generic methods. The first
 * part is meant to understand manipulating a stack using JCF stack. The second part is meant to
 * take an unsorted generic stack and sort it. After sorting this generic stack, count how many
 * times the values appear in the stack. Additionally, creating a stack (using an array list) 
 * gives a better understanding of how stacks work from a developers perspective.
 */
package packageJava;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.util.Stack;

public class StormsNoahAssignment5 {

	public static void main(String[] args) throws IOException {
		
		int[] values = {8, 17, 62, 4, 6, 2, 42, 10, 3, 7};
		
		double average = 0;
		
		// Creating a stack using JCF
		Stack<Integer> s = new Stack<>();
		
		// Move from array to stack
		for(int i = 0; i < values.length; i ++) {
			s.push(values[i]);
		}
		
		average = findAverage(s);
		
		
		System.out.println("\nStack Values After Compute Average:");
		System.out.println("--------------------------------");
		printStack(s);
		
		System.out.println("\nThe average is: " + average);
//********************************************************************************************************************************************
		
		Scanner inputIntegers = new Scanner(new File("numbers.txt"));
		Scanner inputStrings = new Scanner(new File("strings.txt"));
		
		//
		GenericStack<Integer> numStack = new GenericStack<>();
		
		GenericStack<String> stringStack = new GenericStack<>();
		
		// Add values from file to GenericStack
		while(inputIntegers.hasNextLine()) {
			int tempInteger = inputIntegers.nextInt();
			numStack.push(tempInteger);
		}
		
		// Add values from file to GenericStack
		while(inputStrings.hasNextLine()) {
			String tempString = inputStrings.nextLine();
			stringStack.push(tempString);
		}
		
		System.out.println("\nNumbers from file placed on number stack (unsorted)");
		System.out.println("-----------------------------------------------------");
		printStackGS(numStack);
		
		GenericStack<Integer> numStackSorted = new GenericStack<>();
		GenericStack<String> stringStackSorted = new GenericStack<>();
		
		sortStack(numStack, numStackSorted);
		
		System.out.println("\nSorted number stack");
		System.out.println("-----------------------------------------------------");
		printStackGS(numStackSorted);
		
		System.out.println("\nNumber of times each value appears");
		System.out.println("-----------------------------------------------------");
		displayAppearanceCounts(numStackSorted);
		
		
		System.out.println("\nStrings from file placed on string stack (unsorted)");
		System.out.println("-----------------------------------------------------");
		printStackGS(stringStack);
		
		sortStack(stringStack, stringStackSorted);
		
		System.out.println("\nSorted strings stack");
		System.out.println("-----------------------------------------------------");
		printStackGS(stringStackSorted);
		
		System.out.println("\nNumber of times each string appears");
		System.out.println("-----------------------------------------------------");
		displayAppearanceCounts(stringStackSorted);
		
		inputIntegers.close();
		inputStrings.close();
		
	} // End main
	
	
	// Method to find avg from numbers in stack
	public static double findAverage(Stack<Integer> stack) {
		
		Stack<Integer> temp = new Stack<>();
		
		int count = 0;
		int hold = 0;
		double total = 0;
		double avg = 0;
		
		while(!stack.isEmpty()) {
			hold = stack.pop();
			total = total + hold; // Add current value to total
			temp.push(hold);
			count++;
		}
		
		// Place values back into original stack
		while(!temp.isEmpty()) {
			hold = temp.pop();
			stack.push(hold);
		}
		
		System.out.println("The total is: " + total);
		
		avg = total / count; // Calculate avg
		
		return avg;
		
	} // End findAverage
	
	// Method to print the stack
	public static void printStack(Stack<Integer> stack) {
		
		Stack<Integer> temp = new Stack<>();
		
		int hold = 0;
		
		while(!stack.isEmpty()) {
			hold = stack.pop();
			System.out.println(hold);
			temp.push(hold);
		}
		
		// Place values back into original stack
		while(!temp.isEmpty()) {
			hold = temp.pop();
			stack.push(hold);
		}
		
	} // End printStack
	
	
	
	// Method to print the Generic Stack
	public static <E> void printStackGS(GenericStack<E> stack) {
		
		GenericStack<E> tempGenericStack = new GenericStack<>();
		
		E genericValue;
		
		while(!stack.isEmpty()) {
			genericValue = stack.pop();
			System.out.println(genericValue);
			tempGenericStack.push(genericValue);
		}
		
		// Place values back into original stack
		while(!tempGenericStack.isEmpty()) {
			stack.push(tempGenericStack.pop());
		}
		
	} // End printStackGS
	
	// Method to sort the Generic Stack
	public static <E extends Comparable<E>> void sortStack(GenericStack<E> unsortedStack, GenericStack<E> sortedStack) {
		
		
		// While stack not empty
		while(!unsortedStack.isEmpty()){
			E value = unsortedStack.pop(); // Move first value top stack
			boolean done = false;
			// While stack is not empty and not done comparing
			while((!sortedStack.isEmpty()) && (done == false)) {
				// If the defined value compared is greater, push the value in sorted back to the unsorted stack
				if(value.compareTo(sortedStack.peek()) > 0) {
					unsortedStack.push(sortedStack.pop());
				}
				else {
					done = true; // Done comparing
				}
			}	
			
			sortedStack.push(value); // Push smaller value into sorted
		}
			
	} // End sortStack
	
	// Method to count the times a value repeats in the stack
	public static <E extends Comparable<E>> void displayAppearanceCounts(GenericStack<E> stack){
		
		GenericStack<E> tempGenericStack = new GenericStack<>();
		
		
		while(!stack.isEmpty()) {
			E currentValue = stack.pop();
			tempGenericStack.push(currentValue);
			int appearance = 1; // Count the first value
			boolean done = false;
			
			while((!stack.isEmpty()) && (done == false)) {
				E nextValue = stack.pop();
				tempGenericStack.push(nextValue);
				// If value found is the same, count
				if(currentValue.compareTo(nextValue) == 0) {
					appearance++;
				}
				// Else stop comparing
				else {
					done = true;
				}
			}
			
			System.out.println(currentValue + " appeared " + appearance + " times on the stack");
			
			while((!tempGenericStack.isEmpty()) && (done == true)) {
				// If value not same, return back to stack to be counted
				if(currentValue.compareTo(tempGenericStack.peek()) != 0) {
					stack.push(tempGenericStack.pop());
				}
				else {
					done = false;
				}
				
			}
		}
		
	} // End displayAppearanceCounts

} // End class




class GenericStack<E> {
	
	private ArrayList<E> stack;
	
	// Constructor to allocate memory for ArrayList
	public GenericStack() {
		stack = new ArrayList<E>();
	}
	
	// Checks to see if the ArrayList (our stack) is empty
	public boolean isEmpty() {
		if(stack.size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Gets the size of the ArrayList (our stack)
	public int getSize() {
		return stack.size();
	}
	
	// Looks at the value at the top of the stack
	public E peek() {
		return stack.get(getSize() - 1);
	}
	
	// Removes the value at the top of the stack
	public E pop() {
		E value = stack.get(getSize() - 1);
		stack.remove(getSize() - 1);
		return value;
	}
	
	// Adds value on top of stack
	public void push(E value) {
		stack.add(value);
	}
	
} // End GenericStack
