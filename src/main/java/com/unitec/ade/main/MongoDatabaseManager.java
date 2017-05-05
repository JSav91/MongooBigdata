package com.unitec.ade.main;

import java.util.Scanner;

import com.unitec.ade.business.DataInsertManager;
import com.unitec.ade.business.SimpleQuery1;
import com.unitec.ade.business.SimpleQuery2;
import com.unitec.ade.constants.ApplicationConstants;

/**
 * Joel Savio
 *
 */
public class MongoDatabaseManager implements ApplicationConstants
{
	public static void main( String[] args )
	{	
		
	int choice ;
	
	try {
		Scanner scan = new Scanner(System.in);
		do {
			// OPTION PANE ############################################################
			
			System.out.println("Mongo Db Data Analytics on Police Crime data\n");
			System.out.println("Choose from below database operations to execute\n");
			System.out.println("1) \t Insert Dummy data\n");
			System.out.println("2) \t  Find all unsolved homicides where a male of african american \n"
					+ "\t ethnicity murdered someone of younger age, and municipal or county police \n"
					+ "\t were involved, for a given set of years (e.g. 1980 to 1990) and a given city \n"
					+ "\t and state (e.g. Milwaukee, Illinois). The City must be within the given state.\n");
			System.out.println("3) \t  Find all solved crimes in a given year range, with offender and \n"
					+ "\t victim of different ethnicity and gender, and having a difference in age of at \n"
					+ "\t least 2 years, show the weapon used and the count of crimes where weapon was used,\n"
					+ " \t ordered in sequence by agency, then state, then weapons.\n");
			System.out.println("4) \t  Find crimes where offender and victim were acquaintances, and the number\n"
					+ "\t of victims is higher than the state average, find the average age of the victims for\n"
					+ "\t the whole state.Find crimes where offender and victim were acquaintances, and the number\n"
					+ "\t of victims is higher than the state average, find the average age of the victims for the whole state.\n");
			System.out.println("5) \t  Find which weapon was used the most in each state. A Year range may optionally be specified.\n");
			System.out.println("6) \t  Find a count of unsolved crimes for a specified state, grouped by year and month\n");
			System.out.println("7) \t  Update operation\n");
			System.out.println("8) \t  Delete Operation\n");
			System.out.println("\n\nPlease enter Option number");
			//##############################################################################
			choice= new Integer(scan.nextLine()).intValue();
			switch (choice) {
			//~~~~~~~~~~~~INSERT~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			case 1:		System.out.println("Initiate Data Insert Manager");
						int count=20;
						while(count!=0){
							new DataInsertManager().insertCasesRecords();
							count--;
						}
						System.out.println("Time taken for inserting "+(CASE_COUNT*20)+" is :"+DataInsertManager.totalTimeforInsert);
						break;
			//~~~~~~~~~~~~Query~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			case 2:		
						break;
			case 3:
				break;
			case 4: 
				break;
			case 5:	System.out.println("Exceuting Read Operation for Query");
					new SimpleQuery1().runQuery();
				break;
			case 6 :System.out.println("Exceuting Read Operation for Query");
					new SimpleQuery2().runQuery();
				break;
			case 7:
				break;
			case 8 :
				break;
			} // end of switch
		} while (choice!=0); // end of loop

	} 

	catch (Exception e) {
		e.printStackTrace();
	}
	}
}
