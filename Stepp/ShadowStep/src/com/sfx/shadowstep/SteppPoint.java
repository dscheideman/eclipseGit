/**
 * 
 */
package com.sfx.shadowstep;

import android.content.ContentValues;
import android.location.Location;
//import android.text.format.Time;

/**
 * @author David Scheideman
 *This class is intended to be used as a data class within
 *The ShadowStep project.  It is primarily fields which are 
 *Publicly accessible and intended to be set and retrieved
 *directly.
 *
 *The methods have been expanded in this class to better fit with
 *the architecture of the rest of the project.
 *	
 *
 *While the flag String field can be used to hold any String,
 *any that are standardized or need to be "understood" by other
 *classes/objects should be defined in SteppConstants.java in order
 *to maintain consistency.
 */
public class SteppPoint {
//PUBLIC FIELDS TO BE DIRECTLY ACCESSED
	private Location location;
	public double lat = 0;
	public double lon = 0;
	public double alt = 0;
	public long timeCode = 0;	//time from epoc in millisec
	public boolean mutant = false; //used to flag unusual conditions
	public String flags = "";	//in order to notate if needed
	public SteppPoint nextLink;
	public BackStepp parent;
	public long pointID;
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	/**Public constructor:
	 * constructors with fewer arguments fill in the missing arguments
	 * and call the complete constructor
	 */
	public SteppPoint(Location loc, long time, boolean mut, String flag, BackStepp p){
		this(loc.getLatitude(), loc.getLongitude(), loc.getAltitude(), time, mut, flag, p);
		location = loc;
		//calls detailed constructor 
		//but then reassigns newly created Location() with passed reference.
	}
	
	
	public SteppPoint(BackStepp p){	//blank constructor
		this(new Location("SteppBlank"),System.currentTimeMillis() , false, "blank constructor",p);
	}//blank
	
	public SteppPoint(Location loc, BackStepp p){
		this(loc, System.currentTimeMillis(),false,"",p);
	}
	
	public SteppPoint(double latitude, double longitude,double altitude, 
			long time, boolean mut, String flag, BackStepp p){
		location = new Location("SteppPoint");
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		location.setAltitude(altitude);
		this.setLocation(location);
		timeCode = time;
		mutant = mut;
		flags = flag;
		parent = p;
	}
	
	//constructor called when rebuilding from database cursor
	public SteppPoint(long newpointID, double latitude, double longitude,
			double altitude, long time, boolean mut, String flag, BackStepp p) {
		location = new Location("SteppPoint");
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		location.setAltitude(altitude);
		this.setLocation(location);
		timeCode = time;
		mutant = mut;
		flags = flag;
		parent = p;
		pointID = newpointID;
	}
	//METHODS
	
	


	/**
	 * Removes any abnormal flags if present
	 */
	public void clearFlags(){
		mutant = false;
		flags = "";
	}
	
	
	//quick way to find the end of the list
	public SteppPoint ping(){
		if (nextLink == null) return this;
		return nextLink.ping();
	}
	
	public boolean recurSave(SteppDB db){
		if(flags != SteppConstants.DO_NOT_SAVE){
		//use passed db to save to point table
			db.putThis(SteppConstants.STEPP_POINT_TABLE, setValues(this));
		}
		//pay it forward
		if (nextLink != null){ 
			return nextLink.recurSave(db);
		}else{return true;
		}
	}
	
	public void setLocation(Location loc){
		if(!location.equals(loc)) location = loc;
		alt = location.getAltitude();
		lon = location.getLongitude();
		lat = location.getLatitude();
	}
	
	public Location getLocation(){//TODO remember why we had getLocation() and impliment it
		return location;
	}
	
	public ContentValues setValues(SteppPoint stepppoint){
		
		ContentValues values = new ContentValues();
		values.put("run_id", parent.getBackID(0));
		values.put("trip_id", parent.getBackID(1));
		values.put("altitude", stepppoint.alt);
		values.put("lattitude", stepppoint.lat);
		values.put("longitude", stepppoint.lon);
		values.put("mutant", stepppoint.mutant);
		values.put("flags", stepppoint.flags);
		return values;
	}
	
	/**probably deprecated and not used...
	 *@return returns a string formatted to be a representation of the key
	 *aspects of the SteppPoint object so it can be recreated later by parsing
	 *the string and assigning those values to a new SteppPoint object.
	 */
	public String toString(){
		return location.toString();
	}
}
