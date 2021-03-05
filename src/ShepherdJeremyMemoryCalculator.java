

/*
 * CIS 2122
 * 04/02/2020
 * Jeremy L. Shepherd
 * Memory Calculator
 * A menu, memory, state driven console program that takes in user input to 
 * calculate basic math functions of 2 numbers and return a calculation.
 * Now with a save to history capability
 * Using methods to present cleaner code.
 */

public class ShepherdJeremyMemoryCalculator  {
		
	//Primary function
	public static void main(String[] args) {
		
		Calculator_M calc = new Calculator_M();
						
		double num = 0;
		int menuChoice;
		
		System.out.println("The current value is: " + calc.getCurrentValue());
		menuChoice = Calculator_M.displayMenu();
		
		while(menuChoice != 7) {			
			
			String message = "What is the second number? ";
			
			//Only get input if needed
			if(menuChoice >= 1 && menuChoice <= 4) {
				num = Calculator_M.getOperand(message);
			}
			
			switch(menuChoice) {
				case 1:
					calc.add(num);
					break;
				case 2:
					calc.subtract(num);
					break;
				case 3:
					calc.multiply(num);
					break;
				case 4:
					calc.divide(num);
					break;
				case 5:
					calc.clear();
					break;
				case 6:
				try {
					calc.save();
				} catch (Exception e) {
					System.out.println("Error: you had a whoopsie there..." + e.getMessage());
				}
			}
			
			//refactored answer output to single line
			System.out.println("The current value is : " + calc.getCurrentValue());
			System.out.println("");
			
			//Give users a hint if they attempted to divide by zero
			if(Double.isNaN(calc.getCurrentValue())) {
				System.out.println("Current value is NaN and must be cleared (option 5) before any more\ncalculations can be performed.\n");
			}
			menuChoice = Calculator_M.displayMenu();
		}			
		
		System.out.println("Thank you for using Shepherd's Memory Calculator!");
		System.out.println("Goodbye!");
	}
}

