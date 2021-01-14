/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DBModel;
import Models.Navigation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Ikhlas Jihad
 */
public class Admin_interfaceController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView img;
    @FXML
    private ImageView addVol_img;
    @FXML
    private ImageView addmajles_img;
    @FXML
    private ImageView sheikh_mg;
    @FXML
    private ImageView responsiple_img;
    @FXML
    private ImageView add_Std;
    @FXML
    private AnchorPane setRes_root;
    @FXML
    private Button add1;
    @FXML
    private ComboBox majCombo;
    @FXML
    private ComboBox userCombo;
    @FXML
    private Label label_out;
    @FXML
    private AnchorPane settings_root;
    @FXML
    private ComboBox majIDCombo;
    @FXML
    private Label labelOut;
    ArrayList<String> users = new ArrayList<>();
    @FXML
    private AnchorPane getMajles_editroot;
    @FXML
    private AnchorPane getMajles_deleteroot;
    @FXML
    private ComboBox majIDCombo1;
    @FXML
    private Label labelOut1;
    @FXML
    private AnchorPane users_settings_root;
    @FXML
    private AnchorPane getUser_deleteroot;
    @FXML
    private DialogPane logoutDialog;
    @FXML
    private ComboBox delUserCombo;
    @FXML
    private Label label_delUser;
    @FXML
    private AnchorPane getUser_editeroot;
    @FXML
    private ComboBox editUserCombo;
    @FXML
    private Label label_editUser;
    @FXML private Label user;
   private String username;
    DBModel db ;
    Navigation nav = new Navigation();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = (DBModel)(rb.getObject("db"));
        if(rb.getObject("res") != null )
            username = (String)(rb.getObject("res"));
        else username = db.getUser();
        user.setText(username);
        try {
            majCombo.setItems(FXCollections.observableArrayList(db.getMajales()));
            majIDCombo.setItems(FXCollections.observableArrayList(db.getMajales()));
            majIDCombo1.setItems(FXCollections.observableArrayList(db.getMajales()));
            // TODO
        } catch (SQLException ex) {
        }
        
        Button ignore = (Button) logoutDialog.lookupButton(ButtonType.YES);
        if(ignore != null)
            ignore.setOnAction(e -> { 
                db.exit();
                nav.navTo(root, nav.fxmlstart);
            });
        Button stay = (Button) logoutDialog.lookupButton(ButtonType.NO);
        if(stay != null)
            stay.setOnAction(e -> { 
                logoutDialog.setVisible(false);
            }); 
    }    

    @FXML
    private void on_addVol(MouseEvent event) {
        nav.navTowith_udb(root, nav.fxmladdVol,username,db);
       // nav.navTo(root,nav.fxmladdVol);
    }

    @FXML
    private void on_addMajles(MouseEvent event) {
        settings_root.setVisible(false);
        nav.navTowith_udb(root,nav.fxmladdMaj,username,db);
    }

    @FXML
    private void on_responsiple(MouseEvent event) {
        try {
            users = db.getUsers();
        } catch (SQLException ex) {
        }
        userCombo.setItems(FXCollections.observableArrayList(users));
        setRes_root.setVisible(true);
    }


    @FXML
    private void on_addStd(MouseEvent event) {
        
        nav.navTowith_udb(root, nav.fxmladdStd, username,db);
    }

    @FXML
    private void setRes(ActionEvent event) {
        try {
            if(majCombo.getValue() != null & (userCombo.getValue()) != null ){
                    db.setRes(userCombo.getValue().toString(),majCombo.getValue().toString());
                    label_out.setText("تــم");
                    label_out.setTextFill(Color.GREEN);
            }
            else{
                label_out.setText("اختر مجلس و متطوع.");
                label_out.setTextFill(Color.RED);
            }
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void close(ActionEvent event) {
        setRes_root.setVisible(false);
    }

    @FXML
    private void onEditChoose(MouseEvent event) {
        settings_root.setVisible(true);
        settings_root.setFocusTraversable(true);
    }

    @FXML
    private void editMajles(MouseEvent event) {
        settings_root.setVisible(false);
        try {
            majIDCombo1.setItems(FXCollections.observableArrayList(db.getMajales()));
        } catch (SQLException ex) {
        }
        getMajles_editroot.setVisible(true);
        getMajles_editroot.requestFocus();
    }


    @FXML
    private void onClosesettings(MouseEvent event) {
        settings_root.setVisible(false);
    }


    @FXML
    private void navToEditMajles(MouseEvent event) {
        
        if(majIDCombo.getValue()!= null){
            try {
                getMajles_editroot.setVisible(false);
                nav.navTowithMajlesDB(root, nav.fxmleditMajles, db.getMajles(majIDCombo.getValue().toString()),db);
            } catch (SQLException ex) {
                Logger.getLogger(Admin_interfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else  labelOut.setText("اختر مجلس ..");
        
    }
     @FXML
    private void deleteMajles(MouseEvent event) {
        
        if(majIDCombo.getValue()!= null){
            try {
                db.deleteMajles(majIDCombo.getValue().toString());
            } catch (SQLException ex) {
                labelOut.setText("حدث خطأ");
            }
        }
        else  labelOut.setText("اختر مجلس ..");
        
    }

    @FXML
    private void ondelete(MouseEvent event) {
        settings_root.setVisible(false);
        getMajles_deleteroot.setVisible(true);
        try {
            majIDCombo1.setItems(FXCollections.observableArrayList(db.getMajales()));
        } catch (SQLException ex) {
        }
        getMajles_deleteroot.setFocusTraversable(true);
    }

    @FXML
    private void onClosegetMajEdit(MouseEvent event) {
        getMajles_editroot.setVisible(false);
    }

    @FXML
    private void onClosegetMajDlete(MouseEvent event) {
        getMajles_deleteroot.setVisible(false);
    }

    @FXML
    private void onUserSettings(MouseEvent event) {
        users_settings_root.setVisible(true);
        users_settings_root.setFocusTraversable(true);
    }

    @FXML
    private void onClose_Usersettings(MouseEvent event) {
        users_settings_root.setVisible(false);
    }

    @FXML
    private void getUserName_edit(MouseEvent event) {
        try {
            users = db.getUsers();
            editUserCombo.setItems(FXCollections.observableArrayList(users));
            getUser_editeroot.setVisible(true);
            users_settings_root.setVisible(false);
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void getUserName_delete(MouseEvent event) {
         try {
            users = db.getUsers();
            delUserCombo.setItems(FXCollections.observableArrayList(users));
            getUser_deleteroot.setVisible(true);
            getUser_deleteroot.setFocusTraversable(true);
            users_settings_root.setVisible(false);
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void onClose_User_deleteroot(MouseEvent event) {
        getUser_deleteroot.setVisible(false);
    }
    @FXML
    private void createUser(MouseEvent event) {
        nav.navTowith_udb(root, nav.fxmlcreateuser, username, db);
        
    }
    @FXML
    private void deleteUser(MouseEvent event) {
        if(delUserCombo.getValue()!= null ){
            try {
                db.deleteUser(delUserCombo.getValue().toString());
                label_delUser.setText("تم حذف" + delUserCombo.getValue().toString());
                users = db.getUsers();
                delUserCombo.setItems(FXCollections.observableArrayList(users));
            } catch (SQLException ex) {
                label_delUser.setText("حدث خطأ.");
                label_delUser.setTextFill(Color.RED);
            }
        }
    }

    @FXML
    private void onClose_User_editroot(MouseEvent event) {
        getUser_editeroot.setVisible(false);
    }

    @FXML
    private void editUser(MouseEvent event) {
        if(editUserCombo.getValue()!= null){
            nav.navTowith_udb(root, nav.fxmledituser, username,db);
            label_editUser.setTextFill(Color.GREEN);
        }
        else {
            label_editUser.setText("حدث خطأ.");
            label_editUser.setTextFill(Color.RED);
        }
    }
    @FXML
    private void onLogOut(MouseEvent event){
        logoutDialog.setVisible(true);
        logoutDialog.requestFocus();
    }    
}
