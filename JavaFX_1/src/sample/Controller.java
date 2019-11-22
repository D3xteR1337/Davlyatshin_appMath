package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button send;

    @FXML
    private Label SolutionLabel;

    @FXML
    private Label DiscrimLabel;

    @FXML
    private TextField quadX;

    @FXML
    private TextField X;

    @FXML
    private TextField Free;

    @FXML
    void initialize() {
        send.setOnAction(event -> {
            Main.QuadEquationSolution( Double.parseDouble(quadX.getText()), Double.parseDouble(X.getText()), Double.parseDouble(Free.getText()));
            if(Main.D >= 0) {
                SolutionLabel.setText("Корни: х1 = " + Main.x1 + ", x2 = " + Main.x2);
                DiscrimLabel.setText("При D = " + Main.D);
            }else{
                SolutionLabel.setText("Корни: х1 = " + Main.x1 + ", x2 = " + Main.x2);
                DiscrimLabel.setText("При D = " + Main.D);
            }
        });
        assert send != null : "fx:id=\"send\" was not injected: check your FXML file 'sample.fxml'.";
        assert SolutionLabel != null : "fx:id=\"SolutionLabel\" was not injected: check your FXML file 'sample.fxml'.";
        assert DiscrimLabel != null : "fx:id=\"DiscrimLabel\" was not injected: check your FXML file 'sample.fxml'.";
        assert quadX != null : "fx:id=\"quadX\" was not injected: check your FXML file 'sample.fxml'.";
        assert X != null : "fx:id=\"X\" was not injected: check your FXML file 'sample.fxml'.";
        assert Free != null : "fx:id=\"Free\" was not injected: check your FXML file 'sample.fxml'.";

    }
}
