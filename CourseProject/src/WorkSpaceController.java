import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

public class WorkSpaceController {

    @FXML
    private DatePicker dateStart;

    @FXML
    private DatePicker dateEnd;

    @FXML
    private LineChart<?, ?> graphPane;

    @FXML
    private ChoiceBox<?> ChoiseBox;

    @FXML
    private Button GenerateBtn;

}