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

    @FXML
    void initialize() {
        signBtn.setOnAction(event -> {
            try {
                if(username.getText().isEmpty() | passField.getText().isEmpty() | fnameField.getText().isEmpty() | lnameField.getText().isEmpty() | repassField.getText().isEmpty()) signFail.setText("Заполните все данные!");
                else {
                    if (passField.getText().equals(repassField.getText())) {
                        if (DB_connection.signDB(fnameField.getText(), lnameField.getText(), username.getText(), passField.getText())) {
                            signFail.setText("Успешная регистрация! Можете войти в приложение.");
                        } else {
                            signFail.setText("Логин уже занят! Попробуйте другой.");
                        }
                    } else {
                        signFail.setText("Пароли не совпадают!");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
