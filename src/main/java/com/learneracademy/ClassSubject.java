package com.learneracademy;

public class ClassSubject {
    private int ID;
    private int CLASSID;
    private int SUBJECTID;
    private int TEACHERID;
    
    public ClassSubject() {
            
    }
    public ClassSubject(int id, int classid,int subjectid,int teacherid) {
            this.ID = id;
            this.CLASSID = classid;
            this.SUBJECTID = subjectid;
            this.TEACHERID = teacherid;
           
    }
    
    public int getID() {return this.ID; }
    public int getCLASSID() {return this.CLASSID; }
    public int getSUBJECTID() {return this.SUBJECTID; }
    public int getTEACHERID() {return this.TEACHERID; }
  
  
    public void setID(int id) { this.ID = id;}
    public void setCLASSID(int classid) { this.CLASSID = classid;}
    public void setSUBJECTID(int subjectid) { this.SUBJECTID = subjectid;}
    public void setTEACHERID(int teacherid) { this.TEACHERID = teacherid;}
   

}
