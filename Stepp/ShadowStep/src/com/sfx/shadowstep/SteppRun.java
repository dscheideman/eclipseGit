/**
 * 
 */
package com.sfx.shadowstep;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.location.Location;
//import android.text.format.Time;
import android.support.v4.app.Fragment;

/**
 * @author David
 *There's nothing that says a linked list cannot be stored in an array as well?
 */
public class SteppRun implements BackStepp {

	public long 		mRunID;
	public long 		mRouteID;
	public long 		mTripID;
	public long 		timeAutoStart;
	public long 		timeAutoEnd;
	public long 		timePaused;
	public long 		timeManStart;
	public long 		timeManEnd;
	private long 		pauseBegin;
	private long		pauseEnd;
	private SteppPoint 	anchor;
	public BackStepp 	mParent;
	public SteppDB 		db;
	public ArrayList<SteppPoint>	pointList;//?drop ArrayList and stick with LinkedList?
	
//	public int waypointCount;//?
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤
	//CONSTRUCTORS
	//TODO >>initial constructor and reconstructor
	
	/**
	 * This constructor is for use when creating a new run
	 * @param parent must implement the BackStepp interface, will usually be a SteppRoute.
	 */
	public SteppRun(BackStepp parent){//TODO >>stepprun constructor
		timePaused = 0;
		anchor = new SteppPoint(new Location("anchor"), 0,true,SteppConstants.DO_NOT_SAVE, this);
		//get info about parents
		mRouteID = getBackID(1);
		mTripID = getBackID(2);
		
		db = parent.findTopDBHelper();
		if(db == null){
			db = new SteppDB(findTopActivity());
			db.openWritable();
		}
		mRunID = db.putThis(SteppConstants.STEPP_POINT_TABLE, addRun(this));
	}//end new constructor
	
	public SteppRun(long run_id, BackStepp parent){
		db = parent.findTopDBHelper();
		if(db == null){
			db = new SteppDB(findTopActivity());
			db.openWritable();
		}
		
	}//end load constructor
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤	   »[[METHODS]]«
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤
	
//>>>>>for when loading from database
	//Loads Arraylist of waypoints from database
	public int loadPoints(){//TODO >>complete SteppRun.loadpoints()
		//access db
		db = findTopDBHelper();
		String where_parameter = Long.toString(mRouteID);
		//query db and get all points with this.mRouteID in "route_id" column 
		Cursor pulledRows = db.getCursor(SteppConstants.STEPP_POINT_TABLE, SteppConstants.STEPP_POINT_COL_NAME, "route_id", where_parameter );
		//so now you have a cursor...do something with it.
		buildPoints(pulledRows);
		return pointList.size();
	}
	
