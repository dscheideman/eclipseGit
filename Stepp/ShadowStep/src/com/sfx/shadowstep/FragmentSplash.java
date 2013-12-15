package com.sfx.shadowstep;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import static com.sfx.shadowstep.SteppConstants.*;

@SuppressWarnings("unused")
public class SplashFrag extends SteppFrag {

	//view objects
	Button buttonNewRun;
	Button buttonQuickRun;
	Button buttonBrowse;
	Button buttonAssignQuickData;//???
	
	
	
	public SplashFrag() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onResume() {
		super.onResume();
		buttonNewRun = (Button) getActivity().findViewById(R.id.button_splash_new_run);
		buttonQuickRun = (Button) getActivity().findViewById(R.id.button_splash_quickrun);
		buttonNewRun.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(switchFrags(null, F_TAG_SEL_TRIP)==null)
					switchFrags(new SteppFragTrip(), F_TAG_SEL_TRIP);
				
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_splash,container,false);
		mContainer = container;
		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();// TODO Auto-generated method stub
		
	}

	@Override
	public void onPause() {
		super.onPause();// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop() {
		super.onStop();// TODO Auto-generated method stub
		
	}
	
	

}
