
package Controllers;

import Models.DBModel;
import Models.Lecture;
import Models.Navigation;
import Models.Student;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * @author Ikhlas Jihad
 */
public class EditController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ChoiceBox operation_cb;
    @FXML
    private ChoiceBox goal_cb;
    String maj = ""; 
    String vol = "";
    DBModel db ;
    Navigation nav = new Navigation();
    ArrayList<String> op, goal ; 
    @FXML
    private AnchorPane ds_root;
    @FXML
    private TextField ds_id;
    @FXML
    private Button ds_bt;
    @FXML
    private Label ds_label;
    @FXML
    private Button select;
    @FXML
    private Button root_back;
    @FXML
    private AnchorPane dL_root;
    @FXML
    private TextField dL_id;
    @FXML
    private Button dL_bt;
    @FXML
    private Label dL_label;
    @FXML
    private ImageView clos;
    @FXML
    private ImageView ds_clos1;
    @FXML
    private AnchorPane getLec_id;
    @FXML
    private TextField getlec_idTF;
    @FXML
    private Button editLec;
    @FXML
    private AnchorPane editLec_root;
    @FXML
    private TextField el_title;
    @FXML
    private TextField el_place;
    @FXML
    private TextField el_id;
    @FXML
    private TextField el_maj;
    @FXML
    private TextField el_time;
    @FXML
    private TextField el_date;
    boolean lecChange = false;
    @FXML
    private Label date_validate;
    @FXML
    private Label label_out;
    boolean isDateValid = false;
    @FXML
    private ImageView close_editLed;
    @FXML
    private AnchorPane editStd_root;
    @FXML
    private TextField std_name;
    @FXML
    private TextField es_phone;
    @FXML
    private Label date_validate1;
    @FXML
    private Label label_out1;
    @FXML
    private ComboBox Std_regionCombo;
    @FXML
    private TextField es_id_tf;
    @FXML
    private AnchorPane esID_root;
    @FXML
    private Label es_label;
    @FXML
    private TextField es_id1;
    @FXML
    private TextField es_date1;
    @FXML
    private AnchorPane getMorePhones;
    @FXML
    private TextField gerP_phone;
    @FXML private DialogPane esSaveDialog;
    @FXML private DialogPane eLSaveDialog;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maj = (String)(rb.getObject("majles"));
        vol = (String)(rb.getObject("res"));
        db = (DBModel)(rb.getObject("db"));
        op = new ArrayList<>(Arrays.asList("تعديل", "حذف"));
        goal = new ArrayList<>(Arrays.asList("محاضرة", "طالب"));
        operation_cb.setItems(FXCollections.observableArrayList(op));
        goal_cb.setItems(FXCollections.observableArrayList(goal));
        Button ignore = (Button) esSaveDialog.lookupButton(ButtonType.YES);
        if(ignore != null)
            ignore.setOnAction(e -> { 
               editStd_root.setVisible(false);
            });
        Button ignoreL = (Button) eLSaveDialog.lookupButton(ButtonType.YES);
        if(ignoreL != null)
            ignoreL.setOnAction(e -> { 
               editLec_root.setVisible(false);
            });
        Button save = (Button) esSaveDialog.lookupButton(ButtonType.NO);
        if(save != null)
            save.setOnAction(e -> { 
                saveStdEdits();
                nav.navTo(root, nav.fxmladminmain);
            }); 
        Button saveL = (Button) eLSaveDialog.lookupButton(ButtonType.NO);
        if(saveL != null)
            saveL.setOnAction(e -> { 
                saveLecEdits();
                nav.navTo(root, nav.fxmladminmain);
            }); 
    }    

    @FXML
    private void selectOp(ActionEvent event) {
        if(operation_cb.getValue()!= null & goal_cb.getValue() != null ){
            switch (operation_cb.getValue().toString()){
                case "حذف": 
                    switch(goal_cb.getValue().toString()){
                        case "طالب" : ds_root.setVisible(true); ds_root.setFocusTraversable(true);break;
                        case "محاضرة": dL_root.setVisible(true); dL_root.setFocusTraversable(true);break;
                    }
                    break;
                case "تعديل": 
                    switch(goal_cb.getValue().toString()){
                        case "طالب" : esID_root.setVisible(true);break;
                        case "محاضرة": getLec_id.setVisible(true);
                    }
            break;
            }
        }
    }
    @FXML
    private void nav_back(ActionEvent event) {
        nav.navTowithMVDB(root,nav.fxmlvolMain,maj,vol,db);
    }

    @FXML
    private void onDS_btn(ActionEvent event) { 
        if(!ds_id.getText().equals("")){
            try {
                boolean isJoined = db.isJoined(ds_id.getText(), maj); 
                if(isJoined){
                   db.deletStdFromM(ds_id.getText(), maj); 
                   ds_label.setText("تم الحذف");
                   ds_label.setTextFill(Color.GREEN);
                }
                else{
                    ds_label.setText("الطالب غير موجود في هذا المجلس.");
                    ds_label.setTextFill(Color.RED);
                }
        } catch (SQLException ex) {
        }}
        else{
            ds_label.setText("أدخل رقم الطالب.");
            ds_label.setTextFill(Color.RED);
        }
    }
    @FXML
    private void onDL_btn(ActionEvent event) {
        if(!dL_id.getText().equals("")){
            try {
                boolean contains =   db.containsLecture( maj, dL_id.getText()); 
                if(contains){
                   db.deletLecFromM(dL_id.getText(), maj); 
                   dL_label.setText("تم الحذف");
                   dL_label.setTextFill(Color.GREEN);
                }
                else{
                    dL_label.setText("لا توجد محاضرة بهذا الرقم.");
                    dL_label.setTextFill(Color.RED);
                }
        } catch (SQLException ex) {
        }}
        else{
            dL_label.setText("أدخل رقم المحاضرة.");
            dL_label.setTextFill(Color.RED);
        }
    }

    @FXML
    private void dL_onClos(MouseEvent event) {
        dL_root.setVisible(false);
    }

    @FXML
    private void ds_onClos(MouseEvent event) {
        ds_root.setVisible(false);
    }
    Lecture lecture;
    @FXML
    private void oneditLec(ActionEvent event) {
        if(!getlec_idTF.getText().equals("")){
            try {
                if(db.containsLecture(maj, getlec_idTF.getText())){
                    lecture = db.getLecture(maj,getlec_idTF.getText());
                    el_title.setText(lecture.getTitle());
                    el_date.setText(lecture.getDate());
                    el_place.setText(lecture.getPlace());
                    el_time.setText(lecture.getTime() );
                    el_maj.setText(maj); el_maj.setEditable(false);
                    el_id.setText(lecture.getId());el_id.setEditable(false);
                    getLec_id.setVisible(false);
                    editLec_root.setVisible(true);
                }
            }catch(SQLException ex) {
            }
        }
    }
    
    private boolean LecChanged(){
        return (!el_title.getText().equals(lecture.getTitle()) 
                | (!el_date.getText().equals(lecture.getDate()) & isDateValid)
                | ((el_place.getText() != null )&& !el_place.getText().equals(lecture.getPlace()))
                | !el_time.getText().equals(lecture.getTime()));
    
    }
    private void saveLecEdits(){
        String id = lecture.getId();
        try {
            if(!el_title.getText().equals(lecture.getTitle())){
                db.updateLecTitle(id, el_title.getText());
                lecChange = true;
            }
            if(!el_date.getText().equals(lecture.getDate()) & isDateValid){
                db.updateLecDate(id, el_date.getText());
                lecChange = true;
            }
            if(!el_place.getText().equals(lecture.getPlace())){
                db.updateLecPlace(id, el_place.getText());
                lecChange = true;
            }
            if(!el_time.getText().equals(lecture.getTime())){
                db.updateLecTime(id, el_time.getText());
                lecChange = true;
            }
            editLec_root.setVisible(false);
            
        } catch (SQLException ex) {
        }
    
    }
    @FXML
    private void saveLec(MouseEvent event) {
        if (LecChanged()){
            saveLecEdits();
        }
    }
    
    @FXML
    private void ondateTyped(KeyEvent event) {
        try {
            boolean valid =  db.isDate(el_date.getText());
            if(valid){
                isDateValid = true;
                date_validate.setText("متوفر");
                date_validate.setTextFill(Color.GREEN);
            }
            else{
                isDateValid = false;
                date_validate.setText("غير متوفر");
                date_validate.setTextFill(Color.RED);
            }
        } catch (SQLException ex) {
            label_out.setText("حدث خطأ ما..");
            date_validate.setTextFill(Color.RED);
        }
        
    }

    @FXML
    private void onClose_editLed(MouseEvent event) {
        if(!LecChanged())
            editLec_root.setVisible(false);
        else
           eLSaveDialog.setVisible(true);
    }
    
    @FXML
    private void onClose_editStd(MouseEvent event) {
        if(!checkChanges())
            editStd_root.setVisible(false);
        else
           esSaveDialog.setVisible(true);
    }
    
    @FXML
    private void es_addPhone(MouseEvent event) {
        getMorePhones.requestFocus();
        getMorePhones.setVisible(true);
    }

    @FXML
    private void esID_onClose(MouseEvent event) {
        esID_root.setVisible(false);
    }
    private String phone, date;
    private Student std;
    @FXML
    private void ongetStdtoEdit(ActionEvent event) {
        if(es_id_tf.getText().equals("")) es_label.setText("الحقل فارغ!");
        else {
            try {
                if(db.isJoined(es_id_tf.getText(), maj)){
                    std = db.getStudent(es_id_tf.getText());
                    Std_regionCombo.setItems(FXCollections.observableArrayList(db.getRegions()));
                    es_id1.setText(std.getId());
                    std_name.setText(std.getFullName());
                     date = db. getJoinsIn(std.getId(),maj);
                    es_date1.setText(date);
                    Std_regionCombo.setValue(std.getRegion());
                     phone = db.getFirstPhone(std.getId());
                    es_phone.setText(phone);
                    esID_root.setVisible(false);
                    editStd_root.setVisible(true);
                }
                else
                    es_label.setText("الطالب غير مسجل في المجلس " + maj);
            }catch (SQLException ex) {
            }
        }
    }
    
    @FXML
    private void saveStd(MouseEvent event) {
        saveStdEdits();
    }
    private void saveStdEdits(){
        if(checkChanges()){
            try {
                if (!date.equals(es_date1.getText())){
                    db.updateJoinIn(std.getId(), maj, es_date1.getText());
                }
                if (!phone.equals(es_phone.getText())){
                    db.updateStdPhone(std.getId(), maj, es_phone.getText());
                }
                if (!Std_regionCombo.getValue().toString().equals(std.getRegion())){
                    db.updateRegion(std.getId(),maj);
                }
            }catch (SQLException ex) {
            }
        }
    }
    
    private boolean checkChanges(){
        return (!date.equals(es_date1.getText()) |  
                !phone.equals(es_phone.getText())| 
                !Std_regionCombo.getValue().toString().equals(std.getRegion()));
    }

    @FXML
    private void onaddPhones(ActionEvent event) {
        try {
            db.insertStdPhone(es_id_tf.getText(),gerP_phone.getText());
            getMorePhones.setVisible(false);
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void hideAll(MouseEvent event) {
        dL_root.setVisible(false);
        ds_root.setVisible(false);
        getLec_id.setVisible(false);
        getMorePhones.setVisible(false);
        editLec_root.setVisible(false);
        editStd_root.setVisible(false);
        esID_root.setVisible(false);
    }

    @FXML
    private void onCloseGetMorePhones(MouseEvent event) {
        getMorePhones.setVisible(false);
    }
    @FXML
    private void onDateTyped(KeyEvent event) {
        try {
            boolean valid =  db.isDate(el_date.getText());
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
