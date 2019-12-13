import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUpController {


        @FXML
        private TextField fnameField;

        @FXML
        private TextField lnameField;

        @FXML
        private TextField username;

        @FXML
        private TextField passField;

        @FXML
        private TextField repassField;

        @FXML
        private Label signFail;

        @FXML
        private Button signBtn;

/*    void initialize() {
        signBtn.setOnAction(event -> {
            try {
                if (signLog.getText().isEmpty() | signPass.getText().isEmpty()) {
                    signFail.setText("Введите логин и/или пароль!");
                } else {
                    if (DB_connection.signDB(signLog.getText(), signPass.getText())) {
                        signFail.setText("Успешная регистрация! Можете войти в приложение.");
                    } else {
                        signFail.setText("Логин уже занят! Попробуйте другой.");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });*/
    }
