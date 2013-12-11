//package com.sfx.shadowstep;
//
//import android.os.Bundle;
//import android.app.Activity;
//import android.view.Menu;
//
//public class ShadowStepp extends Activity {
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_shadow_stepp);
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.shadow_stepp, menu);
//		return true;
//	}
//
//}


/////////////////////test code for map operation/////////////////////////////

package com.sfx.shadowstep;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

//import com.sfx.shadowutility.ShadowTimer;

public class ShadowStepp extends FragmentActivity implements BackStepp{

	ArrayList<Location> activeList;
	ArrayList<Location> loadedList;
	SteppDB masterDB;
	GoogleMap gMap;
	Fragment mapFrag;
	Fragment controlF;
	Time startTime;
	Time endTime;
	FragmentManager fragMan;
	FragmentTransaction fragTrans;
	Fragment tripFrag;
	ShadowTimer timer = new ShadowTimer();
	
	

//いいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいい
//いいいいいいいいいいいいいいいいいいいいい[[BACKSTEPP INTERFACE REQUIRED METHODS]]
//いいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいいい
	
	@Override
	public Activity findTopActivity() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public SteppDB findTopDBHelper() {
		// TODO Auto-generated method stub
		if (masterDB == null) masterDB = new SteppDB(this);
		
		return masterDB;
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
/////////////////////////////////////////////////////////////////end backstepp/////////////////////////////////////////////////
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        setContentView(R.layout.shadow_stepp);		//activity_shadow_stepp
        //initialize db if not already
        masterDB = new SteppDB(this);
        masterDB.openWritable();
        fragMan = getSupportFragmentManager();
//       MapFragment tempMF = (MapFragment)getFragmentManager().findFragmentById(R.id.mapp);
//        gMap = (GoogleMap) tempMF.getMap();
//       mapFrag = (Fragment) fragMan.findFragmentById(R.id.mapp);
//       controlF = (Fragment) fragMan.findFragmentById(R.id.controlFrag);
       tripFrag = (Fragment) getSupportFragmentManager().findFragmentById(R.id.tripFrag);
       
       fragTrans = fragMan.beginTransaction();
//       fragTrans.add(R.layout.activity_shadow_stepp, fragMan.findFragmentById(R.id.tripFrag), "tripFragTag");
       
       fragTrans.commit();
       
       /* Use the LocationManager class to obtain GPS locations */

       LocationManager mlocManager =

       (LocationManager)getSystemService(Context.LOCATION_SERVICE);

       LocationListener mlocListener = new ShadowLocationListener();

       mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
       setCurrentLocation();
       //we have a locationManager, now some interface building should be appropriate
       
       //Create and initialize Fragments to be displayed
       
       		//Map display fragment
       
       		//Trip control fragment
       
       		//Individual Dialog fragment
       
       		//Save trip dialog
       
       		//Should each load level be a separate fragment type?
       /*
        * TripSelection fragment
        */
       
       //Set up fragment manager for switching diplay configurations
       
       
       
       
    }//end onCreate()

	private void setCurrentLocation() {
		
		
	}//setCurrentLocation
	
	public void bounceLocation(Location loki){
		//determine current needs for location and set markers or current location appropriately
		
		
	}
	
	public void setWaypoint(Location wpLocation){
		
	}
	
	public void startTiming(){
		
		
	}
	
	public void endTiming(){
		
	}
	
	public void pauseTiming(){
		
	}
	
	public void unpauseTiming(){
		
	}
	
