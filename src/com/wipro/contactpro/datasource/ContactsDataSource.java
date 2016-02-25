package com.wipro.contactpro.datasource;

import java.util.ArrayList;
import java.util.List;

import com.wipro.contactpro.domain.ContactDetails;
import com.wipro.contactpro.sqlite.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ContactsDataSource {

/*	
	// Database fields
		  private SQLiteDatabase database;
		  private DatabaseHandler dbHelper;
		  private String[] allColumns = { DatabaseHandler.KEY_ID,
				  DatabaseHandler.KEY_NAME, DatabaseHandler.KEY_MOB_NO, DatabaseHandler.KEY_PH_NO,
				  DatabaseHandler.KEY_EMAIL_WORK, DatabaseHandler.KEY_EMAIL_HOME};
		  
		  public ContactsDataSource(Context context) {
			    dbHelper = new DatabaseHandler(context);
		  }

		  public void open() throws SQLException {
			    database = dbHelper.getWritableDatabase();
		  }
		
		  public void close() {
			    dbHelper.close();
		  }
		  
		  // Adding new contact
		  public ContactDetails addContact(ContactDetails contact) {
			  
			  ContentValues values = new ContentValues();
			    values.put(DatabaseHandler.KEY_NAME, contact.getName());
			    values.put(DatabaseHandler.KEY_MOB_NO, contact.getMobile());
			    values.put(DatabaseHandler.KEY_PH_NO, contact.getHomePhone());
			    values.put(DatabaseHandler.KEY_EMAIL_WORK, contact.getWorkEmail());
			    values.put(DatabaseHandler.KEY_EMAIL_HOME, contact.getHomeEmail());
			    
			    long insertId = database.insert(DatabaseHandler.TABLE_CONTACTS, null,
			        values);
			    
			  Cursor cursor = database.query(DatabaseHandler.TABLE_CONTACTS,
			        allColumns, DatabaseHandler.KEY_ID + " = " + insertId, null, null,
			        null, null, null);
			    cursor.moveToFirst();
			    ContactDetails newContact = cursorToContact(cursor);
			    cursor.close();
			    return newContact;
		  }
		   
		  public List<ContactDetails> getAllComments() {
			    List<ContactDetails> contacts = new ArrayList<ContactDetails>();

			    Cursor cursor = database.query(DatabaseHandler.TABLE_CONTACTS,
			        allColumns, null, null, null, null, null);

			    cursor.moveToFirst();
			    while (!cursor.isAfterLast()) {
			    	ContactDetails contact = cursorToContact(cursor);
			    	contacts.add(contact);
			        cursor.moveToNext();
			    }
			    // make sure to close the cursor
			    cursor.close();
			    return contacts;
		  }
		  
		  
		  private ContactDetails cursorToContact(Cursor cursor) {
			  	ContactDetails contact = new ContactDetails();
			  	//contact.setId(cursor.getLong(0));
			  	contact.setName(cursor.getString(1));
			  	//contact.setMobile(cursor.getString(2));
			  	//contact.setHomePhone(cursor.getString(3));
			  	//contact.setWorkEmail(cursor.getString(4));
			  	//contact.setHomeEmail(cursor.getString(5));
			    return contact;
			  }
*/		  
		  
}
