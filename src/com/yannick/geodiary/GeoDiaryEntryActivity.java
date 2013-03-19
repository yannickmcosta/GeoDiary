package com.yannick.geodiary;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.yannick.geodiary.data.DiaryEntry;

public class GeoDiaryEntryActivity extends Activity {

	private static final String TAG = "GeoDiaryEntryActivity";
	
	private TextView mDateDisplay;
	private Button mSaveButton;
	private TextView mDescription;
	private TextView mTitle;
	private TextView mDiaryDate;
	@SuppressWarnings("unused")
	private ImageButton mReceiptButton;
	
	/** Fields for managing the date control */
	private int mYear;
	private int mMonth;
	private int mDay;	

	private Cursor mCursor;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.i(TAG, "onCreate");
		setContentView(R.layout.diary_form);
		
		// Set up references to the view items.
		mDateDisplay = (TextView) findViewById(R.id.expEdt_et_date);
		mSaveButton = (Button) findViewById(R.id.expEdt_bt_save);
		mDescription = (EditText) findViewById(R.id.expEdt_et_description);
		mTitle = (TextView) findViewById(R.id.expEdt_et_title);
		mDiaryDate = (TextView) findViewById(R.id.expEdt_et_date);
		mReceiptButton = (ImageButton) findViewById(R.id.expEdt_ib_receipt);

		// TODO 21 call local helper method configureCursorForActivity()
		// You will complete code in this method in a moment
		
		
		mSaveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Do something to save the data
				Intent resultIntent = new Intent();
				resultIntent.putExtra(Constants.GEODIARY_DESCRIPTION, mDescription.getText().toString());

				setResult(Activity.RESULT_OK, resultIntent); 
				finish();
			}
		});	  	
		
	}

	private void configureCursorForActivity() {
		// TODO 31  Get the launching intent from the Activity
		//Intent launchingIntent

/* TODO 32 Remove the block comment
  		
		try{
			// TODO 33 Get the ID of the expense to be edited by parsing the Content Provider URI
			// The try catch block catches those cases where there is no ID yet
			 
			mExpenseId = ContentUris.parseId(launchingIntent.getData());
		} catch (NumberFormatException e){
			mExpenseId = -1;
		}	

		if (-1 == mExpenseId) {
			// As the expenseItem was set to -1, create a new expense item 
			// TODO 34 insert a new (empty) expense item into the Content Provider 
			// (Hint: the Uri for the ContentProvider is in the Intent)
			Uri dataUri = getContentResolver()._____(__________.______(), null);

			// TODO 35 Set the data of the launchingIntent to be the URI of the new expense item 
			launchingIntent.________;			
			
			// TODO 36  Get the id of the new expense from the uri returned
			mExpenseId = ________.________(dataUri);
		}
*/
		
		
		// Load a cursor holding the Expense we are working on
		// TODO 36 complete the code to create a new managed query for the expense on which we are working

/*		mCursor = managedQuery(launchingIntent.______, // Get the URI from the Intent for the Activity
				Expense.ExpenseItem.FULL_PROJECTION, // Projection containing all Expense items
				______, // No "where" clause selection criteria.
				______, // No "where" clause selection values.
				Expense.ExpenseItem.DEFAULT_SORT_ORDER // Use the default sort order as there is only one item!
		);
*/		
	}
	
	private void loadDiaryDataFromCursor() {
		/*
		 * mCursor is initialized, since onCreate() always precedes onResume for
		 * any running process. This tests that it's not null, since it should
		 * always contain data.
		 */		
		if (mCursor != null) {
			// Re-query in case something changed while paused
			// TODO 51 remove the comments 
			// and then requery the cursor by moving the cursor to the first row
	
//			if (mCursor.moveTo______()) {
//				// populate the dialog fields
//
//				// TODO Note how the view elements are populated from the cursor content
//				mDescription.setText(mCursor.getString(0));
//				mAmount.setText(mCursor.getString(1));
//				mExpenseDate.setText(mCursor.getLong(2) + "");	// Coerce the long to a String with  + ""
//			}
		}
	}		
	
    @Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG, "onPause");
		/*
		 * Tests to see that the query operation didn't fail (see onCreate()).
		 * The Cursor object will exist, even if no records were returned,
		 * unless the query failed because of some exception or error.
		 */
		if (mCursor != null) {
			// TODO 61 call the local helper method saveExpenseItem()

		}	
	}



	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume");
		// TODO 41 Call local helper method loadExpenseDataFromCursor() 
	
	}


	
	private void saveDiaryItem() {
		
		ContentValues values = new ContentValues();

		// Adds map entries for the user-controlled fields in the map
		// Note that we are not checking the field content here but the provider
		// code does do the checking
		values.put(DiaryEntry.DiaryItem.COLUMN_NAME_DIARY_DATE, mDiaryDate
				.getText().toString());
		values.put(DiaryEntry.DiaryItem.COLUMN_NAME_AMOUNT, mTitle.getText()
				.toString());
		
		// TODO 71 create a new element in the values object with a key of Expense.ExpenseItem.COLUMN_NAME_DESCRIPTION
		// and the value from the mDescription view element (see the lines above for a template)


		// Provider URL sent with the original intent
		// TODO 72 Call the update method on the ContentResolver to update the data with the
		// modified form elements (use null as the where clause parameters)
		// Important: use the provider URL stored in the Intent as it represents the 
		// expense item associated with this Activity instance		
		//getContentResolver().
	}	
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, "onStart");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, "onStop");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "onDestroy");
	}		
	
	/** 
	 * @see android.app.Activity#onCreateDialog(int)
	 * Method called by Android to allow the creation of a dialog
	 * Use this to launch the Date dialog
	 */
	@Override	
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case Constants.DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}	
	
	/**
	 *  The callback received when the user "sets" the date in the dialog
	 */
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDateDisplay();
		}
	};	
	
	/** 
	 * Updates the mDateDisplay TextView with a formatted data based on the values of
	 * year, month and day member fields
	 */
	private void updateDateDisplay() {
		mDateDisplay.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("-").append(mDay).append("-")
				.append(mYear).append(" "));
	}
	
	/**
	 * Converts the year, month and day member fields to a time in milli-seconds
	 * @return
	 */
	private long getDateInMillisFromDateFields() {
		Calendar c= Calendar.getInstance();
		c.set(mYear, mMonth, mDay);
		long date = c.getTimeInMillis();
		return date;
	}	

	/**
	 * Populates the mYear, mMonth and mDay fields from the supplied time in millis
	 * @param time
	 */
	private void getDateFieldsFromMillis(long time) {
		final Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
	}		

	// add a click listener to the date entry field
	private class DateClickListener implements View.OnClickListener {
		public void onClick(View v) {
			showDialog(Constants.DATE_DIALOG_ID);
		}
	};	
	
}
