package com.wipro.contactpro.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.wipro.contactpro.R;
import com.wipro.contactpro.datasource.ContactsDataSource;
import com.wipro.contactpro.domain.*;
import com.wipro.contactpro.sqlite.DatabaseHandler;

import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ContactActivity extends ListActivity {

	DatabaseHandler db = new DatabaseHandler(this);
	
	EditText inputSearch=null;
	ArrayAdapter<String> adapter;
	ImageView icon_add;
	ListView lv1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {

    	System.out.println("------------------Inside onCreate----------------");
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        inputSearch = (EditText) findViewById(R.id.inputSearch);

        getAllContacts();
        
        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
				 // When user changed the Text
                ContactActivity.this.adapter.getFilter().filter(cs);
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				
			}
		});
        
        icon_add = (ImageView) findViewById(R.id.icon_add);
        
        icon_add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent addnewContact = new Intent(ContactActivity.this,
						AddContactActivity.class);
				ContactActivity.this.startActivity(addnewContact);
			}
		});

        lv1 = getListView();
        
        lv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				for (int i = 0; i < lv1.getChildCount(); i++) {
					//System.out.println(i);
				}
				//position=position-1;
				System.out.println("------position : " + position
						+ "-------");
				System.out.println("count: ---------" + lv1.getChildCount()
						+ "-------");
				
				ContactDetails curr_contact = db.getContactById(position+1);
				//for (ContactDetails cn : curr_contact) {
					
					String log = "Id: "+curr_contact.getID()+" ,Name: " + curr_contact.getName() + " ,Phone: " + curr_contact.getPhoneNumber() +
		            		" ,Mobile: " + curr_contact.getMobileNumber() + ",Work Mail: " + curr_contact.getWorkEmail() + ",Home Mail: " + curr_contact.getHomeEmail();
		                // Writing Contacts to log
		            Log.d("Name: ", log);
		            
		            Intent viewContact = new Intent(ContactActivity.this,
							ViewContactDetails.class);
					
		            viewContact.putExtra("position", position+1);
					viewContact.putExtra("name", curr_contact.getName());
		            viewContact.putExtra("phone_no", curr_contact.getPhoneNumber());
		            
		            ContactActivity.this.startActivity(viewContact);
			}
		});
        
    }   

    
    public void getAllContacts(){
    	// Getting/Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<ContactDetails> contacts = db.getAllContacts();
        List<String> contactnames = new ArrayList<String>();
         
        for (ContactDetails cn : contacts) {
            
        	contactnames.add(cn.getName());
        	
        	String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber() +
            		" ,Mobile: " + cn.getMobileNumber() + ",Work Mail: " + cn.getWorkEmail() + ",Home Mail: " + cn.getHomeEmail();
                // Writing Contacts to log
            Log.d("Name: ", log);
        } 

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, contactnames);
        setListAdapter(adapter);
    }
    
    @Override
    protected void onResume() {
      db.open();
      System.out.println("-------------Inside onResume-----------");
      super.onResume();
      getAllContacts();
    }

    @Override
    protected void onPause() {
      //db.close();
      super.onPause();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_contact, menu);
        return true;
    }
    
}
