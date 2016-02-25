package com.wipro.contactpro.activity;

import com.wipro.contactpro.R;
import com.wipro.contactpro.domain.ContactDetails;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewContactDetails extends Activity{

	ImageView iconBack,call,edit;
	TextView tv1,tv2,tv3;
	static int position;
	static String name, phone_no;
	// constant to determine which sub-activity returns
	  private static final int REQUEST_CODE = 10;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		System.out.println("------------------Inside View Contact--------------------");
		
		setContentView(R.layout.contact_detail);
		
		Intent intent = getIntent();
	    
		int position = intent.getIntExtra("position", 0);
	    String name = intent.getStringExtra("name");
	    String phone_no = intent.getStringExtra("phone_no");
	    this.position=position;
	    this.name = name;
	    this.phone_no = phone_no;
	    
	    System.out.println("position : -----"+position+" ------");
	    System.out.println("name : -----"+name+" ------");
	    System.out.println("phone no : -----"+phone_no+" ------");
	    
	    System.out.println("class position : ----"+ViewContactDetails.position+" ------");
	    System.out.println("class name : -----"+ViewContactDetails.name+" ------");
	    System.out.println("class phone no : -----"+ViewContactDetails.phone_no+" ------");
	    
	    tv1 = (TextView) findViewById(R.id.TextView01);
		tv1.setText(name);
		
		tv2 = (TextView) findViewById(R.id.TextView02);
		tv2.setText(phone_no);
	    
	    iconBack = (ImageView) findViewById(R.id.contactDetails_icon_back);
	    
	    iconBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			finish();	
			}
		});
	    
		
		// Calling Feature
	    call=(ImageView)findViewById(R.id.call);
	    call.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {

			//	for calling person
				tv3 = (TextView) findViewById(R.id.TextView02);
			
				if(!(tv3.getText().toString().isEmpty()) ){
					
				Intent call = new Intent(Intent.ACTION_DIAL);

				call.setData(Uri.parse("tel:" + tv3.getText()));
				startActivity(call);
				}
				else{
					
					Toast.makeText(ViewContactDetails.this,"Contact number Not Found", Toast.LENGTH_SHORT).show();
				}
				
			}
			
		});
		
		// Edit contact
	    edit=(ImageView)findViewById(R.id.edit);
	    edit.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View v) {
			
			Intent editContact = new Intent(ViewContactDetails.this,
									EditContactDetails.class);
			System.out.println("position in edit intent : "+ViewContactDetails.position);
			editContact.putExtra("position", ViewContactDetails.position);
			editContact.putExtra("name", ViewContactDetails.name);
			editContact.putExtra("phone_no", ViewContactDetails.phone_no);
			
			//ViewContactDetails.this.startActivity(editContact);
			startActivityForResult(editContact, REQUEST_CODE);
			
			}
		
		});
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK) { //&& requestCode == REQUEST_CODE
		      /*if (data.hasExtra("returnkey")) {
		        String result = data.getExtras().getString("returnkey");
		        if (result != null && result.length() > 0) {
		          Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
		        }
		      }*/
		    
		
			System.out.println("--------onActivityResult-------");
			if(data.getExtras().containsKey("newName")){	

				System.out.println("New Name----"+data.getStringExtra("newName"));
				tv1 = (TextView) findViewById(R.id.TextView01);
				tv1.setText(data.getStringExtra("newName"));

			}
			if(data.getExtras().containsKey("newNumber")){
				
				System.out.println("New Name----"+data.getStringExtra("newNumber"));
				tv2 = (TextView) findViewById(R.id.TextView02);
				tv2.setText(data.getStringExtra("newNumber"));
			}
		
		
		}
		
		
		

	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	
	/*@Override
	protected void onResume() {
		super.onResume();
		
		System.out.println("----------------onResume--------------");
	}*/

	
}
