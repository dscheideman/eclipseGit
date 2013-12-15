/**
 * 
 */
package com.sfx.shadowstep;

//import java.util.ArrayList;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * @author David
 *
 */
public class SteppRoute implements BackStepp {

//	private ArrayList<SteppRun> runList;
	public int mRouteID;
	public int mTripID;
	public String mRouteName;
	public long mCreationTime;
	private SteppRoute parent;

	
	
	
	public long getAverage() {//average time of all runs for this route
		// TODO Auto-generated method stub
		return 0;
	}
	
	//backstepp methods///////////////////////////////////////////////////////////////////////////////
		@Override
		public Activity findTopActivity() {
			// TODO Auto-generated method stub
			return null;
		}

		
		@Override
		public SteppDB findTopDBHelper() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String dialogGetString(String title, String question, String hintText) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public double dialogGetDouble(String title, String question, String hintText) {
			// TODO Auto-generated method stub
			return 0;
		}
	//END BACKSTEP METHODS////////////////////////////////////////////////////////////////////////////////////////////////

		@Override
		public boolean recurSave(SteppDB steppdb) {
			// TODO Auto-generated method stub
			return false;
		}

//		@Override
//		public long getBackID() {
//			// TODO Auto-generated method stub
//			return 0;
//		}

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

		@Override
		public Fragment switchFrags(Fragment fragment, String tag) {
			return parent.switchFrags(fragment, tag);
			}

		@Override
		public ArrayList<Fragment> getFragmentArray() {
			// TODO Auto-generated method stub
			return null;
		}
}
