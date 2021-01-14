/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DBModel;
import Models.Navigation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ikhlas Jihad
 */
public class StartController implements Initializable {
    Navigation nav = new Navigation();
    @FXML
    private ImageView admin;
    @FXML
    private ImageView volunteer;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField user;
    @FXML
    private PasswordField pswd_tf;
    @FXML
    private Button log_in;
    @FXML
    private Label label;
    @FXML
    private ChoiceBox role;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.setItems(FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("متطوع", "مدير"))));
    }    

    @FXML
    private void on_login_btn(ActionEvent event) {
        DBModel db = new DBModel();
        if(user.getText().equals("") | pswd_tf.getText().equals("") | role.getValue() == null)
            label.setText("يجب ملء كل الحقول");
        else{
            String userRole = role.getValue().toString();
            boolean opResult = db.mainConnect(user.getText(), pswd_tf.getText());
            if(!opResult)
                label.setText("حدث خطأ أثناء تسجيل الدخول، يرجى التحقق من المدخلات");
            else{             
                switch(userRole){
                    case "متطوع":
                        nav.navTowithMVDB(root, nav.fxmlvolMain, "", user.getText(),db); 
                        break;
                    case "مدير":                       
                        nav.navTowith_udb(root, nav.fxmladminmain,user.getText(),db); 
                        break;
                }
                
            }
        }
    }
}
