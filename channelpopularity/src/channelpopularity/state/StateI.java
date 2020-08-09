package channelpopularity.state;

import java.util.Map;


/**
 * StateI Interface 
 * This Interface  has declaration for methods which will be 
 * common to all state classes.
 * @return no return
 * @author Shrijeet Rupnar
 * @version 1.0.
 * @since   06-23-2020
 */
public interface StateI {

	public int add_video(Map<String, Map<String,Integer>> channelContent, String videoName);
	public int remove_video(Map<String, Map<String,Integer>> channelContent, String videoName);
	public int metrics_video(Map<String, Map<String,Integer>> channelContent, String videoName, int views, int likes,int dislikeValue);


}
