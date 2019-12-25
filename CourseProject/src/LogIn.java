import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LogIn extends Application {
    public static void launch() {
        Application.launch();
    }

    @Override
    public void start(Stage LogInGUI) throws Exception {
        LogInGUI.setTitle("Вход");
        Parent root = FXMLLoader.load(getClass().getResource("LogInGUI.fxml"));
        Scene LogInScene = new Scene(root, 360, 230);
        LogInGUI.setScene(LogInScene);
        LogInGUI.setResizable(false);
        LogInGUI.getIcons().add(new Image("login.png"));
        LogInGUI.show();
    }
}
