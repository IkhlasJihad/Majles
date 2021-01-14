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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * @author Ikhlas Jihad
 */
public class EdituserController implements Initializable {

    Navigation nav = new Navigation();
    @FXML
    private AnchorPane root;
    DBModel db;
    @FXML
    private TextField username;
    @FXML
    private PasswordField pswd_tf;
    @FXML
    private Label label_out;
    ArrayList<String> users = new ArrayList<>();
    @FXML
    private Label validate;
    
    private String user;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = (DBModel)(rb.getObject("db"));
        user = (String)(rb.getObject("res"));
        try {
            users = db.getUsers();
        } catch (SQLException ex) {
        }
    } 
    boolean isUserValid ;
    @FXML
    private void onUserTyped(KeyEvent event) {
        boolean valid  =  !users.contains(username.getText());
        if(valid){
            isUserValid = true;
            validate.setText("صالح");
            validate.setTextFill(Color.GREEN);
        }
        else{
            isUserValid = false;
            validate.setText("غير صالح");
            validate.setTextFill(Color.RED);
        }
    }

    @FXML
    private void onEdit(ActionEvent event) {
       if(isUserValid & !pswd_tf.getText().equals("") & !username.getText().equals("")){
            try {
               db.editUserPswd(username.getText(), pswd_tf.getText());
               label_out.setText("تم التعديل");
               label_out.setTextFill(Color.GREEN);
               pswd_tf.setText("");
               username.setText("");
               
            } catch (SQLException ex) {
               label_out.setText("حدث خطأ."); 
               label_out.setTextFill(Color.RED);
            }
       }           
    }
    @FXML
    private void nav_back(ActionEvent event) {
        nav.navTowith_udb(root, nav.fxmladminmain, user, db);        
    }
   
    
}
