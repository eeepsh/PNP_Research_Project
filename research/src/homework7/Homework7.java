package homework7;
import java.util.Scanner;

public class Homework7 {
	public static void main(String[] args) {

		System.out.println ("City Mileage w/ Coffee Shops ver. 1.0 (C) 2019 Cindy Pham");
		System.out.println ("The cities in the database are: ");
		System.out.println ("1. Dallas (85 coffee shops)\n" + 
				"2. Houston (100 coffee shops)\n" + 
				"3. Beaumont (20 coffee shops)\n" + 
				"4. Austin (75 coffee shops)\n" + 
				"5. El Paso (44 coffee shops)\n" + 
				"6. Lubbock (59 coffee shops)\n" + 
				"7. Texarkana (37 coffee shops)\n" + 
				"8. Corpus Christi (39 coffee shops)\n");
		
		String [] city = {
				"Dallas", "Houston", "Austin", "El Paso", "Lubbock", "Texarkana", "Corpus Christi"
		}; 
		
		int [] coffeeshops = {85, 100, 20, 75, 44, 59, 37, 39 };
		
		double [] [] mileage = {
				{ 0, 239, 289, 195, 345, 177, 411 },
				{ 239, 0, 165, 747, 532, 290, 207 },
				{ 289, 165, 0, 577, 373, 372, 217 },
				{ 195, 747, 577, 0, 365, 811, 696 },
				{ 345, 532, 373, 365, 0, 522, 530 },
				{ 177, 290, 372, 811, 522, 0, 498 },
				{ 411, 207, 217, 696, 530, 498, 0 }		
		};
		
		String s1; //first city
		String s2; //second city
		
		Scanner in = new Scanner (System.in);
		System.out.println ("Enter the starting city: ");
		s1 = in.nextLine();
		
		System.out.println ("Enter the destination city: ");
		s2 = in.nextLine();
		
		int row, j=0, k=0; 
		for (row = 0; row<city.length; row++)
			if (city[row].equalsIgnoreCase(s1)) {
				j=row;
				break;
	}
		
		int col;
		for (col = 0; col<city.length; col++)
			if (city[col].equalsIgnoreCase(s2)) {
				k=col;
				
				break;
			}
		
		System.out.println ("Mileage: " + mileage [row][col] + " miles");
		
	
		int n1 = coffeeshops[j];
		int n2 = coffeeshops [k];
		System.out.println ("Estimated # of coffees shops: " + n1);
		System.out.println ("Estimated # of coffees shops: " + n2);
		
		int n3 = (n1+n2)/2;
		
		System.out.println ("Estimated # of coffees shops: " + n3);
	
		
	}


}
