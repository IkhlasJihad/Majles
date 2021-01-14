/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.IOException;
import java.util.ListResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Ikhlas Jihad
 */
public class Navigation {
    public final String fxmlstart = "/views/start.fxml";
    public final String fxmlvolMain = "/views/vol_main.fxml";
    public final String fxmladdStd = "/views/addStd.fxml";
    public final String fxmladdLec = "/views/addLecture.fxml";
    public final String fxmledit = "/views/edit.fxml";
    public final String fxmladminmain = "/views/admin_interface.fxml";
    public final String fxmlattendance = "/views/attendance.fxml";
    public final String fxmlreports = "/views/reports.fxml";
    public final String fxmladdMaj = "/views/addmajles.fxml";
    public final String fxmladdsheikh = "/views/addSheikh.fxml";
    public final String fxmladdVol = "/views/addVol.fxml";
    public final String fxmleditMajles = "/views/editMajles.fxml";
    public final String fxmlcreateuser = "/views/createusers.fxml"; 
    public final String fxmledituser = "/views/edituser.fxml"; 
    public void navTo(Parent rootPane, String path){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            rootPane.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void navTowithUSER(Parent rootPane, String path, String s){         
        MyResources_user res = new MyResources_user(s);
            try {
                Parent root = FXMLLoader.load(getClass().getResource(path),res);
                rootPane.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void navTowith_udb(Parent rootPane, String path, String s, DBModel db){         
        MyResources_userdb res = new MyResources_userdb(s, db);
            try {
                Parent root = FXMLLoader.load(getClass().getResource(path),res);
                rootPane.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void navTowithMAJ_ID(Parent rootPane, String path, String s){         
        MyResources_maj res = new MyResources_maj(s);
            try {
                Parent root = FXMLLoader.load(getClass().getResource(path),res);
                rootPane.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    public void navTowithMVDB(Parent rootPane, String path, String m, String v, DBModel db){         
        MyResources_MVDB res = new MyResources_MVDB(m,v,db);
            try {
                Parent root = FXMLLoader.load(getClass().getResource(path),res);
                rootPane.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public void navTowithMajles(Parent rootPane, String path, Majles m){         
        MyResources_m res = new MyResources_m(m);
            try {
                Parent root = FXMLLoader.load(getClass().getResource(path),res);
                rootPane.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void navTowithMajlesDB(Parent rootPane, String path, Majles m, DBModel db){         
        MyResources_mdb res = new MyResources_mdb(m, db);
            try {
                Parent root = FXMLLoader.load(getClass().getResource(path),res);
                rootPane.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void navTowithMname(Parent rootPane, String path, String m, String v, String n){         
        MyResources_MNameVol res = new MyResources_MNameVol(m,v,n);
            try {
                Parent root = FXMLLoader.load(getClass().getResource(path),res);
                rootPane.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public void navTowithLecture(Parent rootPane, String path, Lecture s){
         
         MyResources_Lec res = new MyResources_Lec(s);
            try {
                Parent root = FXMLLoader.load(getClass().getResource(path),res);
                rootPane.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    public void navTowithDB(Parent rootPane, String path, DBModel db){
         
         MyResources_db res = new MyResources_db(db);
            try {
                Parent root = FXMLLoader.load(getClass().getResource(path),res);
                rootPane.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
public class MyResources_db extends ListResourceBundle {
    private DBModel db  ;
    MyResources_db(){}
    public MyResources_db(DBModel db){this.db = db;}
     @Override
     protected Object[][] getContents() {
        return new Object[][] {
         // LOCALIZE THIS
             {"db", db}                    
        };
    }
 }

public class MyResources_mdb extends ListResourceBundle {
    private Majles m ;
    private DBModel db;
    MyResources_mdb(){}
    public MyResources_mdb(Majles m ,DBModel db){
        this.m = m;
        this.db = db;
    }
    
     @Override
     protected Object[][] getContents() {
        return new Object[][] {
         // LOCALIZE THIS
            {"majles", m},{"db",db}
                
        };
    }
 }
public class MyResources_user extends ListResourceBundle {
    private String user  ;
    MyResources_user(){}
    public MyResources_user(String user){this.user = user;}
     @Override
     protected Object[][] getContents() {
        return new Object[][] {
         // LOCALIZE THIS
             {"res", user}                    
        };
    }
 }
public class MyResources_userdb extends ListResourceBundle {
    private String user  ;
    private DBModel db; 
    MyResources_userdb(){}
    public MyResources_userdb(String user,  DBModel db){this.user = user; this.db =db;}
     @Override
     protected Object[][] getContents() {
        return new Object[][] {
         // LOCALIZE THIS
             {"res", user}, {"db",db}                    
        };
    }
 }
public class MyResources_maj extends ListResourceBundle {
    private String id  ;
    MyResources_maj(){}
    public MyResources_maj(String id){this.id = id;}
     @Override
     protected Object[][] getContents() {
        return new Object[][] {
         // LOCALIZE THIS
             {"res", id}                    
        };
    }
 }
public class MyResources_MVDB extends ListResourceBundle {
    private String vol  ;
    private String m;
    private DBModel db;
    MyResources_MVDB(){}
    public MyResources_MVDB(String m,String vol, DBModel db){
        this.m = m;
        this.vol = vol;
        this.db = db;
    }
     @Override
     protected Object[][] getContents() {
        return new Object[][] {
         // LOCALIZE THIS
             {"res",vol },{"majles", m} , {"db",db}                   
        };
    }
     
 }
public class MyResources_MNameVol extends ListResourceBundle {
    private String vol  ;
    private String m;
    private String name;
    MyResources_MNameVol(){}
    public MyResources_MNameVol(String m,String vol, String n){
        this.m = m;
        this.vol = vol;
        this.name= n;
    }
     @Override
     protected Object[][] getContents() {
        return new Object[][] {
         // LOCALIZE THIS
             {"res",vol },{"majles", m} , {"majName", m}                   
        };
    }
 }
public class MyResources_Lec extends ListResourceBundle {
    
    private Lecture l ;
    MyResources_Lec(){}
    public MyResources_Lec(Lecture l){
        this.l = l;
    }
    
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
        // LOCALIZE THIS
           {"lec", l} 
        };
     }}
public class MyResources_m extends ListResourceBundle {
    private Majles m ;
    MyResources_m(){}
    public MyResources_m(Majles m ){
        this.m = m;
    }
    
     @Override
     protected Object[][] getContents() {
        return new Object[][] {
         // LOCALIZE THIS
            {"majles", m},
                
        };
    }
 }

}