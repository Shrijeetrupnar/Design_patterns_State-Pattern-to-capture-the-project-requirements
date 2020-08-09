package channelpopularity.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



/**
 * Results class 
 * This class has  methods to store,writeToFile and display result.
 * @author Shrijeet Rupnar
 * @version 1.0.
 * @since   06-23-2020
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	String result="";
	String outputFile;

	public Results(String outputFileIn){
		outputFile=outputFileIn;

	}


	/**
	 * store method is used to persist result into string
	 * @param result objcet
	 * @author Shrijeet Rupnar
	 * @version 1.0.
	 * @since   06-23-2020
	 */
	@Override
	public void store(String resultIn) {
		// TODO Auto-generated method stub


		result=result+resultIn+"\n";

	}

	/**
	 * writeToFile method to write output to file
	 * @author Shrijeet Rupnar
	 * @version 1.0.
	 * @since   06-23-2020
	 */
	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub




		try {
			File myObj = new File(outputFile);
			myObj.createNewFile();
		}
		catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			System.out.println("Exiting...");
			System.exit(0);

		}catch (IOException e1) {
			// TODO Auto-generated catch block
			System.err.println("Could not create new file");
		}

		try {

			FileWriter myWriter = new FileWriter(outputFile);
			myWriter.write(result);
			myWriter.close();



		}//try
		catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			System.out.println("Exiting...");
			System.exit(0);

		}
		catch(IOException e) {
			System.err.println("Exception: writing to output.txt");
			System.err.println("Exiting...");
			System.exit(0);
		}

	}



	/**
	 * display method to write output to on console
	 * @author Shrijeet Rupnar
	 * @version 1.0.
	 * @since   06-23-2020
	 */
	@Override
	public void display() {
		// TODO Auto-generated method stub

		System.out.println(result);

	}

}
