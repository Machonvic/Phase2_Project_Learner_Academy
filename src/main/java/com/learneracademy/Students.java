package com.learneracademy;


public class Students {
    private long ID;
    private int classid;
    private String firstname;
    private String lastname;
    
    public Students() {
            
    }
    public Students(long id, int classid, String firstname,String lastname) {
            this.ID = id;
            this.classid=classid;
            this.firstname = firstname;
            this.lastname = lastname;
       
    }
    
    
    public long getID() {return this.ID; }
    public int getClassid() {return this.classid; }
    public String getFirstname() { return this.firstname;}
    public String getLastname() { return this.lastname;}
 
    public void setID(long id) { this.ID = id;}
    public void setClassid(int classid) { this.classid = classid;}
    public void setFirstname(String firstname) { this.firstname = firstname;}
    public void setLastname(String lastname) { this.lastname = lastname;}
   
}
