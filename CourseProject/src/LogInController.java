import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
        signBtn.setOnAction(event -> {
            try {
                if(signLog.getText().isEmpty() | signPass.getText().isEmpty()) {
                    signFail.setText("Введите логин и/или пароль!");
                }
                else{
                    if (DB_connection.signDB(signLog.getText(), signPass.getText()) == 1) {
                        signFail.setText("Успешная регистрация! Можете войти в приложение.");
                    } else if (DB_connection.signDB(signLog.getText(), signPass.getText()) == 2) {
                        signFail.setText("Логин уже занят! Попробуйте другой.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}

