/**
 * 
 */
package com.sfx.shadowstep;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
//import android.text.format.DateFormat;
import android.support.v4.app.Fragment;

/**
 * @author David
 *
 */
public class SteppTrip implements BackStepp {

	int						mTripID;
	String					mTripName = "unknown Name";
	long					mCreationTime;
	long					mModifiedTime;
	String					startName = "unknown start location";
	String					endName =	"unknown end location";
	ArrayList<SteppRoute> 	routeList;
	SteppLocation 			startPoint;
	SteppLocation			endPoint;
	SteppDB					sDB;
	BackStepp				steppParent; //should be the top activity for a SteppTrip object
	int						avgTripTime;
	int						numberOfRoutes;
	
	
	
	
	//for newly created stepptrip
	public SteppTrip(BackStepp parent, SteppDB db, String tripName){
		//assign arguments to equiv fields
		steppParent = parent;
		mTripName = tripName;
		Date myDate = new Date();
	    long timeMilliseconds = myDate.getTime();
		mCreationTime = timeMilliseconds;
		if(db != null) sDB = db;
		
		//verify DB
		if(sDB==null) sDB = this.findTopDBHelper();
//		if(sDB.isClosed()) sDB.openWritable();
		//create DBentry for this new Trip, uses tripName
		mTripID = sDB.addTrip(this);
	}//end new stepptrip
	
	
	
	//for loading existing trip
	public SteppTrip(BackStepp parent, SteppDB db, int tripID){
		//assigns arguments to equivilant internal fields
		
		//checks for valid steppDB
		
		//loads data from trip_table into local fields
		
		//loads data from loc_table into local fields
		
	}
	
	/**
	 * writes trip object to trip table in database
	 * can be called internally or externally
	 */
	public void saveToDB(){
		//some decisions need to be made on how to implement this FIXME
	}
	
	/**
	 * @param newStart Since SteppLocation object was already created, it should already be in loctable of DB
	 */
	public void setStart(SteppLocation newStart){
		//verify loc in DB?
		startPoint = newStart;
		startName = startPoint.getName();
	}
	
	
	//called internally when more information needed about this SteppTrip object
	@SuppressWarnings("unused")
	private void loadRoutes(){
		//checks for valid steppDB, calls for one if none found
		
		//queries for routes with this trip_ID in routeTable
		
		//loads ArrayList<SteppRoute> with routes from DBCursor
		
		//uses loaded routes to calculate some information to display to user
		
		
		
	}
	
	public String getCreated(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Create a calendar object that will convert the date and time value in milliseconds to date. 
		   Calendar calendar = Calendar.getInstance();
		 calendar.setTimeInMillis(mCreationTime);
		 return formatter.format(calendar.getTime());
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
		public void switchFrags(Fragment f) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public ArrayList<Fragment> getFragmentArray() {
			// TODO Auto-generated method stub
			return null;
		}
}