    ////////============================================================================
	public class ShadowLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location loc) {
			loc.getLatitude();
			loc.getLongitude();
			bounceLocation(loc);
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			Toast.makeText( getApplicationContext(),
					"Gps Disabled",	Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			Toast.makeText( getApplicationContext(),
					"Gps Enabled",	Toast.LENGTH_SHORT).show();
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		}//ShadowLocationListener
	//=================================================================================================
	//==============================Database/File assistant classes
	//=================================================================================================
	
//	
//	public class SteppSaver{//nixxed Constants. now reference SteppConstants Class
//		private final String[] COL_HEADINGS = {"index", "route_name", "start_location", "end_location",
//			"","","","","",};
//		private final String[] COL_TYPES = {"INTEGER PRIMARY KEY AUTOINCREMENT", "TEXT", "TEXT", "TEXT"
//			, "TEXT", "TEXT", "TEXT", "TEXT"};
//		private static final int NUMBER_OF_COLUMNS = 3;
		
//		private static final String DATABASE_NAME = "mapDB";
//		private static final int DATABASE_VERSION_NUMBER = 1;
//		private static final String DATABASE_TABLE = "shadowSteppRouteTable";
//		private static final String CREATE_TABLE = "CREATE TABLE " + DATABASE_TABLE + 
//    		" (color_index INTEGER PRIMARY KEY AUTOINCREMENT, color_value INTEGER," +
//    		" color_name TEXT, color_notes TEXT, extra_text TEXT, " +//TODO update column values
//    		"extra_int INTEGER, extra_long LONG);";
//
//		private Context mcontext;
//
//		private ShadowDbHelper mDbHelper;
//
//		private SQLiteDatabase myDB;
//		
//		
//		////////////////////////////////////////////////////
//		public SteppSaver(Context c) {
//			this.mcontext = c;
//			mDbHelper = new ShadowDbHelper(mcontext);
//			this.myDB = mDbHelper.getWritableDatabase();//?
//		}//end constructor
//		
//		public SteppSaver openReadable() throws SQLException{
//			mDbHelper = new ShadowDbHelper(mcontext);
//			this.myDB = mDbHelper.getReadableDatabase();
//			return this;
//		}//openReadable() returns SteppSaver object
//	
//		
//		
//		public void addNewRoute(SteppRoute nRoute){
//			
//		}//addNewRoute
//		
//		public SteppRoute getRoute(int index){
//			return null;
//			
//		}//getRoute
//		
//		public ArrayList<String> getRouteList(){
//			return null;
//			
//		}//getRouteList
//	
//	
//				///////////////////////////////////////////////////////////////////////////////////////
//				//helper class within SteppSaver class
//		
//		public class SteppRoute{
//			private String 	rName 						= 	"";
//			private int 	rIndex						=	0;
//			private Location rStart						=	null;
//			private Location rEnd 						=	null;
//			private ArrayList<Location> rWaypoints		= 	new ArrayList<Location>();
//			
//			//constructor(s)
//			
//			public SteppRoute(){}
//			
//			public SteppRoute(String rname){
//				rName = rname;
//			}
//			
//			public SteppRoute(String rname, int rindex, Location rstart, Location rend){
//				rName = rname;
//				rIndex = rindex;
//				rStart = rstart;
//				rEnd = rend;
//			}
//
//			
//			//getters and setters=============================================================
//			/**
//			 * @return the rName
//			 */
//			public String getrName() {
//				return rName;
//			}
//
//			/**
//			 * @param rName the rName to set
//			 */
//			public void setrName(String rName) {
//				this.rName = rName;
//			}
//
//			/**
//			 * @return the rIndex
//			 */
//			public int getrIndex() {
//				return rIndex;
//			}
//
//			/**
//			 * @param rIndex the rIndex to set
//			 */
//			public void setrIndex(int rIndex) {
//				this.rIndex = rIndex;
//			}
//
//			/**
//			 * @return the rStart
//			 */
//			public Location getrStart() {
//				return rStart;
//			}
//
//			/**
//			 * @param rStart the rStart to set
//			 */
//			public void setrStart(Location rStart) {
//				this.rStart = rStart;
//			}
//
//			/**
//			 * @return the rEnd
//			 */
//			public Location getrEnd() {
//				return rEnd;
//			}
//
//			/**
//			 * @param rEnd the rEnd to set
//			 */
//			public void setrEnd(Location rEnd) {
//				this.rEnd = rEnd;
//			}
//
//			/**
//			 * @return the rWaypoints
//			 */
//			public ArrayList<Location> getrWaypoints() {
//				return rWaypoints;
//			}
//
//			/**
//			 * @param rWaypoints the rWaypoints to set
//			 */
//			public void setrWaypoints(ArrayList<Location> rWaypoints) {
//				this.rWaypoints = rWaypoints;
//			}
//						
//			public void addPoint(Location nloc){
//				this.rWaypoints.add(nloc);
//			}
//			//quickSet and Dump
//			public ContentValues DBString(){
//				ContentValues exportVal = new ContentValues();
////				exportVal.put(key, value);"route_name", "start_location", "end_location"
//				exportVal.put("index", rIndex);
//				exportVal.put("route_name", rName);
//				exportVal.put("start_location", rStart.toString());
//				exportVal.put("end_location", rEnd.toString());
////				exportVal.put(
//				return exportVal;
//			}//end DBString()
//			
//		}//end SteppRoute class
//		
//		public class ShadowDbHelper extends SQLiteOpenHelper{
//			
//
//			public ShadowDbHelper(Context context){
//				super(context, DATABASE_NAME, null, DATABASE_VERSION_NUMBER);
//			}
//			
//			public ShadowDbHelper(Context context, String name, CursorFactory factory, int version) {
//				super(context, name, factory, version);
//				// TODO Auto-generated constructor stub
//			}
//
//			@Override
//			public void onCreate(SQLiteDatabase db) {
//				db.execSQL(buildCreateTableQuery());//method in SteppSaver Class
//			}
//
//			@Override
//			public void onUpgrade(SQLiteDatabase db, int oldVersion,
//					int newVersion) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			
//	}//SteppSaver
//	//<<<<<<<<<<<<<<<<<<<(((((((((([[[[[[[[[[{{{{{{{{{{Fragment definintions, etc}}}}}}}}}}]]]]]]]]]]))))))))))>>>>>>>>>>>>>>>>>>>	
//	//<<<<<<<<<<<<<<<<<<<(((((((((([[[[[[[[[[{{{{{{{{{{Fragment definintions, etc}}}}}}}}}}]]]]]]]]]]))))))))))>>>>>>>>>>>>>>>>>>>
//	//<<<<<<<<<<<<<<<<<<<(((((((((([[[[[[[[[[{{{{{{{{{{Fragment definintions, etc}}}}}}}}}}]]]]]]]]]]))))))))))>>>>>>>>>>>>>>>>>>>
//	//<<<<<<<<<<<<<<<<<<<(((((((((([[[[[[[[[[{{{{{{{{{{Fragment definintions, etc}}}}}}}}}}]]]]]]]]]]))))))))))>>>>>>>>>>>>>>>>>>>
//	//TASK does this show up
	public static class ControlFragment extends Fragment{

		Button buttonStart;
		Button buttonStop;
		Button buttonCenter;
		TextView tvStart;
		TextView tvCenter;
		TextView tvStop;
		ShadowTimer sTime;
		boolean paused = false;
		boolean timing = false;
		public void onResume(){
			super.onResume();
			
		}
		
		public static ControlFragment newInstance(int index){
			ControlFragment f = new ControlFragment();
			 Bundle args = new Bundle();
	         args.putInt("index", index);
	         f.setArguments(args);
	         return f;
		}
		
		
		public View OnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			if(container == null) return null;		//just in case...
			
			View rootView = inflater.inflate(R.layout.fragment_control,container,false);
			buttonStart = (Button) getActivity().findViewById(R.id.button_Start);
			buttonStop = (Button) getActivity().findViewById(R.id.button_stop);
			buttonCenter = (Button) getActivity().findViewById(R.id.button_center);
			tvStart = (TextView) getActivity().findViewById(R.id.tv_startTime);
			tvStop = (TextView) getActivity().findViewById(R.id.tv_stopTime);
			tvCenter = (TextView) getActivity().findViewById(R.id.tv_counter);
			sTime = new ShadowTimer();
			sTime.setTextView(tvCenter);
			
			buttonStart.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					if(!timing){
						timing = true;
						sTime.startTiming();
					}else{
						if(paused){//if paused, restart
							sTime.unPause();
							buttonCenter.setText(R.string.pause);
							paused = false;
						}
						//if start pressed while already timing??
					}
				}//onclick
			});//buttonstart.setonclicklistener
			
			buttonStop.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					if(timing){
						if(paused){
							sTime.unPause();
							buttonCenter.setText(R.string.pause);
							paused = false;
						}
						sTime.stopTiming();	
						timing = false;
					}else{
						
						//if stop pressed when not timing???
					}
				}//onclick
			});//buttonstop.set...
			
			buttonCenter.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					if (paused){
						sTime.unPause();
						buttonCenter.setText(R.string.pause);
						paused = false;
					}else{
						sTime.pause();
						buttonCenter.setText(R.string.button_continue);
						paused = true;					
					}//ifelse
					
				}//onclick
			});//centerbutton
			
			
			return rootView;
	       	
		}

	}//controlfragment
	
	
}//ShadowStepp class entire