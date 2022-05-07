package com.learneracademy;

public class Subjects {
    private long ID;
    private String subjectname;

    
    public Subjects() {
            
    }
    public Subjects(long id, String subjectName) {
            this.ID = id;
            this.subjectname = subjectName;
           
    }
    
    public long getID() {return this.ID; }
    public String getSubjectname() { return this.subjectname;}
   
    
    public void setID(long id) { this.ID = id;}
    public void setSubjectname(String subjectname) { this.subjectname = subjectname;}
    
}
