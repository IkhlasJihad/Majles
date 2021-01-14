/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
/**
 *
 * @author Ikhlas Jihad
 */
public class Student {
    private String id;
    private String fname;
    private String sname;
    private String tname;
    private String lastname;
    private String region;
    private String gender;
    public Student(String id, String fname, String sname, String tname, String lastname, String region, String gender) {
        this.id = id;
        this.fname = fname;
        this.sname = sname;
        this.tname = tname;
        this.lastname = lastname;
        this.region = region;
        this.gender = gender;
    }
    public String getFullName(){
        return fname + " " + sname + " " + tname + " " + lastname;
    }
    
    public String getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getSname() {
        return sname;
    }

    public String getTname() {
        return tname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getRegion() {
        return region;
    }

    public String getGender() {
        return gender;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    
}
