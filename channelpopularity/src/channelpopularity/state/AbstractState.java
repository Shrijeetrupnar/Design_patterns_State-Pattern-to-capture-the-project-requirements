package channelpopularity.state;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import channelpopularity.context.ChannelContext;


/**
 * AbstractState class 
 * This class extends StateI and has implementation 
 * for add_video,metrics and remove video methods
 * @return no return
 * @author Shrijeet Rupnar
 * @version 1.0.
 * @since   06-24-2020
 */
public abstract class AbstractState implements StateI {

	/**
	 * add_video method, will first check whether the video already present in map
	 * if not then only it will add the video 
	 * @param Map of String, Map and video Name
	 * @return will return popularity score
	 * @author Shrijeet Rupnar
	 * @version 1.0.
	 * @since   06-24-2020
	 */

	@Override
	public int add_video(Map<String, Map<String, Integer>> channelContent, String videoName) {
		// TODO Auto-generated method stub

		if(channelContent.containsValue(videoName)== true )
		{
			System.out.println("Video already present");
			System.out.println("Invalid input format...");
			System.out.println("Exiting...!!!");
			System.exit(0);
			return 0;
		}
		else {
			Map<String, Integer> tempMap = new HashMap<String, Integer>();
			tempMap.put("VIEWS", 0);
			tempMap.put("LIKES", 0);
			tempMap.put("DISLIKES", 0);
			channelContent.put(videoName, tempMap);


			int popularityScore = popularityCalculator(channelContent);
			return popularityScore;

		}
	}


	/**
	 * remove_video method, will first check whether the video to be removed is
	 *  present in map or not, if present then perform remove operation
	 * @param Map of String, Map and video Name
	 * @return will return popularity score
	 * @author Shrijeet Rupnar
	 * @version 1.0.
	 * @since   06-24-2020
	 */

	@Override
	public int remove_video(Map<String, Map<String, Integer>> channelContent, String videoName) {
		// TODO Auto-generated method stub


		if(channelContent.containsKey(videoName)== true){
			channelContent.remove(videoName);
			int popularityScore = popularityCalculator(channelContent);
			return popularityScore;

		}
		else {

			System.out.println("REMOVE _VIDEO : "+videoName+ "\t doesnt exist...!");
			System.out.println("Invalid Input...!");
			System.out.println("Exiting...!");
			System.exit(0);
			return 0;
		}
	}


	/**
	 * metrics_video method, will recalculate popularity score based on
	 * views , likes and dislike 
	 * by adding new values to existing values of views , likes and dislike
	 * @param Map of String, Map and video Name, int views,int likes, int dislike
	 * @return will return popularity score
	 * @author Shrijeet Rupnar
	 * @version 1.0.
	 * @since   06-24-2020
	 */
	@Override
	public int metrics_video(Map<String, Map<String, Integer>> channelContent, String videoName, int views,
			int likes, int dislike) {
		// TODO Auto-generated method stub

		if(channelContent.get(videoName).get("VIEWS") >=0){
			if((channelContent.get(videoName).get("LIKES") + likes) >=0 && (channelContent.get(videoName).get("DISLIKES") + dislike) >=0){

				Map<String, Integer> tempMap = new HashMap<String, Integer>();
				tempMap.put("VIEWS", channelContent.get(videoName).get("VIEWS") + views);
				tempMap.put("LIKES", channelContent.get(videoName).get("LIKES") + likes);
				tempMap.put("DISLIKES", channelContent.get(videoName).get("DISLIKES") + dislike);
				channelContent.put(videoName, tempMap);
				int popularityScore = popularityCalculator(channelContent);
				return popularityScore;

			}
			else{
				System.out.println("Exiting");
				System.exit(0);
				return 0;
			}

		}else{
			System.out.println("Value of VIEWS is negative for video :"+videoName);
			System.out.println("Please enter correct input");
			System.out.println("Exiting...");
			System.exit(0);
			return 0;
		}
	}


	/**
	 * popularityCalculator method, will calculate popularity score
	 * based on views,likes and dislike.
	 * @param Map of String, Map 
	 * @return will return popularity score
	 * @author Shrijeet Rupnar
	 * @version 1.0.
	 * @since   06-24-2020
	 */

	public int popularityCalculator(Map<String, Map<String, Integer>> channelContent) {

		int score = 0;

		for(Map.Entry<String, Map<String, Integer>> content : channelContent.entrySet())	{
			score += content.getValue().get("VIEWS") + (2 * (content.getValue().get("LIKES") - content.getValue().get("DISLIKES")));
		}

		if(channelContent.size()==0)
			score /= 1;
		else
			score /= channelContent.size();

		return score;
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
