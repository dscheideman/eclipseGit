package com.sfx.shadowstep;

import android.text.format.Time;
import android.widget.TextView;

public class ShadowTimer {

	private Time time1;
//	private Object tStart;
	private Time time2;
	@SuppressWarnings("unused")
	private TextView outView;// = new TextView(null);//initialized to prevent null pointer exceptions
	private boolean hasViewSet = false;
	
	
	public ShadowTimer(){
		
	}
	
	public boolean isHasViewSet() {
		return hasViewSet;
	}

	public void setHasViewSet(boolean hasViewSet) {
		this.hasViewSet = hasViewSet;
	}

	public void setCallBack(){//TODO use to set a continuous callback method to report elapsed time while timing
		//possibly pass a reference to a TextView object that can be updated continuously.
	}
	
	public void setTextView(TextView tv){
		outView = tv;
		setHasViewSet(true);
	}
	
	
	public void startTiming() {
		time1.setToNow();
//		tStart.setText(time1.format("%H:%M:%S"));
		//TODO start thread  which continues timer...
	}
	
	public void stopTiming(){
		time2.setToNow();
//		tStop.setText(time2.format("%H:%M:%S"));
//		tOther.setText(findDifference(time1, time2));
		//TODO stop continuous thread
	}
	
	public String getElapsed(){
		return (String) findDifference(time1, time2);
	}

	public CharSequence findDifference(Time time12, Time time22) {
		Time holder;
		int hour;
		int min;
		int sec;
		if(Time.compare(time12, time22)>0){
			holder = time12;
			time12 = time22;
			time22 = holder;
			holder = new Time();
		}
		hour = (time22.hour - time12.hour);
		min = (time22.minute - time12.minute);
		sec = (time22.second - time12.second);
		if (sec < 0) {//correct for placeshift
			min -= 1;
			sec += 60;
		}
		if (min<0){
			hour -= 1;
			min += 60;
		}
		return String.format("%02d", hour) + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec);
	}

	public Time setToNow() {
		// TODO Auto-generated method stub
		return null;
	}

	public void unPause() {
		// TODO Auto-generated method stub
		
	}

	public void pause() {
		// TODO Auto-generated method stub
		
	}
}
