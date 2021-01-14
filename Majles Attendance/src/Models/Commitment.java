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
public class Commitment {
    private String com_id;
    private String com_name;
    private int com_count;
    private String att;
    public String getCom_id() {
        return com_id;
    }

    public String getCom_name() {
        return com_name;
    }

    public int getCom_count() {
        return com_count;
    }

    public void setCom_id(String com_id) {
        this.com_id = com_id;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public void setCom_count(int com_count) {
        this.com_count = com_count;
    }

    public Commitment(String id, String name, int lec) {
        this.com_id = id;
        this.com_name = name;
        this.com_count = lec;
    }  
}
