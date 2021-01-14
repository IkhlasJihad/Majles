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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Ikhlas Jihad
 */
public class CreateusersController implements Initializable {

    @FXML
    private ComboBox roleCombo;
    @FXML
    private TextField username;
    @FXML
    private PasswordField pswd_tf;
    @FXML
    private Label label_out;
    Navigation nav = new Navigation();
    @FXML
    private AnchorPane root;
    DBModel db ;// new DBModel("");
    private String user;
    ArrayList<String> roles = new ArrayList<>();
    @FXML
    private Label name_label;
    @FXML
    private TextField name;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = (DBModel)(rb.getObject("db"));
        user = (String)(rb.getObject("res"));
        roles.add("متطوع"); roles.add("مدير نظام");
        roleCombo.setItems(FXCollections.observableArrayList(roles));
    }    

    @FXML
    private void onCreate(ActionEvent event) {
        if(roleCombo.getValue()!= null & !pswd_tf.getText().equals("") & !username.getText().equals("")){
            try {
                String role = roleCombo.getValue().toString();
                switch(role){
                    case "متطوع": 
                        name_label.setVisible(true);
                        name.setVisible(true);
                        if(!name.getText().equals(""))
                            db.createVolUser(username.getText(), pswd_tf.getText(),name.getText());
                        else 
                            label_out.setText("أدخل اسم المتطوع.");
                            label_out.setTextFill(Color.RED);
                                  break;
                    case "مدير نظام": db.createAdminUser(username.getText(), pswd_tf.getText()); break;
                }
                nav_back(event);
            } catch (SQLException ex) {
                label_out.setText("حدث خطأ." + ex.getSQLState());
                label_out.setTextFill(Color.RED);
            }
        }
        else{
            label_out.setText("يجب ملء جميع الحقول.");
            label_out.setTextFill(Color.RED);
        }  
    }

    @FXML
    private void nav_back(ActionEvent event) {
        nav.navTowith_udb(root, nav.fxmladminmain, user, db);   
    }
    
}
