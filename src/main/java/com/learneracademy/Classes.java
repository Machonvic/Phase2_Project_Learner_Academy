package com.learneracademy;
public class Classes {
	
	private long ID;
    private String classname;
   
    
    public Classes() {
            
    }
    public Classes(long id, String class_name) {
            this.ID = id;
            this.classname = class_name;
         
    }
    
    public long getID() {return this.ID; }
    public String getClassname() { return this.classname;}

    
    public void setID(long id) { this.ID = id;}
    public void setClassname(String class_name) { this.classname = class_name;}



}
