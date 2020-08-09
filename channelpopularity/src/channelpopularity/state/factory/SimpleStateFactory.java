package channelpopularity.state.factory;

import channelpopularity.state.HighlyPopularState;
import channelpopularity.state.MildlyPopulatState;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.UltraPopularState;
import channelpopularity.state.UnpopularState;


/**
 * SimpleStateFactory class 
 * This class extends SimpleStateFactoryI 
 * This class instantiates respective state class based on 
 * input parameter
 * @return no return value
 * @author Shrijeet Rupnar
 * @version 1.0.
 * @since   06-24-2020
 */

public class SimpleStateFactory implements SimpleStateFactoryI {

	/**
	 * This create method will instantiates respective state
	 * class based on input parameter
	 * @param Name of state
	 * @return objcet of state class
	 * @author Shrijeet Rupnar
	 * @version 1.0.
	 * @since   06-24-2020
	 */

	public StateI create(StateName StateN) {
		if(StateN == StateName.UNPOPULAR)
			return new UnpopularState();
		else if(StateN == StateName.MILDLY_POPULAR)
			return new MildlyPopulatState();
		else if(StateN == StateName.HIGHLY_POPULAR)
			return new HighlyPopularState();
		else if(StateN == StateName.ULTRA_POPULAR)
			return new UltraPopularState();
		else return null;
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
