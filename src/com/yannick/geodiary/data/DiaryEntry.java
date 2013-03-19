package com.yannick.geodiary.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DiaryEntry implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String AUTHORITY = "com.yannick.geodiary.Expenses";	// How to explain?
	
	/** Key properties for the Expense object. Note they are public to avoid the overhead of virtual method invocation
	 * (Android docs say this is up to 7 times faster!)
	 */
	public String description;
	public double amount;
	public long diaryDate;	

	/**
	 * Create an Expenses instance from a ContentValues object
	 * @param values
	 */
	public DiaryEntry(ContentValues values){
		description = values.getAsString(DiaryEntry.DiaryItem.COLUMN_NAME_DESCRIPTION );
		amount = values.getAsDouble(DiaryEntry.DiaryItem.COLUMN_NAME_AMOUNT);
		diaryDate = values.getAsLong(DiaryEntry.DiaryItem.COLUMN_NAME_DIARY_DATE);
	}
	
	public DiaryEntry(String description, double amount, long date ){
		this.description = description;
		this.amount = amount;
		diaryDate = date;

	}	
	
    /*
     * Returns a ContentValues instance (a map) for this expenseInfo instance. This is useful for
     * inserting a expenseInfo into a database.
     */
    public ContentValues getContentValues() {
        // Gets a new ContentValues object
        ContentValues v = new ContentValues();

        // Adds map entries for the user-controlled fields in the map
        v.put(DiaryEntry.DiaryItem.COLUMN_NAME_DESCRIPTION, description);
        v.put(DiaryEntry.DiaryItem.COLUMN_NAME_AMOUNT, amount);
        v.put(DiaryEntry.DiaryItem.COLUMN_NAME_DIARY_DATE, diaryDate);
        return v;

    }    	
	
	/**
	 * Constant definition to define the mapping of an Expense to the underlying database
	 * Also provides constants to help define the Content Provider
	 * @author Development Team
	 *
	 */
	public static final class DiaryItem implements BaseColumns {
		
        // This class cannot be instantiated
        private DiaryItem() {}

        /**
         * The table name offered by this provider
         */
        public static final String TABLE_NAME = "diary";

        /*
         * URI definitions
         */

        /**
         * The scheme part for this provider's URI
         */
        private static final String SCHEME = "content://";

        /**
         * Path parts for the URIs
         */

        /**
         * Path part for the expenses URI
         */
        private static final String PATH_DIARY = "/diary";

        /**
         * Path part for the expense ID URI
         */
        private static final String PATH_DIARY_ID = "/diary/";

        /**
         * 0-relative position of a expense item ID segment in the path part of a expense item ID URI
         */
        public static final int DIARY_ID_PATH_POSITION = 1;


        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI =  Uri.parse(SCHEME + AUTHORITY + PATH_DIARY);
        
        /**
         * The content URI base for a single expense item. Callers must
         * append a numeric expense id to this Uri to retrieve an expense item
         */
        public static final Uri CONTENT_ID_URI_BASE
            = Uri.parse(SCHEME + AUTHORITY + PATH_DIARY_ID);        
        
        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = DiaryEntry.DiaryItem.COLUMN_NAME_DIARY_DATE + " ASC";        
        
        
        /*
         * MIME type definitions
         */

        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of expenses.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.yannick.geodiary.expense";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single expense item
         * 
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.yannick.geodiary.expense";
        
        /*
         * Column definitions
         */

        /**
         * Column name for the description of the expense item
         * <P>Type: TEXT</P>
         */
        public static final String COLUMN_NAME_DESCRIPTION = "description";

        /**
         * Column name of the expense item amount
         * <P>Type: REAL</P>
         */
        public static final String COLUMN_NAME_AMOUNT = "amount";
        
        /**
         * Column name for the creation timestamp
         * <P>Type: INTEGER (long from System.curentTimeMillis())</P>
         */
        public static final String COLUMN_NAME_DIARY_DATE = "incurred";       
        
        
        public static final String COLUMN_NAME_ID = BaseColumns._ID;
        
        /** Projection holding all the columns required to populate and Expense item */
        public static final String[] FULL_PROJECTION = {
                COLUMN_NAME_DESCRIPTION,
                COLUMN_NAME_AMOUNT,
                COLUMN_NAME_DIARY_DATE
            };        
        
        public static final String[] LIST_PROJECTION =
            new String[] {
                DiaryEntry.DiaryItem._ID,
                DiaryEntry.DiaryItem.COLUMN_NAME_DESCRIPTION,
                DiaryEntry.DiaryItem.COLUMN_NAME_AMOUNT
        };            

	}

	public static final class Helper {
		/**
		 * Converts a cursor to an array of Expense 
		 * Note that this method is "mean and lean" with little error checking.
		 * It assumes that the projection used is ExpenseItem.FULL_PROJECTION
		 * @param cursor A cursor loaded with Expense data
		 * @return populated array of Expense
		 */
		public static final DiaryEntry[] getDiaryFromCursor(Cursor cursor){
			DiaryEntry[] diary = null;
			int rows = cursor.getCount();
			if(rows > 0){
				diary = new DiaryEntry[rows];
				int i=0;
				while(cursor.moveToNext()){
					diary[i++] = new DiaryEntry( cursor.getString(0), cursor.getDouble(1), cursor.getLong(2));
				}
			}
			return diary;
		}
		
		public static final JSONObject diaryToJSON(DiaryEntry e) throws JSONException{
			JSONObject jObj = new JSONObject();
			jObj.put("description", e.description);
			jObj.put("amount", e.amount);
			jObj.put("diaryDate", e.diaryDate);
			return jObj;
		}
		
		public static final JSONArray diaryArrayToJSON(DiaryEntry[] diary)  throws JSONException{
			JSONArray jArray = new JSONArray();
			System.out.println("Converting  " + diary.length + " expenses to JSON");
			for(DiaryEntry e: diary){
				jArray.put(diaryToJSON(e));
			}
			return jArray;
		}		
	}
	
	
}
