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
public class Lecture {
    private String majles;
    private String id;
    private String title;
    private String date;
    private String time;
    private String place;

    public Lecture(String majles, String id, String title, String date, String time, String place) {
        this.majles = majles;
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.place = place;
    }

    public Lecture(String id, String title, String date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public void setMajles(String majles) {
        this.majles = majles;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMajles() {
        return majles;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }
    
    
    
}
