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
import java.util.Arrays;
import java.util.ResourceBundle;
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
 *
 * @author Ikhlas Jihad
 */
public class AddStdController implements Initializable {
    @FXML private Label label_out;
    @FXML
    private AnchorPane root;
    @FXML
    private Button close;
    @FXML
    private Button add;
    @FXML
    private TextField fname;
    @FXML
    private TextField phone;
    @FXML
    private TextField sname;
    @FXML
    private TextField tname;
    @FXML
    private TextField lastname;
    @FXML
    private ComboBox genderCombo;
    @FXML
    private ComboBox regionCombo;
    private DBModel db ;
    private String user;
    Navigation nav = new Navigation();
    String maj = "";
    
    String vol = "";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = (DBModel)(rb.getObject("db"));
        vol = (String)(rb.getObject("res"));
        regionCombo.setItems(FXCollections.observableArrayList(db.getRegions()));
        genderCombo.setItems(FXCollections.observableArrayList(
               new ArrayList<String>(Arrays.asList("F", "M"))));
    }    
    @FXML
    private void nav_back(ActionEvent event) {
        nav.navTowithMVDB(root, nav.fxmlvolMain, maj, vol,db);
    }
    @FXML
    private void addStudent(ActionEvent event) {
        if(!fname.getText().equals("") & !sname.getText().equals("") 
                & !tname.getText().equals("") & !lastname.getText().equals("") & regionCombo.getValue() != null)
            try {
                String stdID = db.addStd(fname.getText(),sname.getText(),tname.getText(),
                        lastname.getText(),
                        regionCombo.getValue().toString(),
                        genderCombo.getValue() == null ? null : genderCombo.getValue().toString());
                label_out.setText("تمت الإضافة بنجاح" + "الرقم الخاص:" + stdID );
                label_out.setTextFill(Color.GREEN);
        } catch (SQLException ex) {
            label_out.setText(ex.getMessage());
            label_out.setTextFill(Color.RED);
        }
        else{
           label_out.setText("تأكد من ملء الحقول الإجبارية.");
           label_out.setTextFill(Color.RED); 
        }
    }
    
}
