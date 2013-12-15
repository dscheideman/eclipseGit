/**
 * 
 */
package com.sfx.shadowstep;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.Fragment;
import android.util.Log;

import static com.sfx.shadowstep.SteppConstants.*;

/**
 * @author David
 *
 */
public class SteppDB implements BackStepp {

	//fields
	SQLiteDatabase db;
	SteppConstants sc = new SteppConstants();
	SteppDbHelper DBhelp;
	
	
	public SteppDB(Context context) {
		DBhelp = new SteppDbHelper(context);
		db = DBhelp.getWritableDatabase();
		db.execSQL(buildCreateTableQuery(STEPP_RUN_TABLE, STEPP_RUN_COL_NAME, STEPP_RUN_COL_TYPE));
		db.execSQL(buildCreateTableQuery(STEPP_LOC_TABLE, STEPP_LOC_COL_NAME, STEPP_LOC_COL_TYPE));
		db.execSQL(buildCreateTableQuery(STEPP_TRIP_TABLE, STEPP_TRIP_COL_NAME, STEPP_TRIP_COL_TYPE));
		db.execSQL(buildCreateTableQuery(STEPP_ROUTE_TABLE, STEPP_ROUTE_COL_NAME, STEPP_ROUTE_COL_TYPE));
		db.execSQL(buildCreateTableQuery(STEPP_POINT_TABLE, STEPP_POINT_COL_NAME, STEPP_POINT_COL_TYPE));
		// TODO Auto-generated constructor stub
	}

	/**
	 * Used to retrieve a Cursor with the indicated rowID from the indicated table
	 * @param tableName name of the table in the database to be searched
	 * @param row_id row ID number desired from the table
	 * @return a Cursor object pointing to the requested row.  If no such exists,
	 * returns null.
	 */

	public Cursor getCursor(String tableName, long row_id){//just for the rowID
		db = DBhelp.getReadableDatabase();
		String[] arrrgs = {Long.toString(row_id)};
		Cursor returnCursor = db.query(tableName, null, "rowid = ?", arrrgs, null, null, null);
//		db.close();
		return returnCursor;
	}
	
	/**
	 * Used to retrieve a Cursor with the indicated rowID and the indicated columns 
	 * from the indicated table
	 * @param tableName name of the table in the database to be searched
	 * @param columns An array of String objects specifying the columns to return.
	 * best practice would be to use java to get the column list for the table 
	 * you are querying.
	 * @param row_id row ID number desired from the table
	 * @return a Cursor object pointing to the requested row.  If no such exists,
	 * returns null.
	 */
	public Cursor getCursor(String tableName, String[] columns, long row_id){//specify columns to return (use java
		db = DBhelp.getReadableDatabase();
		String[] selectionArgs = {Long.toString(row_id)};
		Cursor returnCursor = db.query(tableName, columns, "rowid = ?", selectionArgs,null,null, null);
//		db.close();
		return returnCursor;
	}
	
	public Cursor getCursor(String tableName, String[] return_columns, String search_column, String where_parameter){
		//for querying other parameters
		db = DBhelp.getReadableDatabase();
		String[] selectionArgs = {where_parameter};
		String selection = search_column + " = ";
		Cursor returnCursor = db.query(tableName, return_columns, selection, selectionArgs, null, null, null);
//		db.close();
		return returnCursor;
	}
	//NOTE: db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
	public Cursor getAllRows(String tableName){
		db = DBhelp.getReadableDatabase();
		String[] selectArgs = {};
		Cursor cc = db.rawQuery("SELECT * from " + tableName, selectArgs);
//		db.close();
		return cc;
	}

	/**
	 * Arguments to this method should (probably) use project constants such as those defined in 
	 * SteppConstants.java.  At least that was the intent when this was created.
	 * @param tableName the name of the table ex: STEPP_RUN_TABLE
	 * @param colNames	A list of the column headings. Ex.: STEPP_RUN_COL_NAMES
	 * @param colTypes  A list of SQLite column keywords which correspond one to one with colNames[] array
	 * @return A formatted query string to build the described table in SQLite
	 */
	private String buildCreateTableQuery(String tableName, String[] colNames, String[] colTypes){
		String queryBuffer = "CREATE TABLE IF NOT EXISTS " + tableName + " (";
		for (int i = 0; i <colNames.length; i++){
			if (i > 0)queryBuffer += ", " ;
			queryBuffer += colNames[i] + " " + colTypes[i];
		}//for loop
		queryBuffer += ");";
		return queryBuffer;
	}//build tableQuery

	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void openWritable() {
		db = DBhelp.getWritableDatabase();
		
	}

