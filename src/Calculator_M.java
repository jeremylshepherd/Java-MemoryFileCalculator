
/*
 * CIS 2122
 * 04/02/2020
 * Jeremy L. Shepherd
 * Calculator_M
 * A calculator class that takes in user input to calculate basic math functions 
 * of 2 numbers (currentValue and userInput) and stores result in currentValue.
 * Now with ability to save hsitory in a text file.
 * Using methods to present cleaner code.
 */

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter; 

public class Calculator_M {
	static Scanner input = new Scanner(System.in);
	private double currentValue;
	private static ArrayList<String> history = new ArrayList<String>();
	
	public Calculator_M() {
		this.currentValue = 0.0;
		history.add("The initial value is " + currentValue);
	}
	
	//Overridden Constructor
	public Calculator_M(double currentValue) {
		this.currentValue = currentValue;
		history.add("The initial value is " + currentValue);
	}
	
	//Display menu
	public static void showMenu() {
		System.out.println("Calculator Menu");
		System.out.println("1. Add");
		System.out.println("2. Subtract");
		System.out.println("3. Multiply");
		System.out.println("4. Divide");
		System.out.println("5. Clear");
		System.out.println("6. Save");
		System.out.println("7. Quit");
		System.out.print("What would you like to do? ");
	}
	
	//Get menu method
	public static int displayMenu() {
		int choice;
		
		showMenu();
		choice = input.nextInt();
		while(choice < 1 || choice > 7)
		{
			System.out.println("I'm sorry, " + choice + " wasn't one of the options");
			System.out.print("Please select a valid option: ");
			choice = input.nextInt();
		}
		
		return choice;
	}
	
	static void addCalculationToList() {
		history.add("Cleared");
	}
	
	static void addCalculationToList(int choice, double currentValue, double operand2, double returnedValue) {
		String str = "";
		String operator = "";
		
		switch(choice) {
			case 1:
				operator = " + ";
				break;
			case 2:
				operator = " - ";
				break;
			case 3:
				operator = " * ";
				break;
			case 4:
				operator = " / ";
				break;			
		}
		str = currentValue + operator + operand2 + " = " + returnedValue;
		history.add(str);		
	}
	
	//GET method for current 
	public double getCurrentValue() {
		return currentValue;
	}
	
	//Get Operand method
	public static double getOperand(String message) {		
		double num;
		
		System.out.print(message);
		num = input.nextDouble();
		return num;
	}
	
	//Addition method
	public void add(double operand2) {
		double temp = currentValue;
		currentValue += operand2;
		addCalculationToList(1, temp, operand2, currentValue);
	}
	
	//Subtraction method
	public void subtract(double operand2) {
		double temp = currentValue;
		currentValue  -= operand2;
		addCalculationToList(2, temp, operand2, currentValue);
	}
	
	//Multiplication method
	public void multiply(double operand2) {
		double temp = currentValue;
		 currentValue *= operand2;
		 addCalculationToList(3, temp, operand2, currentValue);
	}
	
	//Division method
	public void divide(double operand2) {
		double temp = currentValue;
		if(operand2 == 0) {
			System.out.println("I'm sorry, but dividing by zero will break the universe.\nFor everyone's sake the value has defaulted to NaN.");
			currentValue = Double.NaN;
		}else {	
			currentValue /= operand2;
		}
		addCalculationToList(4, temp, operand2, currentValue);
	}
	
	//Reset currentValue to Zero
	public void clear() {		
		currentValue = 0.0;
		addCalculationToList();
	}
	
	//Save history to file
	public void save() throws Exception {	
		File file = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	file = chooser.getSelectedFile();
	    }
	    
	    if (!file.isFile()) {
	    	throw new Exception("The user didn't choose a file.");
	    }
	    
	    System.out.println(file.getAbsolutePath());
	    
	    try {
		    PrintWriter pw = new PrintWriter(file);		    
		    for(int i = 0; i < history.size(); i++) {
		    	pw.println(history.get(i));
		    }		    
		    System.out.println("Data saved to file");
		    pw.close();
	    } catch (Exception e) {
	    	System.out.println("error:" + e.getMessage());
	    }	
	}
}
