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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Ikhlas Jihad
 */
public class AddVolController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button close;
    @FXML
    private Button add;
    @FXML
    private TextField phone;
    @FXML
    private ComboBox regionCombo;
    @FXML
    private Label label_out;
    DBModel db ;
    Navigation nav = new Navigation();
    @FXML
    private TextField username;
    @FXML
    private TextField name;
    String user;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = (DBModel)(rb.getObject("db"));
        user = (String)(rb.getObject("res"));
        regionCombo.setItems(FXCollections.observableArrayList(db.getRegions()));
    }    

    @FXML
    private void nav_back(ActionEvent event) {
        nav.navTowith_udb(root, nav.fxmladminmain, user, db); 
    }

    @FXML
    private void onAddVol(ActionEvent event) {
       if(!username.getText().equals("") & !name.getText().equals("")){
           try {
                db.insertVolunteer(username.getText(), name.getText(),
                       regionCombo.getValue() == null ? null : regionCombo.getValue().toString(),
                       phone.getText());
                label_out.setText("تمت الإضافة بنجاح");
                label_out.setTextFill(Color.GREEN);
           } catch (SQLException ex) {
                label_out.setText("حدث خطأ!");
                label_out.setTextFill(Color.RED);
           }
       }
       else {
            label_out.setText("يجب ملء الحقول الإجبارية");
            label_out.setTextFill(Color.RED);
       
       }
    }
    
}
