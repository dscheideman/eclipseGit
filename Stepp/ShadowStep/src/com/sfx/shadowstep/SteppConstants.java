/**
 * 
 */
package com.sfx.shadowstep;

/**
 * @author David
 *This is a class solely to hold constant values as used in com.sfx.shadowstep 
 *so they may be easily referenced and changed on a global level.
 *	!!!THIS CLASS CANNOT BE INSTANTIATED!!! or maybe it can
 */
public final class SteppConstants {
	//DATABASE CONSTANTS
	public static final String 		STEPP_DB_NAME 		= 	"sfx_stepp_db";
	public static final String		STEPP_RUN_TABLE 	= 	"table_run";
	public static final String		STEPP_ROUTE_TABLE 	= 	"table_route";
	public static final String		STEPP_TRIP_TABLE 	=	"table_trip";
	public static final String 		STEPP_LOC_TABLE 	= 	"table_location";
	public static final String 		STEPP_POINT_TABLE 	= 	"table_point";
	public static final int 		STEPP_DB_VERSION 	= 	1;
	
	//DATABASE COLUMNS
	//FIXME>>>>> be sure to verify all columns are consistant with queries.  Example: run cols no longer need to include name fields or filepath
	public static final String[]	STEPP_RUN_COL_NAME	=	{"run_id", 						"route_id", 	"trip_id", 		"td_auto_start",	"td_auto_end", 	"time_paused", 	"td_man_start", "td_man_end", "_id"};
	public static final String[]	STEPP_RUN_COL_TYPE	=	{"INTEGER PRIMARY KEY", 		"INT", 			"INT", 			"REAL",				"REAL", 		"REAL", 		"REAL", 		"REAL", "INTEGER"};
	public static final String[]	STEPP_ROUTE_COL_NAME=	{"route_id", 					"trip_id", 		"route_name", 	"avg_route_time", 	"td_rt_created", "_id"};
	public static final String[]	STEPP_ROUTE_COL_TYPE=	{"INTEGER PRIMARY KEY", 		"INT", 			"TEXT", 		"REAL", 			"REAL", "INTEGER"};
	public static final String[]	STEPP_TRIP_COL_NAME	=	{"trip_id", 					"trip_name", 	"start_loc_id", "end_loc_id", 		"td_tp_created", "_id"};
	public static final String[]	STEPP_TRIP_COL_TYPE	=	{"INTEGER PRIMARY KEY", 		"TEXT", 		"REAL", 		"REAL", 			"REAL", "INTEGER"};
	public static final String[]	STEPP_LOC_COL_NAME 	=	{"loc_id", 						"loc_name", 	"loc_lat", 		"loc_lon", 			"td_loc_created", "_id"};
	public static final String[]	STEPP_LOC_COL_TYPE	=	{"INTEGER PRIMARY KEY", 		"TEXT", 		"REAL", 		"REAL", 			"REAL", "INTEGER"};
	public static final String[] 	STEPP_POINT_COL_NAME = 	{"point_id", 					"run_id"	,	"altitude"	,	"lattitude", 		"longitude",		"time_created", 	"mutant",		"flags", "_id"};
	public static final String[] 	STEPP_POINT_COL_TYPE = 	{"INTEGER PRIMARY KEY",  		"REAL",			"REAL",			"REAL",				"REAL",				"REAL",				"INTEGER",		"TEXT", "INTEGER"};//mutant integer is boolean 0=false 1=true
	public static final String		STEPP_FILE_PATH 	=	""; //Deprecated -- file path for waypoint file
	
	//DIALOG & INQUIRY
	
	public static final String		DIALOG_CANCEL		=	"@@@USERCANCELLED@@@";
	public static final double		FLOAT_CANCEL		= 	-4200.12;
	
	public static final String		DO_NOT_SAVE			=	"This Object is not intended for permanent storage";
	public static final int[]		STEPP_SCA_TO_ARGUMENT = {};
	
	//FragmentTags
	
	public static final String		F_TAG_SPLASH		=	"SPLASHFRAG";
	public static final String		F_TAG_MAP			=	"MAPFRAG";
	public static final String		F_TAG_CONTROL		=	"CONTROLFRAG";
	public static final String		F_TAG_SEL_TRIP		=	"SELECTTRIPFRAG";
	public static final String		F_TAG_NEW_TRIP		=	"NEWTRIPFRAG";
	public static final String		F_TAG_EDIT_TRIP		=	"EDITTRIPFRAG";
	public static final String		F_TAG_SEL_LOC		=	"SELECTLOCFRAG";
	public static final String		F_TAG_NEW_LOC		=	"NEWLOCFRAG";
	public static final String		F_TAG_EDIT_LOC		=	"EDITLOCFRAG";
	public static final String		F_TAG_SEL_ROUTE		=	"SELECTROUTEFRAG";
	public static final String		F_TAG_NEW_ROUTE		=	"NEWROUTEFRAG";
	public static final String		F_TAG_EDIT_ROUTE	=	"EDITROUTEFRAG";
	public static final String		F_TAG_EDIT_RUN		=	"EDITRUNFRAG";
	
	public SteppConstants(){
//		throw new AssertionError();	//prevents native instantiation
	}
}
