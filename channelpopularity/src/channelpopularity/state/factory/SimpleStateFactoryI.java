package channelpopularity.state.factory;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;


/**
 * SimpleStateFactoryI Interface
 * This interface will have declaration of 
 * method create()
 * @return no return value
 * @author Shrijeet Rupnar
 * @version 1.0.
 * @since   06-24-2020
 */
public interface SimpleStateFactoryI {

	public StateI create(StateName StateName);

}
