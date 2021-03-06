package com.sfx.shadowstep;


import android.database.Cursor;
import android.database.CursorWrapper;

/** 
 * A {@link CursorWrapper} implementation that allows a {@link Cursor} 
 * without a field named "_id" to be used with various Android {@link 
ListView} 
 * classes that expect a column named "_id".  This is done by 
specifying an 
 * alias field name to be used in place of "_id". 
 * 
 * Modified from code written by j_...@users.sourceforge.net 
 */ 
public class SteppCursorWrapper extends CursorWrapper { 

        public SteppCursorWrapper(Cursor cursor) {	//required, but doesn't function
		super(cursor);
	}


		private int idColumnIndex; 

        /** 
         * Create a NoIdCursorWrapper using the alias column index. 
         * @param c the cursor to wrap 
         * @param idColumnIndex the column index to use as the _id column 
alias 
         */ 
        public SteppCursorWrapper(Cursor c, int idColumnIndex) { 
                super(c); 
                this.idColumnIndex = idColumnIndex; 
        } 

        /** 
         * Create a NoIdCursorWrapper using the alias column name. 
         * @param c the cursor to wrap 
         * @param idColumnName the column name to use as the _id column alias 
         */ 
        public SteppCursorWrapper(Cursor c, String idColumnName) { 
                super(c); 
                idColumnIndex = c.getColumnIndex(idColumnName); 
        } 

        @Override 
        public int getColumnIndex(String columnName) { 
                int index = super.getColumnIndex(columnName); 
        if (index < 0 && "_id".equals(columnName)) { 
                        index = idColumnIndex; 
                } 
                return index; 
        }; 


        @Override 
        public int getColumnIndexOrThrow(String columnName) throws 
IllegalArgumentException { 
                int index = getColumnIndex(columnName); 
                if (index>=0) { 
                        return index; 
                } 
                // let the AbstractCursor generate the exception 
                return super.getColumnIndexOrThrow(columnName); 
        }; 
} 
