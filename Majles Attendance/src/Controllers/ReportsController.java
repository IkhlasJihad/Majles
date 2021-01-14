/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Commitment;
import Models.DBModel;
import Models.ExportToExcel;
import Models.LecAttendance;
import Models.Navigation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 * @author Ikhlas Jihad
 */
public class ReportsController implements Initializable {

    @FXML
    private ComboBox reportCombo;
    @FXML
    private ComboBox majlesCombo;
    @FXML
    private Label majTitle;
    @FXML
    private AnchorPane getLec_root;
    @FXML
    private ComboBox lec_combo;
    @FXML
    private AnchorPane search_root;
    @FXML
    private Label exist_label1;
    @FXML private Label attends90_label;
    @FXML
    private Button add1;
    @FXML
    private ImageView close_search;
    @FXML
    private TextField search;
    private String maj = ""; 
    private String vol = "";
    private DBModel db ;
    private Navigation nav = new Navigation();
    @FXML
    private ImageView select;
    private final String [] arr = {"كشف الطلبة الملتزمين بحضور 90% من المجلس","كشف الحضور لمحاضرة محددة" , 
        "محاضرات المجلس، بحث بالعنوان"};
    @FXML
    private AnchorPane attends90_root;
    @FXML
    private TableView<Commitment> com_table;
    @FXML
    private TableColumn<Commitment, String> com_id;
    @FXML
    private TableColumn<Commitment, String> com_name;
    @FXML
    private TableColumn<Commitment, String> com_count;
    @FXML
    private Label label_lec;
    @FXML
    private Button selectLec1;
    @FXML
    private AnchorPane lecture_root2;
    @FXML
    private TableView<LecAttendance> lectureTable;
    @FXML
    private TextArea lec_details;
    @FXML
    private Label label_lec_id;
    @FXML
    private TableColumn<LecAttendance, String> la_id;
    @FXML
    private TableColumn<LecAttendance, String> la_name;
    @FXML
    private TableColumn<LecAttendance, String> att;
    @FXML
    private AnchorPane root;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maj = (String)(rb.getObject("majles"));
        vol = (String)(rb.getObject("res"));
        db = (DBModel)(rb.getObject("db"));
        majlesCombo.setItems(FXCollections.observableArrayList(db.getMajlesManagedBy(vol)));
        majlesCombo.setValue(maj);
        ArrayList<String> reports = new ArrayList<>(Arrays.asList(arr));
        reportCombo.setItems(FXCollections.observableArrayList(reports)); 
        setCellValues();
        String [] name_place;
        try {
            name_place = db.getMajlesName_Place(maj);
            majTitle.setText(name_place[0]);
        } catch (SQLException ex) {
        }
        
    }

    private void setCellValues(){
        com_id.setCellValueFactory(new PropertyValueFactory<>("Com_id"));
        com_name.setCellValueFactory(new PropertyValueFactory<>("Com_name"));
        com_count.setCellValueFactory(new PropertyValueFactory<>("Com_count"));
        
        la_id.setCellValueFactory(new PropertyValueFactory<>("La_id"));
        la_name.setCellValueFactory(new PropertyValueFactory<>("La_name"));
        att.setCellValueFactory(new PropertyValueFactory<>("Att"));
    }
    @FXML
    private void onLecClose(MouseEvent event) {
        getLec_root.setVisible(false);
    }

    @FXML
    private void onSearchClose(MouseEvent event) {
        search_root.setVisible(false);
    }

    @FXML
    private void clearReport(KeyEvent event) {
    }

    @FXML
    private void onMajChanged(ActionEvent event) {
        if(majlesCombo.getValue()!= null)
             try {
                maj = majlesCombo.getValue().toString();
                majTitle.setText( db.getMjlesName(maj));
        } catch (SQLException ex) {
        }
    }
    private void hideOthers(AnchorPane a1,AnchorPane a2){
        a1.setVisible(false);
        a2.setVisible(false);
    }
    private void hideAll(){
        search_root.setVisible(false);
        attends90_root.setVisible(false);
        lecture_root2.setVisible(false);
        getLec_root.setVisible(false);
        search_root.setVisible(false);
    }
    List<Commitment> whoAttends90PList ;
    @FXML
    private void onSelect(MouseEvent event) {
        hideAll();
        try {
            if(!maj.equals("") & reportCombo.getValue()!=null){
                String rep = reportCombo.getValue().toString();
                if(rep.equals(arr[0])){
                    //the most commitment
                    hideOthers(lecture_root2,search_root);
                    attends90_root.setVisible(true);
                    whoAttends90PList = db.getWhoAttends90P(maj);
                    com_table.setItems(FXCollections.observableArrayList(whoAttends90PList));
                } 
                else if(rep.equals(arr[1])){
                    hideOthers(attends90_root,search_root);
                    getLec_root.setVisible(true);
                    lec_combo.setItems(FXCollections.observableArrayList(db.getLectures(maj)));
                } else /*if (rep.equals(arr[2]))*/{
                    hideOthers(attends90_root, lecture_root2);
                    search_root.setVisible(true);
                }
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    String lecSelected = "";
    String lecTitle = "";
    @FXML
    private void onSelectLec1(MouseEvent event) {
        if(lec_combo.getValue()!=null){
            lecSelected = lec_combo.getValue().toString();
            
            getLec_root.setVisible(false);
            viewLec(); 
        }
    }

    @FXML
    private void changeLec(MouseEvent event) {
        if(event.getClickCount() == 2){
            lecture_root2.setVisible(false);
            getLec_root.setVisible(true);
        }
    }
    
    private void viewLec(){
        try {
            lectureTable.setItems(FXCollections.observableArrayList(db.getLectureReport(lecSelected, maj)));
            lecTitle = db.getLectureTitle(lecSelected, maj);
            label_lec_id.setText(lecSelected);
            lec_details.setText("  " + lecTitle  + "\n" + "عدد الحضور: " + db.getHowManyAttends(maj, lecSelected) + "\n"
                +  "نسبة الحضور: " + db.getAttendansePercantage(maj, lecSelected) + "%" );
            lecture_root2.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void onSearch(ActionEvent event) {
        if(!search.getText().equals("")){
            String title = search.getText().trim();
            lecTitle = title;
            try {
                String id = db.getLectureByTitle(maj, title);
                lecSelected = id;
                search_root.setVisible(false);
                viewLec();
            } catch (SQLException ex) {
                exist_label1.setText("يرجى التحقق من العنوان !");
                exist_label1.setTextFill(Color.RED);
            }
        }
    }

    @FXML
    private void exportToExcel(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("حفظ في .. ");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SGF", "*.xlsx"));
        File file = fileChooser.showSaveDialog(null);
        ExportToExcel excelWriter = new ExportToExcel();
        try {
            excelWriter.writeExcel(whoAttends90PList, file.getAbsolutePath());
            attends90_label.setText( "تم بنجاح.");
            attends90_label.setTextFill( Color.GREEN);
        } catch (IOException ex) {
             attends90_label.setText( "حدث خطأ");
              attends90_label.setTextFill( Color.RED);
        }
    }

    @FXML
    private void nav_back(MouseEvent event) {
         nav.navTowithMVDB(root, nav.fxmlvolMain, maj, vol,db);
    }
}
