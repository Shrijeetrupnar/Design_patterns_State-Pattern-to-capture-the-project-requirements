package channelpopularity.util;

import java.io.IOException;
import channelpopularity.context.ContextI;

/**
 * InputParser class 
 * This class has  methods to parse input and perform validation.
 * @author Shrijeet Rupnar
 * @version 1.0.
 * @since   06-23-2020
 */

public class InputParser {

	FileProcessor fp=null;
	ContextI context=null;


	/**
	 * InputParser is constructor to Initialize the data memebers
	 * @param fp FileProcessor objcet
	 * @param contextIn Context object
	 * @author Shrijeet Rupnar
	 * @version 1.0.
	 * @since   06-23-2020
	 */
	public InputParser(FileProcessor fp, ContextI contextIn) {
		this.fp=fp;
		context=contextIn;
	}


	/**
	 * processInput method perform poll operation and return line from 
	 * input file and send the input to ChennelContext
	 * @return no return
	 * @author Shrijeet Rupnar
	 * @version 1.0.
	 * @since   06-23-2020
	 */

	public void processInput()  {
		String poll;
		//String regex="[a-dA-Z]";

		try {
			while((poll=fp.poll())!=null&&!(poll.isEmpty()))
			{

				//if(poll.matches("^[A-Z0-9::]+"))
				context.execute(poll);
			}

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
