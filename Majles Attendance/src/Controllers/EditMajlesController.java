/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DBModel;
import Models.Majles;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @author Ikhlas Jihad
 */
public class EditMajlesController implements Initializable {
    private DBModel db ;
    private Navigation nav = new Navigation();
    @FXML
    private Button close;
    @FXML
    private Button add;
    @FXML
    private TextField subject;
    @FXML
    private TextField place_tf;
    @FXML
    private TextField title_tf;
    @FXML
    private Label label_out;
    @FXML
    private TextField id;
    @FXML
    private ComboBox shCombo;
    private Majles majles ;
    private AnchorPane root;
    @FXML
    private DialogPane SaveDialog;
    @FXML
    private ButtonType del_no;
    @FXML
    private ButtonType del_yes;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = (DBModel)(rb.getObject("db"));
        majles = (Majles)(rb.getObject("majles"));
        try {
            shCombo.setItems(FXCollections.observableArrayList(db.getSheikh()));
            id.setText(majles.getId());
            place_tf.setText(majles.getPlace());
            title_tf.setText(majles.getName());
            subject.setText(majles.getSubject());
            shCombo.setValue(majles.getSheikh());
        } catch (SQLException ex) {}
        Button ignore = (Button) SaveDialog.lookupButton(ButtonType.YES);
        if(ignore != null)
            ignore.setOnAction(e -> { 
                 nav.navTowith_udb(root, nav.fxmladminmain, db.getUser(), db); 
            });
        Button save = (Button) SaveDialog.lookupButton(ButtonType.NO);
        if(save != null)
            save.setOnAction(e -> { 
                addMaj(e);
                 nav.navTowith_udb(root, nav.fxmladminmain, db.getUser(), db); 
            }); 
    }    

    @FXML
    private void nav_back(ActionEvent event) {
        if(!check())
            nav.navTowith_udb(root, nav.fxmladminmain, db.getUser(), db); 
        else
            SaveDialog.setVisible(true);
    }
    private boolean check(){
         return (!majles.getName().equals(title_tf.getText())
                | !subject.getText().equals(majles.getSubject())
                | !place_tf.getText().equals(majles.getPlace())
                | !shCombo.getValue().toString().equals(majles.getSheikh()));
        
    }
    @FXML
    private void addMaj(ActionEvent event) {
        String id = majles.getId();
        if(check()) {
            try {
                if(!majles.getName().equals(title_tf.getText()))
                    db.updateMajName(majles.getId(), title_tf.getText());
                if (!subject.getText().equals(majles.getSubject()))
                    db.updateMajSubject(id,subject.getText());
                if(!place_tf.getText().equals(majles.getPlace()))
                    db.updateMajPlace(id, place_tf.getText());
                if(!shCombo.getValue().toString().equals(majles.getSheikh()))
                    db.updateMajsheikh(id, shCombo.getValue().toString());
            } catch (SQLException ex) {
            }
        }
        else
             nav.navTowith_udb(root, nav.fxmladminmain, db.getUser(), db); 
        
    }
    @FXML
    private void select(MouseEvent event) {
    }
    
}
