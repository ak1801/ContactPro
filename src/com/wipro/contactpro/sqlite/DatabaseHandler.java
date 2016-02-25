package com.wipro.contactpro.sqlite;

import java.util.ArrayList;
import java.util.List;

import com.wipro.contactpro.domain.ContactDetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
		 
	// Database fields
		  private SQLiteDatabase database;

		  public void open() throws SQLException {
		    database = this.getWritableDatabase();
		  }

		  public void close() {
		    this.close();
		  }
		  
	// All Static variables
	
	// Database Version
	    private static final int DATABASE_VERSION = 1;
	    
	    // Database Name
	    private static final String DATABASE_NAME = "contactsPro.db";
	 
	    // Contacts table name
	    public static final String TABLE_CONTACTS = "contacts";
	 
	    // Contacts Table Columns names
	    public static final String KEY_ID = "id";
	    public static final String KEY_NAME = "name";
	    public static final String KEY_MOB_NO = "mobile_number";
	    public static final String KEY_PH_NO = "phone_number";
	    public static final String KEY_EMAIL_WORK = "email_work";
	    public static final String KEY_EMAIL_HOME = "email_home";
	 
	    public DatabaseHandler(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }

		@Override
		public void onCreate(SQLiteDatabase db) {
			/*String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
	                + KEY_ID + " INTEGER PRIMARY KEY autoincrement," + KEY_NAME + " TEXT,"
	                + KEY_PH_NO + " TEXT"
	                //+ KEY_MOB_NO + "TEXT," + KEY_EMAIL_WORK + "TEXT," + KEY_EMAIL_HOME + "TEXT"
	                + ")";
			
	        db.execSQL(CREATE_CONTACTS_TABLE);*/
	        
	        
	        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
	                + KEY_ID + " INTEGER PRIMARY KEY," 
	        		+ KEY_NAME + " TEXT,"
	                + KEY_PH_NO + " TEXT" 
	               // + KEY_MOB_NO + "TEXT," 
	               // + KEY_EMAIL_WORK + "TEXT" 
	              //  + KEY_EMAIL_HOME + "TEXT"
	                + ")";
	        db.execSQL(CREATE_CONTACTS_TABLE);
			
		}
		
		// Adding new contact
		public void addContact(ContactDetails contact) {
		    SQLiteDatabase db = this.getWritableDatabase();
		 
		    ContentValues values = new ContentValues();
		    values.put(KEY_NAME, contact.getName()); // Contact Name
		    values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone Number
		    //values.put(KEY_MOB_NO, contact.getMobileNumber()); // Contact Mobile Number
		    //values.put(KEY_EMAIL_WORK, contact.getWorkEmail()); // Contact Work Email
		    //values.put(KEY_EMAIL_HOME, contact.getHomeEmail()); // Contact Home Email
		 
		    // Inserting Row
		    db.insert(TABLE_CONTACTS, null, values);
		    db.close(); // Closing database connection
		}
		
		// Getting All Contacts
	    public List<ContactDetails> getAllContacts() {
	        List<ContactDetails> contactList = new ArrayList<ContactDetails>();
	        // Select All Query
	        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
	 
	        SQLiteDatabase db = this.getWritableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	 
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	            	ContactDetails contact = new ContactDetails();
	                contact.setID(Integer.parseInt(cursor.getString(0)));
	                contact.setName(cursor.getString(1));
	                contact.setPhoneNumber(cursor.getString(2));
	                // Adding contact to list
	                contactList.add(contact);
	            } while (cursor.moveToNext());
	        }
	 
	        // return contact list
	        return contactList;
	    }

	 // Updating single contact
	    public int updateContact(ContactDetails contact) {
	        SQLiteDatabase db = this.getWritableDatabase();
	 
	        ContentValues values = new ContentValues();
	        values.put(KEY_NAME, contact.getName());
	        values.put(KEY_PH_NO, contact.getPhoneNumber());
	        //values.put(KEY_MOB_NO, contact.getMobileNumber());
	        //values.put(KEY_EMAIL_WORK, contact.getWorkEmail());
	        //values.put(KEY_EMAIL_HOME, contact.getHomeEmail());
	 
	        // updating row
	        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
	                new String[] { String.valueOf(contact.getID()) });
	    }
	 
	    // Deleting single contact
	    public void deleteContact(ContactDetails contact) {
	        SQLiteDatabase db = this.getWritableDatabase();
	        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
	                new String[] { String.valueOf(contact.getID()) });
	        db.close();
	    }
	 
	 
	    // Getting contacts Count
	    public int getContactsCount() {
	        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS; 
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(countQuery, null);
	        cursor.close();
	 
	        // return count
	        return cursor.getCount();
	    }
	    
	    public ContactDetails getContactById(int contactId){
			
	    	//List<ContactDetails> contactDetails = new ArrayList<ContactDetails>();

	    	String query = "Select * FROM " + TABLE_CONTACTS + " WHERE " + KEY_ID + " =  \"" + contactId + "\"";
	    	
	    	SQLiteDatabase db = this.getWritableDatabase();
	    	
	    	Cursor cursor = db.rawQuery(query, null);
	    	
	    	ContactDetails curr_contact = new ContactDetails();
	    	
	    	if (cursor.moveToFirst()) {
	    		cursor.moveToFirst();
	    		curr_contact.setID(Integer.parseInt(cursor.getString(0)));
	    		curr_contact.setName(cursor.getString(1));
	    		curr_contact.setPhoneNumber(cursor.getString(2));
	    		cursor.close();
	    	} else {
	    		curr_contact = null;
	    	}
	            db.close();
	    	return curr_contact;

	    	
	    	/*String getcontactQuery = "SELECT * FROM " + TABLE_CONTACTS;// + " where KEY_ID =" + contactId;
	    	
	    	return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
	                new String[] { String.valueOf(contact.getID()) });
	    	
	    	SQLiteDatabase db = this.getWritableDatabase();
	        Cursor cursor = db.rawQuery(getcontactQuery, null);
	        
	    	// looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	            	ContactDetails contact = new ContactDetails();
	                contact.setID(Integer.parseInt(cursor.getString(0)));
	                contact.setName(cursor.getString(1));
	                contact.setPhoneNumber(cursor.getString(2));
	                // Adding contact to list
	                contactDetails.add(contact);
	            } while (cursor.moveToNext());
	        }
	 
	        // return contact list
	        return contactDetails;
*/
	    }
	    
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(DatabaseHandler.class.getName(),
			        "Upgrading database from version " + oldVersion + " to "
			            + newVersion + ", which will destroy all old data");
			    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
			    onCreate(db);
			
		}

}