	/**
	 * @param curse a Cursor object that resulted from a SQLite query of the points table
	 */
	private void buildPoints(Cursor curse){
		//local temp fields
		long 	lPointID;
		@SuppressWarnings("unused")
		long 	lRunID;
		double 	lAlt;
		double 	lLat;
		double 	lLon;
		long	lTime;
		boolean	lMutant;
		String	lFlags;
		
		curse.moveToFirst();
		//test for valid cursor
		if (curse.getCount() == 0) return;
		if (curse.isClosed()) return;
		if (curse.getColumnCount() != 8) return;
		if (curse.getColumnIndex(SteppConstants.STEPP_POINT_COL_NAME[6]) == -1) return;
		if (pointList == null) {
			pointList = new ArrayList<SteppPoint>();
		}
		
		/*loop logic:
		 * do, first time.  increment. test if cursor has gone off the end of the last row
		 * case 1 row: run through row 0, move to 1 (off end), test !true, does not loop. 
		 */
		do{
			lPointID = curse.getLong(curse.getColumnIndexOrThrow(SteppConstants.STEPP_POINT_COL_NAME[0]));
			lRunID = curse.getLong(curse.getColumnIndexOrThrow(SteppConstants.STEPP_POINT_COL_NAME[1]));
			lAlt = curse.getDouble(curse.getColumnIndexOrThrow(SteppConstants.STEPP_POINT_COL_NAME[2]));
			lLat = curse.getDouble(curse.getColumnIndexOrThrow(SteppConstants.STEPP_POINT_COL_NAME[3]));
			lLon = curse.getDouble(curse.getColumnIndexOrThrow(SteppConstants.STEPP_POINT_COL_NAME[4]));
			lTime = curse.getLong(curse.getColumnIndexOrThrow(SteppConstants.STEPP_POINT_COL_NAME[5]));
			lMutant = (curse.getInt(curse.getColumnIndexOrThrow(SteppConstants.STEPP_POINT_COL_NAME[6])) == 0)? false : true;
			lFlags = curse.getString(curse.getColumnIndexOrThrow(SteppConstants.STEPP_POINT_COL_NAME[7]));
			SteppPoint newP = new SteppPoint(lPointID, lLat, lLon, lAlt, lTime, lMutant, lFlags, this);
			anchor.ping().nextLink = newP; //returns end of list and sets reference to new point
			pointList.add(newP);//adds point to arraylist
			curse.moveToNext();
		}while(!curse.isAfterLast());
	}//end buildpoints
	
//>>>>>for use during creation of the run	
	//adds new SteppPoint to Arraylist, called by Trip Fragment during trip
	public int addWayPoint(Location loc){
		SteppPoint ppp = new SteppPoint(loc, System.currentTimeMillis(),false,"", this);
		if (pointList == null) {
			pointList = new ArrayList<SteppPoint>();
		}
		anchor.ping().nextLink = ppp; //returns end of list and sets reference to new point
		pointList.add(ppp);
		return pointList.size();
	}//addwaypoint(location)
	
	
	//begin TIMER methods
	public void autoStart(){
		timeAutoStart = System.currentTimeMillis();
	}
	
	public void manStart(){
		timeManStart = System.currentTimeMillis();
	}
		//verification should be used before this call
	public void autoStop(){
		timeAutoEnd = System.currentTimeMillis();
		//total up time
	}
	
		//verification of intent should be used before this call
	public void manStop(){
		timeManEnd = System.currentTimeMillis();//TODO update entry in DB to include newly aquired data
		//total up time
	}
	
	public void pause(){
		pauseBegin = System.currentTimeMillis();
	}
	
	public void unpause(){
		//totals up time paused
		pauseEnd = System.currentTimeMillis();
		timePaused = (timePaused + (pauseEnd - pauseBegin));
		pauseBegin = pauseEnd = 0;
	}
	
	public ContentValues addRun(SteppRun steppRun){
		
		ContentValues values = new ContentValues();
		values.put("route_id", steppRun.mRouteID);
		values.put("trip_id", steppRun.mTripID);
		values.put("td_auto_start",steppRun.timeAutoStart);
		values.put("td_auto_end", steppRun.timeAutoEnd);
		values.put("time_paused", steppRun.timePaused);
		values.put("td_man_start", steppRun.timeManStart);
		values.put("td_man_end", steppRun.timeManEnd);
		return values;
	}
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤	   »[[BACKSTEPP REQUIRED METHODS]]«
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤
	
	@Override
	public boolean	recurSave(SteppDB steppdb){//? do we still need this?
		//saves self
		steppdb.putThis(SteppConstants.STEPP_POINT_TABLE, addRun(this));
		//recurrs through children (if loaded)
		return anchor.recurSave(steppdb);
		
		}
			
	
	
	@Override
	public Activity findTopActivity() {
		return mParent.findTopActivity();
	}

	@Override
	public SteppDB findTopDBHelper() {
		return mParent.findTopDBHelper();
	}

	@Override
	public String dialogGetString(String title, String question, String hintText) {
		return mParent.dialogGetString(title, question, hintText);
	}

	@Override
	public double dialogGetDouble(String title, String question, String hintText) {
		return mParent.dialogGetDouble(title, question, hintText);
	}
	
	@Override
	public BackStepp getBackRef(int levels){
		if(levels <= 0) return this;
		levels--;
		return mParent.getBackRef(levels);
	}
	
	@Override
	public long getBackID(int levels) {
		if(levels <= 0) return mRouteID;
		levels--;
		return mParent.getBackID(levels);
	}
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤	END          »[[BACKSTEPP]]«
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤

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
