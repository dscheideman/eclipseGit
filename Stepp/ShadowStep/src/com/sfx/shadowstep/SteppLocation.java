/**
 * 
 */
package com.sfx.shadowstep;

import android.app.Activity;

/**
 * @author David
 *
 */
public class SteppLocation implements BackStepp {

	public String id;
	public String mLocID;
	public String mLocName;
	public String locLat;
	public String locLon;
	public String mCreationTime;

	/* (non-Javadoc)
	 * @see com.sfx.shadowstep.BackStepp#findTopActivity()
	 */
	@Override
	public Activity findTopActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sfx.shadowstep.BackStepp#findTopDBHelper()
	 */
	@Override
	public SteppDB findTopDBHelper() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sfx.shadowstep.BackStepp#dialogGetString(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String dialogGetString(String title, String question, String hintText) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sfx.shadowstep.BackStepp#dialogGetDouble(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public double dialogGetDouble(String title, String question, String hintText) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean recurSave(SteppDB steppdb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getBackID(int levels) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BackStepp getBackRef(int levels) {
		// TODO Auto-generated method stub
		return null;
	}

}
