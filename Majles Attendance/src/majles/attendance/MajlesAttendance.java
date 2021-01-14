
package majles.attendance;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Ikhlas Jihad
 */
public class MajlesAttendance extends Application{

    public static void main(String[] args) {
       launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/start.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("إدارة حضور المجالس"); 
        stage.setResizable(false);
        stage.show();        
    }
    
}
