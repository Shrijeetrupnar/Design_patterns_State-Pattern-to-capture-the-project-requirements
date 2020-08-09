package channelpopularity.context;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.Results;
import channelpopularity.operation.Operation;



/**
 * ChannelContext class 
 * This class has  methods to handle input received from InputParser.
 * @author Shrijeet Rupnar
 * @version 1.0.
 * @since   06-23-2020
 */
public class ChannelContext implements ContextI {

	private StateI currentState=null;
	Map<StateName,StateI> availableStates=null;
	private SimpleStateFactoryI stateFactory= null;
	Map<String, Map<String,Integer>> channelContent =null;
	Results resultobj=null;


	/**
	 * ChannelContext is double parameterized  constructor
	 * used to instantiate the states
	 * @param stateFactoryIn stateFactory object
	 * @param stateNames names of the states
	 * @param resultsIN Results class object
	 * @author Shrijeet Rupnar
	 * @version 1.0.
	 * @since   06-23-2020
	 */
	public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames,Results resultsIN){

		channelContent = new HashMap<>();
		availableStates = new HashMap<StateName,StateI>();
		stateFactory=stateFactoryIn;

		for(int i=0;i<stateNames.size();i++) {
			StateI state1 = stateFactory.create(stateNames.get(i));
			availableStates.put(stateNames.get(i), state1);
			//System.out.println(state1.toString());
		}

		currentState=availableStates.get(StateName.UNPOPULAR);
		resultobj=resultsIN;
	}


	/**
	 * This method is used to  set the state from available states
	 * @param next state name
	 * @return no return type
	 * @author  Shrijeet Rupnar
	 * @version 1.0
	 * @since   06-23-2020
	 */

	public void setState(StateName nextState) {

		if (availableStates.containsKey(nextState)) 
		{ 
			currentState = availableStates.get(nextState);
		}
	}

	/**
	 * This method is used to handle the input and send them to desired 
	 * state class , and based on popularity score set the new state
	 * also boundary condition are also checked here
	 * @param input line from file
	 * @return no return type
	 * @author  Shrijeet Rupnar
	 * @version 1.0
	 * @since   06-23-2020
	 */

	@Override
	public void execute(String line) {
		// TODO Auto-generated method stub

		String[] str=line.split("::",2);

		if(str[0].equals(Operation.ADD_VIDEO.toString())) {

			if(channelContent.containsKey(str[1])){

				System.out.println("Video already present...!");
				System.out.println("Invalid Input Format....!");
				System.out.println("Exiting....!");
				System.exit(0);

			}
			else
			{



				int popularityScore=currentState.add_video(channelContent, str[1]);

				String result=currentState.toString()+"__VIDEO_ADDED"+"::"+str[1];
				resultobj.store(result);


				if(popularityScore>=0 && popularityScore<=1000){
					setState(StateName.UNPOPULAR);

				}
				else if(popularityScore>1000 && popularityScore<=10000){
					setState(StateName.MILDLY_POPULAR);

				}else if(popularityScore>10000 && popularityScore<=100000){
					setState(StateName.HIGHLY_POPULAR);

				}else{
					setState(StateName.ULTRA_POPULAR);

				}

			}




		}else if(str[0].equals(Operation.REMOVE_VIDEO.toString())) {

			int popularityScore=currentState.remove_video(channelContent, str[1]);
			String result=currentState.toString()+"__VIDEO_REMOVED"+"::"+str[1];
			resultobj.store(result);
			if(popularityScore>=0 && popularityScore<=1000){
				setState(StateName.UNPOPULAR);

			}
			else if(popularityScore>1000 && popularityScore<=10000){
				setState(StateName.MILDLY_POPULAR);

			}else if(popularityScore>10000 && popularityScore<=100000){
				setState(StateName.HIGHLY_POPULAR);

			}else{
				setState(StateName.ULTRA_POPULAR);

			}


		}else if(str[0].contains(Operation.METRICS.toString())){

			String[] s=str[0].split("__",2);
			Scanner in = new Scanner(line);
			in.useDelimiter("::");
			String viewsValue1, likesValue1,dislike1;
			String firstWord = in.next();
			Scanner nameScanner = new Scanner(in.nextLine());
			nameScanner.useDelimiter(",");
			viewsValue1 = getName(new Scanner(nameScanner.next()));
			likesValue1 = getName(new Scanner(nameScanner.next()));
			dislike1=getName(new Scanner(nameScanner.next()));
			String dislike2= dislike1.substring(0, dislike1.length()-1);

			try {
				int iTemp = Integer.parseInt(viewsValue1);
				int iTemp1 = Integer.parseInt(likesValue1);
				int iTemp2 = Integer.parseInt(dislike2);
			} catch(NumberFormatException e) {
				System.out.println("Invalid Format for Views, Likes and Dislike");
				System.out.println("Exiting...!");
				System.exit(0);
			}


			int viewsValue=Integer.parseInt(viewsValue1);
			int likesValue=Integer.parseInt(likesValue1);
			int dislikeValue=Integer.parseInt(dislike2);

			int popularityScore=currentState.metrics_video(channelContent ,s[1], viewsValue, likesValue, dislikeValue);
			String result=currentState.toString()+"__POPULARITY_SCORE_UPDATE"+"::"+popularityScore;
			resultobj.store(result);

			if(popularityScore>=0 && popularityScore<=1000){
				setState(StateName.UNPOPULAR);

			}
			else if(popularityScore>1000 && popularityScore<=10000){
				setState(StateName.MILDLY_POPULAR);

			}else if(popularityScore>10000 && popularityScore<=100000){
				setState(StateName.HIGHLY_POPULAR);

			}else{
				setState(StateName.ULTRA_POPULAR);

			}


		}else if(str[0].contains(Operation.AD_REQUEST.toString())){

			String[] str1=line.split("::",2);

			String[] str2=str[0].split("__",2);


			if(channelContent.containsKey(str2[1]))
			{
				String[] len=str[1].split("=",2);

				try {
					int iTemp = Integer.parseInt(len[1]);
				} catch(NumberFormatException e) {
					System.out.println("Invalid Format for length of video");
					System.out.println("Exiting...!");
					System.exit(0);
				}
				int length=Integer.parseInt(len[1]);

				if(currentState.toString().equals("UNPOPULAR") && (length >1 && length<=10))
				{
					String result=currentState.toString()+"__AD_REQUEST"+"::"+"APPROVED";
					resultobj.store(result);


				}else if(currentState.toString().equals("MILDLYPOPULAR") && (length >1 && length<=20)){

					String result=currentState.toString()+"__AD_REQUEST"+"::"+"APPROVED";
					resultobj.store(result);

				}else if(currentState.toString().equals("HIGHLYPOPULAR") && (length >1 && length<=30)){

					String result=currentState.toString()+"__AD_REQUEST"+"::"+"APPROVED";
					resultobj.store(result);

				}else if(currentState.toString().equals("ULTRAPOPULAR") && (length >1 && length<=40)){

					String result=currentState.toString()+"__AD_REQUEST"+"::"+"APPROVED";
					resultobj.store(result);

				}
				else{

					String result=currentState.toString()+"__AD_REQUEST"+"::"+"REJECTED";
					resultobj.store(result);

				}

			}
			else
			{
				System.out.println("Video associalted with ADD_REQUEST dosent exisit");
				System.out.println("Exiting...");
				System.exit(0);

			}



		}

	}



	/**
	 * This method is used to process the input file 
	 * @param input line from file
	 * @return String
	 * @author  Shrijeet Rupnar
	 * @version 1.0
	 * @since   06-23-2020
	 */
	private String getName(Scanner nameScanner){

		nameScanner.useDelimiter("=");
		String nameTitle = nameScanner.next();
		return nameScanner.next();
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
