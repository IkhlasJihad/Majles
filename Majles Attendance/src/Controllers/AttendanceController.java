/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DBModel;
import Models.Navigation;
import Models.ReadFromExcel;
import Models.StdAttendance;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.controlsfx.control.textfield.TextFields;
/**
 * FXML Controller class
 *
 * @author Ikhlas Jihad
 */
public class AttendanceController implements Initializable {

    @FXML
    private ComboBox lec_combo;
    @FXML
    private TextField std_id;    
    @FXML
    private ImageView save;
    @FXML
    private ImageView getreport;
    @FXML
    private Label majTitle;
    @FXML
    private AnchorPane report_root;
    private String maj;
    private String vol;
    private DBModel db ;
    @FXML
    private TableView<StdAttendance> attTable;
    @FXML
    private Label name;
    @FXML
    private TableColumn<StdAttendance, String> lec_id;
    @FXML
    private TableColumn<StdAttendance, String> date;
    @FXML
    private TableColumn<StdAttendance, String> attends;
    @FXML
    private Label label_out;
    Navigation nav = new Navigation();
    @FXML
    private AnchorPane root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maj = (String)(rb.getObject("majles"));
        vol = (String)(rb.getObject("res"));
        db = (DBModel)(rb.getObject("db"));
        ArrayList<String> entries = new ArrayList<>();
        
        try {
            String majName = db.getMajlesName_Place(maj)[0];
            majTitle.setText(maj + ", " + majName);
            lec_combo.setItems(FXCollections.observableArrayList(db.getLectures(maj)));
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCellValues();
        attends.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<StdAttendance, String>>() {
                public void handle(TableColumn.CellEditEvent<StdAttendance, String> t) {
                    StdAttendance  c = attTable.getSelectionModel().getSelectedItem();
                    ((StdAttendance) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setAttends(t.getNewValue());
                    attTable.refresh();
                    try {
                       db.updateAttendance(c.getId(),c.getLec_id(),c.getAttends());
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        label_out.setText("حدث خطأ أثناء التعديل");
                    }
                }
            }
        );
        //For autoCompletion
        try {
            entries = db.whoJoins(maj);
        } catch (SQLException ex) {
        }
        TextFields.bindAutoCompletion(std_id,entries);
    }

    private void setCellValues(){
        //instructor
        lec_id.setCellValueFactory(new PropertyValueFactory<>("Lec_id"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        attends.setCellValueFactory(new PropertyValueFactory<>("Attends"));
        attends.setCellFactory(TextFieldTableCell.<StdAttendance>forTableColumn());
    }

    @FXML
    private void onSave(MouseEvent event) {
        if(!std_id.getText().equals("") & lec_combo.getValue() != null){
            try {
                db.insertIntoAttends(std_id.getText(), lec_combo.getValue().toString());
                label_out.setText("تمت إضافة" + std_id.getText());
                label_out.setTextFill(Color.GREEN);
            } catch (SQLException ex) {
                label_out.setText("حدث خطأ" + ex.getMessage());
            }
        }else label_out.setText("املأ الحقول اللازمة.");
    }

    @FXML
    private void ongetreport(MouseEvent event) {
        if(!std_id.getText().equals("")){
            try {
                if(db.isJoined(std_id.getText(), maj)){
                    attTable.setItems(FXCollections.observableArrayList(db.getStdAttendanceReport(maj, std_id.getText())));
                    report_root.setVisible(true);
                }
                else label_out.setText("أدخل رقم الطالب لتسجيله");
            }catch (SQLException ex) {
                label_out.setText("حدث خطأ."+ ex.getMessage());
            }
        }
        else label_out.setText("أدخل رقم الطالب لتسجيله");
            
    }

    @FXML
    private void clearReport(KeyEvent event) {
        label_out.setText("");
        report_root.setVisible(false);
    }

    @FXML
    private void nav_back(MouseEvent event) {
        nav.navTowithMVDB(root, nav.fxmlvolMain, maj, vol,db);
    }

    @FXML
    private void getFromExcel(MouseEvent event) {
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("get File","'.xlsx","'.csv"));
            File f = fc.showOpenDialog(null);
            String path = f.getAbsolutePath();
            if (path != null ) {
                ReadFromExcel obj = new ReadFromExcel();
                obj.readFile(path,db);
                label_out.setText("تمت بنجاح، عرض كشف المحاضرة لمراجعة ذلك");
            }
            else
                label_out.setText("اختر ملفا لتصديره.");
        } catch (Exception ex) {
            label_out.setText("تحقق من المسار");
        }
    } 
    
    
}
