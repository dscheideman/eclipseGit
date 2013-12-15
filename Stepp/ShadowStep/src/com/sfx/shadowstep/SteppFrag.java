package com.sfx.shadowstep;

import static com.sfx.shadowstep.SteppConstants.STEPP_TRIP_COL_NAME;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public abstract class SteppFrag extends Fragment implements BackStepp {

	BackStepp parent;
	SteppDB sdb;
	Activity steppActivity;
	protected int selectedListItemRow;
	private Cursor c;
	protected int selectedListItemID;
	ViewGroup mContainer;// could be important
	//layout components
//	EditText editText_NewName;
//	Button	button_load;
//	Button	button_new;
//	Spinner	spinner_savedTrips;
//	int[] listViewMap = {R.id.list_title, R.id.list_info1, R.id.list_info2, R.id.list_info3};
//	String[] listColRef = {STEPP_TRIP_COL_NAME[0],STEPP_TRIP_COL_NAME[4], STEPP_TRIP_COL_NAME[2], STEPP_TRIP_COL_NAME[3]};

	
	
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
	//required methods for lifecycle of fragment
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
	return super.onCreateView(inflater, container, savedInstanceState);
	}
//	@Override
//	public abstract void onStart();
//	@Override
//	public abstract void onResume();
//	@Override
//	public abstract void onPause();
//	@Override
//	public abstract void onStop();
	
	
	
	public SteppFrag() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Activity findTopActivity() {
		
		return parent.findTopActivity();
	}

	@Override
	public SteppDB findTopDBHelper() {
		
		return parent.findTopDBHelper();
	}

	@Override
	public String dialogGetString(String title, String question, String hintText) {
		return parent.dialogGetString(title, question, hintText);
	}

	@Override
	public double dialogGetDouble(String title, String question, String hintText) {
		return parent.dialogGetDouble(title, question, hintText);
	}

	@Override
	public boolean recurSave(SteppDB steppdb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getBackID(int levels) {
		if(levels <= 0) return -1;	//fragments don't have SQL ID tags
		levels--;
		return parent.getBackID(levels);
	}

	@Override
	public BackStepp getBackRef(int levels) {
		if(levels <= 0) return this;
		levels--;
		return parent.getBackRef(levels);
	}

	@Override
	public Fragment switchFrags(Fragment fragment, String tag) {
		return parent.switchFrags(fragment, tag);
	}

	@Override
	public ArrayList<Fragment> getFragmentArray() {
		return parent.getFragmentArray();
	}
	
//	public void proxyOnResume() {
//		super.onResume();
//		
//	}
	

}
