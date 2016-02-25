package com.wipro.contactpro.activity;

import java.util.ArrayList;

import com.wipro.contactpro.R;
import com.wipro.contactpro.domain.ContactDetails;
import com.wipro.contactpro.sqlite.DatabaseHandler;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.OperationApplicationException;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class AddContactActivity extends Activity {

	DatabaseHandler db = new DatabaseHandler(this);
	
	ArrayList<ContactDetails> contL = new ArrayList<ContactDetails>();
	
	ImageView btnAdd;
	ImageView iconBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_contact);
		
		btnAdd = (ImageView) findViewById(R.id.icon_add);
		iconBack = (ImageView) findViewById(R.id.icon_back);
		
		btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				// Getting reference to Name EditText 
				EditText etName = (EditText) findViewById(R.id.et_name);
				
				// Getting reference to Mobile EditText 
				EditText etMobile = (EditText) findViewById(R.id.et_mobile_phone);
				
				// Getting reference to HomePhone EditText 
				EditText etHomePhone = (EditText) findViewById(R.id.et_home_phone);
				
				// Getting reference to WorkEmail EditText 
				EditText etWorkEmail = (EditText) findViewById(R.id.et_work_email);	
				
				// Getting reference to HomeEmail EditText 
				EditText etHomeEmail = (EditText) findViewById(R.id.et_home_email);			
				
				// add contact in the db using sqlite
				/**
		         * CRUD Operations
		         * */
		        // Inserting Contacts
		        Log.d("Insert: ", "Inserting ..");
		        db.addContact(new ContactDetails(etName.getText().toString(), etMobile.getText().toString(), etHomePhone.getText().toString(),
		        		etHomeEmail.getText().toString(),etWorkEmail.getText().toString())); 
				
				ContactDetails contD = new ContactDetails();
				
				contD.setName(etName.getText().toString());
				contD.setMobileNumber(etMobile.getText().toString());
				contD.setPhoneNumber(etHomePhone.getText().toString());
				contD.setHomeEmail(etHomeEmail.getText().toString());
				contD.setWorkEmail(etWorkEmail.getText().toString());
				 
				contL.add(contD);
				
				ArrayList<ContentProviderOperation> ops =
				          new ArrayList<ContentProviderOperation>();
				
				int rawContactID = ops.size();
				
				// Adding insert operation to operations list 
				// to insert a new raw contact in the table ContactsContract.RawContacts
				ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
						.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
						.withValue(RawContacts.ACCOUNT_NAME, null)
						.build());

				// Adding insert operation to operations list
				// to insert display name in the table ContactsContract.Data
				ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
						.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
						.withValue(ContactsContract.Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
						.withValue(StructuredName.DISPLAY_NAME, etName.getText().toString())
						.build());
				
				// Adding insert operation to operations list
				// to insert Mobile Number in the table ContactsContract.Data
				ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
						.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
						.withValue(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
						.withValue(Phone.NUMBER, etMobile.getText().toString())
						.withValue(Phone.TYPE, CommonDataKinds.Phone.TYPE_MOBILE)
						.build());
				
				// Adding insert operation to operations list
				// to  insert Home Phone Number in the table ContactsContract.Data
				ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
						.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
						.withValue(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
						.withValue(Phone.NUMBER, etHomePhone.getText().toString())
						.withValue(Phone.TYPE, Phone.TYPE_HOME)
						.build());

				// Adding insert operation to operations list
				// to insert Home Email in the table ContactsContract.Data
				ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
						.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
						.withValue(ContactsContract.Data.MIMETYPE, Email.CONTENT_ITEM_TYPE)
						.withValue(Email.ADDRESS, etHomeEmail.getText().toString())
						.withValue(Email.TYPE, Email.TYPE_HOME)
						.build());
				
				// Adding insert operation to operations list
				// to insert Work Email in the table ContactsContract.Data
				ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
						.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
						.withValue(ContactsContract.Data.MIMETYPE, Email.CONTENT_ITEM_TYPE)
						.withValue(Email.ADDRESS, etWorkEmail.getText().toString())
						.withValue(Email.TYPE, Email.TYPE_WORK)
						.build());				

				try{
				// Executing all the insert operations as a single database transaction
					getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
					Toast.makeText(getBaseContext(), "Contact is successfully added", Toast.LENGTH_SHORT).show();
					finish();
				}catch (RemoteException e) {					
					e.printStackTrace();
					Toast.makeText(getBaseContext(), "Contact Addition failed", Toast.LENGTH_SHORT).show();
				}catch (OperationApplicationException e) {
					Toast.makeText(getBaseContext(), "Contact Addition failed", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
				finally{
					finish();
				}
				
			}
		});
		
		iconBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				finish();
			}
		});
	
	}

}
