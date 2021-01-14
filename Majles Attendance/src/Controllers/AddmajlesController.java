/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Book;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ikhlas Jihad
 */
public class AddmajlesController implements Initializable {

    @FXML
    private AnchorPane root;
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
    private ComboBox booksCombo;
    private DBModel db ;
    Navigation nav = new Navigation();
    @FXML
    private AnchorPane addBook_root;
    @FXML
    private TextField b_place;
    @FXML
    private TextField b_title;
    @FXML
    private ComboBox catCombo;
    @FXML
    private TextField authName;
    @FXML
    private TextField b_price;
    @FXML
    private ComboBox authCombo;
    @FXML
    private Label label_bookroot;
    @FXML
    private ComboBox sh_idCombo;
    private String user;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = (DBModel)(rb.getObject("db"));
        user = (String)(rb.getObject("res"));
        try {
            sh_idCombo.setItems(FXCollections.observableArrayList(db.getSheikh()));
            ArrayList<Book> dbBooks = db.getBooks();
            ArrayList<String> list = new ArrayList<>();
            for(Book b: dbBooks)
                list.add(b.toString());
            booksCombo.setItems(FXCollections.observableArrayList(list));
        } catch (SQLException ex) {
        }
    }    

    @FXML
    private void nav_back(ActionEvent event) {
        nav.navTowith_udb(root, nav.fxmladminmain, user, db); 
    }

    @FXML
    private void addMaj(ActionEvent event) {
        if(!title_tf.getText().equals("") & sh_idCombo.getValue() != null & !place_tf.getText().equals("")){
            try {
                if(booksCombo.getValue() != null){
                    String majID = db.insertMajles(title_tf.getText(), sh_idCombo.getValue().toString(), place_tf.getText(),
                             subject.getText(),
                        booksCombo.getValue().toString());
                    db.setElucidates(majID,booksCombo.getValue().toString());
                }else
                    db.insertMajles(title_tf.getText(), sh_idCombo.getValue().toString(), place_tf.getText(),
                            subject.getText(),
                            null);
            } catch (SQLException ex) {
            }
        }
        else
            label_out.setText("يرجى ملء الحقول الإجبارية.");
    }

    @FXML
    private void addBook(MouseEvent event) {
        addBook_root.setVisible(true);
        addBook_root.setFocusTraversable(true);
    }

    @FXML
    private void getBook(MouseEvent event) {
        String auth = "";
        if(!b_title.getText().equals("")){
            try {
                String bid = db.insertBook(b_title.getText(),b_place.getText(),b_price.getText());
                booksCombo.setValue(bid);
                if(catCombo.getValue()!= null) db.setCategory(bid, catCombo.getValue().toString());
                if(!authName.getText().equals("")) {
                   auth = db.insertAuthor(authName.getText());
                   db.setBookAuth(auth,bid);
                }
                else if (authCombo.getValue()!= null)
                     db.setBookAuth(authCombo.getValue().toString(),bid);
                booksCombo.setEditable(false);
            } catch (SQLException ex) {
                label_bookroot.setText("حدث خطأ أثناء الإضافة");
            }
        }
        else
            label_bookroot.setText("العنوان إجباري.");
    }

    @FXML
    private void exitBroot(MouseEvent event) {
        addBook_root.setVisible(false);
    }
    
}
