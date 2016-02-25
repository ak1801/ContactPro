package com.wipro.contactpro.activity;

import com.wipro.contactpro.R;
import com.wipro.contactpro.domain.ContactDetails;
import com.wipro.contactpro.sqlite.DatabaseHandler;

import android.app.Activity;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.SQLException;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditContactDetails extends Activity{

	ImageView back,save;
	EditText et1,et2;
	//Intent intent = getIntent();
	
	DatabaseHandler db = new DatabaseHandler(this);
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.contact_details_edit);
		
		back = (ImageView) findViewById(R.id.back);
		save = (ImageView) findViewById(R.id.save);
		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
		
		Intent intent = getIntent();
	    
	    final int position = intent.getIntExtra("position", 0);
	    final String name = intent.getStringExtra("name");
	    final String phone_no = intent.getStringExtra("phone_no");

	    System.out.println("position : -----"+position+" ------");
	    System.out.println("name : -----"+name+" ------");
	    System.out.println("phone no : -----"+phone_no+" ------");
	    
	    
		et1.setText(name);

		et2.setText(phone_no);
	    
	    back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			finish();	
			}
		});
	    
	    save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				ContactDetails updateContact = new ContactDetails();
				Intent intent1 = getIntent();
				
				String new_name=et1.getText().toString();
				String new_phone_no=et2.getText().toString();
				
			    updateContact.setID(position);
			    updateContact.setName(new_name);
			    updateContact.setPhoneNumber(new_phone_no);
			    
			    try{
					// Executing all the insert operations as a single database transaction
			    		db.updateContact(updateContact);
			    		
			    		intent1.putExtra("newName", new_name);
			    		intent1.putExtra("newNumber", new_phone_no);
			    		setResult(RESULT_OK, intent1);
			    		
						Toast.makeText(getBaseContext(), "Contact updated successfully", Toast.LENGTH_SHORT).show();
						finish();
					}catch (SQLException e) {					
						e.printStackTrace();
						Toast.makeText(getBaseContext(), "Contact Updation failed", Toast.LENGTH_SHORT).show();
					}
			    	catch (Exception e) {					
			    		e.printStackTrace();
			    		Toast.makeText(getBaseContext(), "Contact Updation failed", Toast.LENGTH_SHORT).show();
			    	}
					finally{
						finish();
					}
			}
		});
	    
	}

}
