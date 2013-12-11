/**
 * 
 */
package com.sfx.shadowstep;

import android.app.Activity;
import static com.sfx.shadowstep.SteppConstants.*;

/**

 * @author David
 *Designed for use in ShadowStepp project.  All classes used in the ShadowStepp project
 *should implement this interface to facilitate both communication between objects and 
 *activities/fragments but to allow excecution of recursive methods as well, since the 
 *exact layout of the objects will be dynamic, these recursive functions will allow 
 *easy implementation of actions across unknown distributions of objects.
 */
@SuppressWarnings("unused")
public interface BackStepp {
	
	/**
	 * @return a reference to the top activity 
	 * RECURSIVE UP
	 * used by objects in ShadowStep to obtain a reference to the top level activity if they 
	 * are not otherwise provided with one.
	 * 
	 * intermediate levels should pass the method call up to parent
	 * top level should return itself back down recursive chain.
	 */
	public Activity findTopActivity();
	
	/**
	 * @return a reference to the top level SteppDB (SQLite Helper) object
	 * RECURSIVE UP
	 * used by objects in the ShadowStep package to obtain a reference to the top level
	 * SteppDB object if they are not otherwise provided with one.
	 * 
	 * intermediate levels should pass the method call up to parent
	 * top level should return its SteppDB object back down recursive chain.
	 */
	public SteppDB findTopDBHelper();
	
	/**
	 * @param title	Title to apply to inquiry dialog. Should be a reference to R.strings...
	 * @param question Question to be displayed. Should be a reference to R.strings...
	 * @param hintText Text to use as hintText within dialog.  Should reference a string value in R.Strings
	 * @return String entered by user.
	 * 
	 * Method call is passed up through implemented classes.
	 * Activity is responsible for handling dialog interchange (including cancel button)
	 * and returning the entered string.
	 * 
	 * If dialog is cancelled or otherwise no adequate response is received:
	 * Dialog should return SteppConstants.DIALOG_CANCEL constant.
	 */
	public String dialogGetString(String title, String question, String hintText);
	
	
	/**
	 * @param title	Title to apply to inquiry dialog. Should be a reference to R.strings...
	 * @param question Question to be displayed. Should be a reference to R.strings...
	 * @param hintText Text to use as hintText within dialog.  Should reference a string value in R.Strings
	 * @return numerical value entered by user.
	 * 
	 * Same as dialogGetString() except return value is a double.
	 */
	public double dialogGetDouble(String title, String question, String hintText);
	
	public boolean	recurSave(SteppDB steppdb);
		
	/**
	 * Used to retrieve an ID number from a BackStepp object. The intended ID
	 * number would be a reference to the SQLite row ID where that object
	 * is stored.  If calling this method, you should already know what type
	 * of BackStepp object you are calling to. (use getBackRef(int) if needed.)
	 * This method is intended to be originally called from within 
	 * its own object. (this.getBackID(x)).  The method body should check 
	 * the current levels index, if it is not 0 or less, decrement the level
	 * index and pass it as the argument to that objects parent.  If the index
	 * is 0 or less, return the ID field of that object (return myIdNumber;) 
	 * If the object indicated by the index does not have a ID number or no parent
	 * object to pass the recursion to, the recommended return would be 0. 
	 * Alternatively if the return can be used to pass a code back to the 
	 * calling object that can be checked for and treated accordingly.  These
	 * codes are not yet established, but should be defined in SteppConstants.java
	 * to maintain consistency across Classes implementing the BackStepp interface.
	 * @param levels number of levels to recursively move. In the default
	 * implementation, the direction of movement is 'up' through the 'parent'
	 * object.  For example; an argument of 2 should return the SQLite rowID of the
	 * grandparent object.
	 * @return returns the SQLite rowID for the indicated object.
	 */
	public long getBackID(int levels);
	
	
	/**
	 * Used to retrieve a reference to an object in the hierarchy of BackStepp
	 * objects.  This method is intended to be originally called from within 
	 * its own object. (this.getBackRef(x)).  The method body should check 
	 * the current levels index, if it is not 0 or less, decrement the level
	 * index and pass it as the argument to that objects parent.  If the index
	 * is 0 or less, return a reference to itself (return this;) 
	 * When implemented at the top of the hierarchy (The Activity, or fragment)
	 * regardless of index level, should return a reference to itself.
	 * @param levels number of levels to recursively move. In the default
	 * implementation, the direction of movement is 'up' through the 'parent'
	 * object.  For example; an argument of 2 should return a reference to the
	 * grandparent object.
	 * @return returns a reference to the object.
	 */
	public BackStepp getBackRef(int levels);
}
