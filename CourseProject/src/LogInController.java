import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LogInController {

    @FXML
    private TextField loginLog;

    @FXML
    private TextField loginPass;

    @FXML
    private Button signBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField signLog;

    @FXML
    private TextField signPass;

    @FXML
    private Label loginFail;

    @FXML
    private Label signFail;

    @FXML
    void initialize() {
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    if (loginLog.getText().isEmpty() | loginPass.getText().isEmpty()) {
                        loginFail.setText("Введите логин и/или пароль!");
                    } else if (DB_connection.loginDB(loginLog.getText(), loginPass.getText())) {
                        root = FXMLLoader.load(getClass().getResource("WorkSpace.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root, 1035, 660);
                        stage.setTitle("Осноовное рабочее пространство");
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                    } else loginFail.setText("Неверный логин или пароль!");
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