	public int addTrip(SteppTrip steppTrip) {
		// TODO Auto-generated method stub
		if (db == null) db = DBhelp.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("trip_id", steppTrip.mTripID);
		values.put("trip_name", steppTrip.mTripName);
		values.put("start_loc_id", steppTrip.startPoint.id);
		values.put("end_loc_id", steppTrip.endPoint.id);
		values.put("td_tp_created", steppTrip.mCreationTime);
		return (int)db.insert(STEPP_TRIP_TABLE, null, values);
	}
	
//	public int addRun(SteppRun steppRun){
//		if (db == null) db = DBhelp.getWritableDatabase();
//		ContentValues values = new ContentValues();
//		values.put("run_id", steppRun.mRunID);
//		values.put("route_id", steppRun.mRouteID);
//		values.put("route_name", steppRun.mRouteName);
//		values.put("trip_id", steppRun.mTripID);
//		values.put("trip_name", steppRun.mTripName);
//		values.put("td_auto_start",steppRun.timeAutoStart);
//		values.put("td_auto_end", steppRun.timeAutoEnd);
//		values.put("time_paused", steppRun.timePaused);
//		values.put("td_man_start", steppRun.timeManStart);
//		values.put("td_man_end", steppRun.timeManEnd);
//		values.put("file_path", steppRun.filepath);
//		values.put("waypoint_count", steppRun.waypointCount);
//		return (int)db.insert(STEPP_RUN_TABLE, null, values);
//	}
	
	public void saveRun(SteppRun steppRun) {
		// Check for existing row
		
	}
	
	public long putThis(String tableName, ContentValues cv){
		if (db == null) db = DBhelp.getWritableDatabase();
		return db.insert(tableName, null, cv);
	}
	
	public int addRoute(SteppRoute steppRoute){
		if (db == null) db = DBhelp.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("route_id", steppRoute.mRouteID);
		values.put("trip_id", steppRoute.mTripID);
		values.put("route_name", steppRoute.mRouteName);
		values.put("avg_route_time", steppRoute.getAverage());
		values.put("td_rt_created", steppRoute.mCreationTime);
//		values.put("td_tp_created", steppRoute.mCreationTime);
		return (int)db.insert(STEPP_ROUTE_TABLE, null, values);
	}
	
	public int addLocation(SteppLocation steppLoc){
		if (db == null) db = DBhelp.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("loc_id", steppLoc.mLocID);
		values.put("loc_name", steppLoc.mLocName);
		values.put("loc_lat", steppLoc.locLat);
		values.put("loc_lon", steppLoc.locLon);
		values.put("td_loc_created", steppLoc.mCreationTime);		
		return (int)db.insert(STEPP_LOC_TABLE, null, values);
		
	}
	
//	public int addPoint(SteppPoint stepppoint, int runID){
//		if (db == null) db = DBhelp.getWritableDatabase();
//		ContentValues values = new ContentValues();
//		values.put("run_id", runID);
//		values.put("altitude", stepppoint.alt);//TODO here dumbass!!!!!
//		values.put("lattitude", stepppoint.lat);
//		values.put("longitude", stepppoint.lon);
//		values.put("mutant", stepppoint.mutant);
//		values.put("flags", stepppoint.flags);
//		return (int)db.insert(STEPP_POINT_TABLE, null, values);
//	}
	
/////////////////////SUPPORT CLASSES TO ASSIST WITH DB ACCESS/////////////
public class SteppDbHelper extends SQLiteOpenHelper {
// If you change the database schema, you must increment the database version.


public SteppDbHelper(Context context) {
super(context, STEPP_DB_NAME, null, STEPP_DB_VERSION);

}
@Override
public void onCreate(SQLiteDatabase db) {


}
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
Log.w("Saved Colors Table","Upgrading DB: dropping and rebuilding table");
db.execSQL("DROP TABLE IF EXISTS "+ STEPP_RUN_TABLE);
onCreate(db);
}
}//end SteppDbHelper inner class

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


@Override
public Fragment switchFrags(Fragment fragment, String tag) {
	return null;
	
}

@Override
public ArrayList<Fragment> getFragmentArray() {
	// TODO Auto-generated method stub
	return null;
}


	
}
