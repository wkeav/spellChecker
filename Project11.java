// Astra Nguyen 
/* Spell Checker(Class Project):
 * User can enter a word to see if the spelling are correct based on the dictionary on file
 * If the word is new, user has a choice to add the word into the dictionary 
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Project11 {

	public static void main(String[] args)
		throws FileNotFoundException, IOException {

		final String FILENAME_COMMON = "common-dictionary.txt";
		final String FILENAME_PERSONAL = "personal-dictionary.txt";
		final int CAPACITY_PERSONAL_DICT = 100;
		String prompt = "Enter a word or 'quit' to stop: ";

		// Read the common dictionary and store the words in an array.
		ArrayList <String >common = readFile(FILENAME_COMMON);

		// Construct an array to arrayList 
		ArrayList <String > personal = new ArrayList <String >(CAPACITY_PERSONAL_DICT);
		
	
		
		// Construct a Scanner to read user input from the keyboard.
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Spell Checker");
		System.out.println("-------------");
		
		// Perform a priming read to get the first word + prompting user
		System.out.print(prompt);
		String word = keyboard.nextLine().toLowerCase();
		
		//  user input loop.
		while (!word.equals("quit")) {
			
			// Check if the word is in either dictionary.
			if (checkSpelling(word, common, personal)) {
				System.out.println("The word is spelled correctly.");
			} 
			else {
				System.out.println("The word was not found in the "
					+ "dictionary.");			
				System.out.println("Would you like to add it to your personal "
					+ "dictionary (yes/no)?");
				String response = keyboard.nextLine().toLowerCase();
				
				// If the user responds "yes" , personal array will add the word
				if (response.equals("yes")) {
					personal.add(word);
					Collections.sort(personal);
				}
			}
			
			// Get the next word from the user.
			System.out.println();
			System.out.print(prompt);
			word = keyboard.nextLine().toLowerCase();
		}
		
		keyboard.close();
		writeFile(FILENAME_PERSONAL, personal);
		System.out.println("Goodbye!");
	}
	
	// Read each line of a file and store them in an ArrayList
	public static ArrayList <String>  readFile(String filename) 
		throws FileNotFoundException,IOException {
			
		// Count the number of lines in the file and reading the file
	
		File file = new File (filename);
		
		file.createNewFile();
		
		Scanner fileScanner = new Scanner(file);
		
		ArrayList <String> lines = new ArrayList<String>();
		
		while (fileScanner.hasNextLine()) {
			
			lines.add(fileScanner.nextLine());
			
		}
		fileScanner.close();
		return lines;
	}
	



	// Return true if word is in either array; otherwise, return false. 
	public static boolean checkSpelling(String word, ArrayList <String >common, 
		ArrayList <String> personal) {
			
	//Using binary search to go through the array to see if it matches
		if (Collections.binarySearch(common, word) >= 0) {
			return true;
		}
		
		if (Collections.binarySearch(personal, word) >= 0) {
			return true;
		}
		
		return false;
	}
	
	// Write the nonempty elements of an ArrayList array to a given file.
	public static void writeFile(String filename, ArrayList <String > array)
			throws FileNotFoundException {
 
		PrintWriter writer = new PrintWriter(filename);

		
		for (int idx = 0; idx < array.size(); ++idx) {
			writer.println(array.get(idx));
		}

	
		writer.close();
	}
}
