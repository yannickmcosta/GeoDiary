package com.yannick.geodiary;

public class Constants {
	
	private Constants(){}
	
	public static final String GEODIARY_ARRAY = "geodiaryArrayData";
	public static final String GEODIARY_ENTRY = "geodiaryEntry";
	
	public static final String GEODIARY_EXTRA_FILENAME = "com.yannick.geodiary.filename";
	/** If true,causes the service to save to a local file */
	public static final String GEODIARY_EXTRA_SAVE_TO_FILE = "save_to_file";

	
	/** Preferences */
	public static final String PREF_SERVER_PUT_URL = "sync_server_put_url";
	public static final String PREF_SERVER_GET_URL = "sync_server_get_url";
	public static final String GEODIARY_ID = "geodiary_id";
	public static final long GEODIARY_ITEM_UNDEFINED = -1;
	
	/** keys for Intent extras for the simple approach taken in exercise 4.2 */
	public static final String GEODIARY_DESCRIPTION = "GEODIARY_description";
	public static final String GEODIARY_AMOUNT = "GEODIARY_amount";
	public static final String GEODIARY_DATE = "GEODIARY_date";
	
	
	public static final int REQUEST_CODE_EDIT = 1;
	protected static final int DATE_DIALOG_ID = 100;

	
}
