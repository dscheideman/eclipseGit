/**
 * 
 */
package com.sfx.shadowstep;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;

/**
 * @author David
 *
 */
public class ControlFragment extends SteppFrag {

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
		super.onCreateView(inflater, container, savedInstanceState);
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

}
