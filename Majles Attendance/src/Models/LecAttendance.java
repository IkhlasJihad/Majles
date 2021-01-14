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
public class LecAttendance {
    private String la_id;
    private String la_name;
    private String att;
    public String getLa_id() {
        return la_id;
    }

    public String getLa_name() {
        return la_name;
    }

    public void setLa_id(String la_id) {
        this.la_id = la_id;
    }

    public void setLa_name(String la_name) {
        this.la_name = la_name;
    }

    public String getAtt() {
        return att;
    }

    public void setAtt(String att) {
        this.att = att;
    }

    public LecAttendance(String id, String name, String att) {
        this.la_id = id;
        this.la_name = name;
        this.att = att;
    }  
}

