package com.sfx.shadowstep;




import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import static com.sfx.shadowstep.SteppConstants.*;

public class SteppFragTrip extends SteppFrag implements BackStepp{

	BackStepp parent;
	SteppDB sdb;
	Activity steppActivity;
	//layout components
	EditText editText_NewName;
	Button	button_load;
	Button	button_new;
	Spinner	spinner_savedTrips;
	int[] listViewMap = {R.id.list_title, R.id.list_info1, R.id.list_info2, R.id.list_info3};
	String[] listColRef = {STEPP_TRIP_COL_NAME[0],STEPP_TRIP_COL_NAME[4], STEPP_TRIP_COL_NAME[2], STEPP_TRIP_COL_NAME[3]};
	protected int selectedListItemRow;
	private Cursor c;
	protected int selectedListItemID;
	ViewGroup mContainer;// could be important
	
//	public SteppFragTrip(BackStepp backSteppParent) {
//		parent = backSteppParent;
//	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception

		try {
			parent = (BackStepp) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement BackStepp Interface");
		}//try,catch,exception BackStepp Interface
	}
	
	
	public SteppFragTrip(){}	//required default constructor

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.fragment_stepp_trip,container,false);
		mContainer = container;
		return rootView;
	}
	
	public void onResume(){
		new Fragment().onResume();//TODO this Resume method may break if I cannot call super.onResume()
		super.proxyOnResume();
		//build layout associations
		steppActivity = findTopActivity();
		editText_NewName = (EditText) getActivity().findViewById(R.id.SFT_editText_newTripName);
		button_load = (Button) getActivity().findViewById(R.id.SFT_button_loadTrip);
		button_new = (Button) getActivity().findViewById(R.id.SFT_button_newTrip);
		spinner_savedTrips = (Spinner) getActivity().findViewById(R.id.SFT_spinner_triplist);
		//TODO populate spinner with available trips
		sdb = findTopDBHelper();
		c = sdb.getAllRows(STEPP_TRIP_TABLE);
		//NEED TO CHANGE COLUMN ID IN CURSOR TO MATCH "_id" to prevent exception...
		SteppCursorWrapper wc = new SteppCursorWrapper(c,0);
//!!!!!!!>>>>>>>		//SCA(Context where ListView is Running, int Resource.ID for ListItemLayoutFile, Cursor-the cursor, String[] "from" List of the columns to map to listItems, int[] list of textView resource IDs to map the "from" columns to, int Flags sets requery preference.
		int[] listViewMap = {R.id.list_title, R.id.list_info1, R.id.list_info2, R.id.list_info3};
		String[] listColRef = {STEPP_TRIP_COL_NAME[0],STEPP_TRIP_COL_NAME[4], STEPP_TRIP_COL_NAME[2], STEPP_TRIP_COL_NAME[3]};
		SimpleCursorAdapter scAdapt = new SimpleCursorAdapter((Context)this.getActivity(), R.layout.stepp_list_item_layout, wc, listColRef ,listViewMap,0);
		spinner_savedTrips.setAdapter(scAdapt);
		
		//set button onClickListeners
		button_new.setClickable(false);
		button_load.setClickable(false);
		button_new.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Button_new 
				//create new SteppTrip object with name Entered, ID will be assigned by sqlite
				//display newTripFragment to collect info on locations.
				ArrayList<Fragment> fraggs = getFragmentArray();
				switchFrags(F_TAG_CONTROL);
				
			}});
		button_load.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Button_load Auto-generated method stub
				
			}
		});
		//set spinner selection to make load button clickable
		spinner_savedTrips.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				button_load.setClickable(true);
				button_load.setText("Load: " + c.getString(1)); //TODO if wrong text is showing up on load button, this is the culprit
				selectedListItemID = c.getInt(0);
				selectedListItemRow = arg2;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				button_load.setClickable(false);
				
			}
		});
		//set name entry to make newTrip button clickable
		editText_NewName.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				if(arg0.length()>0) button_new.setClickable(true);
				else{button_new.setClickable(false);}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if(count>0) button_new.setClickable(true);
				else{button_new.setClickable(false);}
			}});
		
		
//		Saved for quick reference building this view
//		saveSwatch = (SurfaceView) getActivity().findViewById(R.id.sampleSwatch);
//		@Override
//			public void onClick(View v) {
//				nameValue = nameEntry.getText().toString();
//				noteValue = noteEntry.getText().toString();
//				if (nameValue == "") nameValue = getDateTime();//default "name" if none provided
//				noteValue = noteValue + " / " + Integer.toString(yankedColor) + " / " + getDateTime();
//				mColorSaver.SaveColor(yankedColor, nameValue, noteValue);
//			}});//end saveButton onClickListener
		}//onResume


	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		
	}
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤	   »[[BACKSTEPP METHODS]]«
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤
//	@Override
//	public Activity findTopActivity() {
//		return parent.findTopActivity();
//	}

//	@Override
//	public SteppDB findTopDBHelper() {
//		return parent.findTopDBHelper();
//
//	}
//
//	@Override
//	public String dialogGetString(String title, String question, String hintText) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public double dialogGetDouble(String title, String question, String hintText) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public boolean recurSave(SteppDB steppdb) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public long getBackID(int levels) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public BackStepp getBackRef(int levels) {
//		// TODO Auto-generated method stub
//		return null;
//	}


//	@Override
//	public void switchFrags() {
//		// TODO Auto-generated method stub
//		
//	}


//	@Override
//	public SteppFragmentManager getSteppFragmentManager() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
