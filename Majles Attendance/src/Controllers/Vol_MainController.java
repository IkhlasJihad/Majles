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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @author Ikhlas Jihad
 */
public class Vol_MainController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView volunteer;
    @FXML
    private Label label_vol;
    @FXML
    private ComboBox majlesCombo;
    @FXML
    private ImageView attendance_img;
    @FXML
    private ImageView addStd_img;
    @FXML
    private ImageView reports_img;
    @FXML
    private ImageView addLec_img;
    @FXML
    private ImageView edit_img;
    DBModel db ;//= new DBModel();
    Navigation nav = new Navigation();
    String vol = "" ; 
    String maj = "";
    @FXML
    private AnchorPane exist_root;
    @FXML
    private AnchorPane addSTD_root;
    @FXML
    private TextField exist_id;
    @FXML
    private Button add;
    @FXML
    private Label exist_label;
    @FXML
    private ImageView img;
    @FXML
    private DialogPane logoutDialog;
    private String majName;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(rb.getObject("res")!=null){
            vol = (String)(rb.getObject("res")); 
        }
        db = (DBModel)(rb.getObject("db"));
       // System.out.println(vol);
        majlesCombo.setItems(FXCollections.observableArrayList(db.getMajlesManagedBy(vol)));
        label_vol.setText(db.getVolName(vol));
        if(!maj.equals(""))
           majlesCombo.setValue(maj);
        
        Button ignore = (Button) logoutDialog.lookupButton(ButtonType.YES);
        if(ignore != null)
            ignore.setOnAction(e -> { 
                db.exit();
                nav.navTo(root, nav.fxmlstart);
            });
        Button save = (Button) logoutDialog.lookupButton(ButtonType.NO);
        if(save != null)
            save.setOnAction(e -> { 
                logoutDialog.setVisible(false);
            }); 
    }    

    @FXML
    private void on_attendance(MouseEvent event) {
        if(majlesCombo.getValue()!= null){
            maj = majlesCombo.getValue().toString();
            try {
                majName = db.getMajlesName_Place(maj)[0];
                //System.out.println(majName);
            }catch (SQLException ex) {
            }
        nav.navTowithMVDB(root, nav.fxmlattendance, maj, vol,db);
        }
    }

    @FXML
    private void on_addStd(MouseEvent event) {
        if(majlesCombo.getValue()!= null){
            addSTD_root.setVisible(true);
        }
    }

    @FXML
    private void getReports(MouseEvent event) {
        nav.navTowithMVDB(root, nav.fxmlreports, majlesCombo.getValue().toString(), vol,db);
    }

    @FXML
    private void on_addLectures(MouseEvent event) {
        if(majlesCombo.getValue()!= null){
            nav.navTowithMVDB(root, nav.fxmladdLec, majlesCombo.getValue().toString(), vol,db);
        }
    }

    @FXML
    private void on_edit(MouseEvent event) {
        if(majlesCombo.getValue()!= null){
            nav.navTowithMVDB(root, nav.fxmledit, majlesCombo.getValue().toString(), vol,db);
        }
    }
    @FXML
    private void Onnew(ActionEvent event) {
        nav.navTowithMVDB(root, nav.fxmladdStd, majlesCombo.getValue().toString(), vol,db);        
    }
    @FXML
    private void Onexist(ActionEvent event) {
        addSTD_root.setVisible(false);
        exist_root.setVisible(true);
    }

    @FXML   
    private void OnExistclose(MouseEvent event) {
        exist_root.setVisible(false);
    }
    
    @FXML
    private void onClosestdroot(MouseEvent event) {
        addSTD_root.setVisible(false);
    }
    @FXML
    private void addExist(ActionEvent event) {
        if(!exist_id.getText().equals(""))
            try {
                db.addTojoins(exist_id.getText(), majlesCombo.getValue().toString());
                exist_label.setText("تمت الإضافة بنجاح");
                
        } catch (SQLException ex) {
            exist_label.setText("حدث خطأ، تأكد من الرقم");
        }
        else
            exist_label.setText("أدخل رقم الطالب.");
    }
    @FXML
    private void onLogOut(ActionEvent event){
        logoutDialog.setVisible(true);
        logoutDialog.requestFocus();
    
    }
    
}
