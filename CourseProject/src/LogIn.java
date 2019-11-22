import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static javafx.fxml.FXMLLoader.load;

public class LogIn extends Application {
    public static void launch() {
        Application.launch();
    }

    @Override
    public void start(Stage LogInGUI) throws Exception {
        LogInGUI.setTitle("Вход");
        Parent root = FXMLLoader.load(getClass().getResource("LogInGUI.fxml"));
        Scene LogInScene = new Scene(root);
        LogInGUI.setScene(LogInScene);
        LogInGUI.setMinWidth(490);
        LogInGUI.setMaxWidth(490);
        LogInGUI.setMinHeight(240);
        LogInGUI.setMaxHeight(240);
        LogInGUI.show();
    }
}
