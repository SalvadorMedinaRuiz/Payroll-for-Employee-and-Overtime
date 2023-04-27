// ****************************************************************
// Payroll.java
//
// Represents a list of employees.
// ****************************************************************
import java.util.Scanner;
import java.util.*;
import java.io.*;
public class Payroll
{
	final int MAX = 30;
	Employee[] payroll = new Employee[MAX];
	int numEmployees = 0;
	int numOvertimeWorkers = 0;
	//---------------------------------------------
	// Reads the list of employee wage data from the given
	// file.
	// ---------------------------------------------------------
	public void readPayrollInfo(String file)
	{
		String line; // a line in the file
		String name; // name of an employee
		int hours=0;
		double rate; // hourly pay rate
		Scanner lineScan;
		try (Scanner fileScan = new Scanner (new File(file));)
		{
			
			while (fileScan.hasNext())
			{
				line = fileScan.nextLine();
				lineScan = new Scanner(line);
				name = lineScan.next ();
				try
				{
					hours = lineScan.nextInt();
					rate = lineScan.nextDouble();
					payroll[numEmployees] = new Employee (name, hours, rate);
					numEmployees++;
				}
				catch (InputMismatchException exception)
				{
					System.out.println ("Error in input. Line ignored.");
					System.out.println (line);
				}
				catch (ArrayIndexOutOfBoundsException exception)
				{
					System.out.println ("Too many employees!");
				}
			}
		}
		catch (FileNotFoundException exception)
		{
			System.out.println ("The file " + file + " was not found.");
		}

	}
	
	// ------------------------------------------
	// Returns the number of employees who
	// worked over 40 hours; the helper method
	// overtime is called to do all the work.
	// ------------------------------------------
	public int numOvertime ()
	{
		return overtime (0);
	}
	
	// ------------------------------------------------
	// Returns the number of employees in the part
	// of the list from index start to the end who
	// worked more than 40 hours.
	// ------------------------------------------------
	private int overtime(int start) {
		
		if (start==numEmployees) { //base case
			return numOvertimeWorkers;
		}
		if (payroll[start].getHours() > 40) {
			numOvertimeWorkers++;
		}
		
		overtime(start + 1); //making it smaller (bigger)
		
		return numOvertimeWorkers;
	}
}