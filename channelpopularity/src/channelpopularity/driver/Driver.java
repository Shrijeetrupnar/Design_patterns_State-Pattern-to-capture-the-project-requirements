package channelpopularity.driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.List;
import java.util.ArrayList;
import channelpopularity.context.ChannelContext;
import channelpopularity.context.ContextI;
import channelpopularity.util.FileProcessor;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.InputParser;
import channelpopularity.util.Results;


/**
 *  Driver class 
 * The Driver program implements the application,
 * It reads input, creates different objects, process the input
 * perform the write to output file and console write operation.
 * @author Shrijeet Rupnar
 * @version 1.0.
 * @since   06-23-2020
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;


	/**
	 * This is the main method which accepts input file 
	 * Fills List of state names, process the input,
	 * Using Result Object, it also prints to output file
	 * perform the write to output file and console write operation
	 * @param args Accepts command line arguments as array of strings
	 * @return void. Since it is main method
	 */

	public static void main(String[] args){

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */

		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("---------------------------Design Patterns------------------------------------------------");
		System.out.println("---------------------Assignment-2 - Summer -2020------------------------------------------");
		System.out.println("---Design and Implementation of Youtube categorize a single channel based on popularity---");
		System.out.println("------------------------------------------------------------------------------------------\n");



		if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}



		try {
			File newFile = new File(args[0]);


			if(!new File(args[0]).exists()) {
				throw new FileNotFoundException("Exception: input file not found!!");
			}
			if(newFile==null)
				throw new NullPointerException("Exception:Null pointer...!!");

			if(args[0]!=null && !"".equals(args[0]) && newFile.length()!=0){
				FileProcessor fp= new FileProcessor(args[0]);

				List<StateName> stateNames= new ArrayList<StateName>();
				StateName[] sName = StateName.values();
				for(StateName s : sName) {
					stateNames.add(s);
				}

				Results resultobj=new Results(args[1]);

				SimpleStateFactoryI SimpleStateFactoryObj= new SimpleStateFactory();

				ContextI context = new ChannelContext(SimpleStateFactoryObj,stateNames,resultobj);

				InputParser inputParser=new InputParser(fp,context);

				inputParser.processInput();

				resultobj.writeToFile();
				resultobj.display();
				System.out.println("------------------------------------------------------------------------------------------");

			}
			else
			{
				System.out.println("Invalid input file");
				System.exit(0);
			}

		}
		catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			System.out.println("Exiting...");
			System.exit(0);

		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			System.out.println("\nPlease check Input File...");
			System.out.println("Exiting...");
			System.err.println(e.getMessage());
			System.exit(0);

		} 
		catch(SecurityException e)
		{
			System.out.println(e.getMessage());
			System.out.println("\n Not valid Input file...");
			System.exit(0);
		}
		catch (InvalidPathException e)
		{
			// TODO Auto-generated catch block
			System.out.println("\nPlease check Input File...");
			System.out.println("Exiting...");
			System.err.println(e.getMessage());
			System.exit(0);

		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
			System.out.println("\n Input Output Exception...");
		}
		catch(Exception e)// Any other exceptions
		{
			System.err.println(e.getMessage());
			System.out.println("\nException...");
		}


		finally {

		}




	}
	/**
	 * toString() method
	 * @return String
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}
}
