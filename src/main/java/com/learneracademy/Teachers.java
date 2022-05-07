package com.learneracademy;


public class Teachers {
    private long ID;
    private String firstname;
    private String lastname;
    
    public Teachers() {
            
    }
    public Teachers(long id, String firstname,String lastname) {
            this.ID = id;
            this.firstname = firstname;
            this.lastname = lastname;
       
    }
    
    
    public long getID() {return this.ID; }
    public String getFirstname() { return this.firstname;}
    public String getLastname() { return this.lastname;}
 
    public void setID(long id) { this.ID = id;}
    public void setFirstname(String firstname) { this.firstname = firstname;}
    public void setLastname(String lastname) { this.lastname = lastname;}
   

}
