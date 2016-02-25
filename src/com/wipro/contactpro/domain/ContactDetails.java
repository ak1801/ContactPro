package com.wipro.contactpro.domain;

public class ContactDetails {

	/*private long id;
	private String Name;
	private String Mobile;
	private String HomePhone;
	private String WorkEmail;
	private String HomeEmail;
	
	public long getId() {
		return id;
	}
	public void setId(long l) {
		this.id = l;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getHomePhone() {
		return HomePhone;
	}
	public void setHomePhone(String homePhone) {
		HomePhone = homePhone;
	}
	public String getWorkEmail() {
		return WorkEmail;
	}
	public void setWorkEmail(String workEmail) {
		WorkEmail = workEmail;
	}
	public String getHomeEmail() {
		return HomeEmail;
	}
	public void setHomeEmail(String homeEmail) {
		HomeEmail = homeEmail;
	}
	
	// Will be used by the ArrayAdapter in the ListView
		  @Override
		  public String toString() {
		    return Name;
		  }*/
	
	
	
	//private variables
    int _id;
    String _name;
    String _phone_number;
    String _mobile_number;
	String _work_email;
	String _home_email;
     
    // Empty constructor
    public ContactDetails() {
		// TODO Auto-generated constructor stub 
    }
    
    // constructor
    public ContactDetails (int id, String name, String _phone_number, String _mobile_number, String _work_email, String _home_email){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
        this._mobile_number = _mobile_number;
        this._work_email = _work_email;
        this._home_email = _home_email;
    }
     
    // constructor
    public ContactDetails(String name, String _phone_number, String _mobile_number, String _work_email, String _home_email){
        this._name = name;
        this._phone_number = _phone_number;
        this._mobile_number = _mobile_number;
        this._work_email = _work_email;
        this._home_email = _home_email;
    }
    
    // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
     
    // getting name
    public String getName(){
        return this._name;
    }
     
    // setting name
    public void setName(String name){
        this._name = name;
    }
     
    // getting phone number
    public String getPhoneNumber(){
        return this._phone_number;
    }
     
    // setting phone number
    public void setPhoneNumber(String phone_number){
    	System.out.println("phone no : "+phone_number);
        this._phone_number = phone_number;
    }

    // getting mobile number
    public String getMobileNumber(){
        return this._mobile_number;
    }
     
    // setting mobile number
    public void setMobileNumber(String mobile_number){
        this._mobile_number = mobile_number;
    }
    
    // getting work email
    public String getWorkEmail(){
        return this._work_email;
    }
     
    // setting work email
    public void setWorkEmail(String work_email){
    	System.out.println("Work Email : "+work_email);
        this._work_email = work_email;
    }
    
    // getting work email
    public String getHomeEmail(){
        return this._home_email;
    }
     
    // setting work email
    public void setHomeEmail(String home_email){
        this._home_email = home_email;
    }
	
}
