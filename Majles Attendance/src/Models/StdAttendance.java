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
public class StdAttendance {
    private String id;
    private String lec_id;
    private String date;
    private String attends;

    public StdAttendance(String id, String lec, String date, String attends) {
        this.id = id;
        this.lec_id = lec;
        this.date = date;
        this.attends = attends;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLec_id(String lec_id) {
        this.lec_id = lec_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAttends(String attends) {
        this.attends = attends;
    }

    public String getId() {
        return id;
    }

    public String getLec_id() {
        return lec_id;
    }

    public String getDate() {
        return date;
    }

    public String getAttends() {
        return attends;
    }
    
    
    
}
