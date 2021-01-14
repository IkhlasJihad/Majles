package Controllers;

import Models.DBModel;
import Models.Navigation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Ikhlas Jihad
 */

public class AddLectureController implements Initializable {
    DBModel db ;
    Navigation nav = new Navigation();
    String maj = "";
    String vol = "";
    @FXML
    private Button close;
    @FXML
    private Button add;
    @FXML
    private TextField title;
    @FXML
    private TextField place;
    @FXML
    private Label label_out;
    @FXML
    private TextField date;
    @FXML
    private TextField time;
    @FXML
    private TextField maj_tf;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField lec_tf;
    @FXML
    private Label date_validate;
    boolean isDateValid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = (DBModel)(rb.getObject("db"));
        maj = (String)(rb.getObject("majles"));
        vol = (String)(rb.getObject("res"));
        try {
            String [] name_place = db.getMajlesName_Place(maj);
            maj_tf.setText(maj + ",  " + name_place[0]);
            place.setText(name_place[1]);
            lec_tf.setPromptText("يمكنك استخدام: " + (db.getMaxLecID(maj) + 1));
            
        } catch (SQLException ex) {
            nav.navTowithMVDB(root, nav.fxmlvolMain, maj, vol,db);
        }
    }    

    @FXML
    private void nav_back(ActionEvent event) {
         nav.navTowithMVDB(root, nav.fxmlvolMain, maj, vol,db);
    }

    @FXML
    private void addLec(ActionEvent event) {
        if(!lec_tf.getText().equals("")){
        try {
            // insertLecture(String maj, String lec, String title, String date, String time)
            db.insertLecture(maj, lec_tf.getText(),
                    title.getText(),
                    isDateValid ? date.getText() : null,
                    time.getText());
            label_out.setText("تمت الإضافة بنجاح"  );
            label_out.setTextFill(Color.GREEN);
        } catch (SQLException ex) {
            label_out.setText("حدث خطأ.");
            label_out.setTextFill(Color.RED);
        }}
        else
            label_out.setText("رقم المحاضرة إجباري");
            label_out.setTextFill(Color.RED);  
        
    }

    @FXML
    private void onDateTyped(KeyEvent event) {
        try {
            boolean valid =  db.isDate(date.getText());
            if(valid){
                isDateValid = true;
                date_validate.setText("Valid");
                date_validate.setTextFill(Color.GREEN);
            }
            else{
                isDateValid = false;
                date_validate.setText("InValid");
                date_validate.setTextFill(Color.RED);
            }
        } catch (SQLException ex) {
            label_out.setText("حدث خطأ ما..");
        }
    }
    
}
