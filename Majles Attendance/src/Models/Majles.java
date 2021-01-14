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
public class Majles {
    private String id;
    private String name;
    private String sheikh;
    private String place;
    private String subject;

    public Majles(String id, String sheikh, String place, String name, String subject) {
        this.id = id;
        this.name = name;
        this.sheikh = sheikh;
        this.place = place;
        this.subject = subject;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSheikh(String sheikh) {
        this.sheikh = sheikh;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSheikh() {
        return sheikh;
    }

    public String getPlace() {
        return place;
    }

    public String getSubject() {
        return subject;
    }
    
           
    
    
    
}
