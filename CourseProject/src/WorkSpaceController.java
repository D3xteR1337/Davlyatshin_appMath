import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;

public class WorkSpaceController {
    ArrayList<TableInfo> data;
    private ObservableList<TableInfo> tableList;
    private ObservableList<SimpleInfo> graphicList;


    @FXML
    private DatePicker dateStart;

    @FXML
    private DatePicker dateEnd;

    @FXML
    private Button downloadBtn;

    @FXML
    private Button createtableBtn;

    @FXML
    private TableView<TableInfo> infoTabView;

    @FXML
    private TableColumn<TableInfo, String> dateColumn;

    @FXML
    private TableColumn<TableInfo, Number> openColumn;

    @FXML
    private TableColumn<TableInfo, Number> highColumn;

    @FXML
    private TableColumn<TableInfo, Number> lowColumn;

    @FXML
    private TableColumn<TableInfo, Number> closeColumn;

    @FXML
    private CategoryAxis xAxis = new CategoryAxis();

    @FXML
    private NumberAxis yAxis = new NumberAxis();

    @FXML
    private LineChart<String, Number> graphPane = new LineChart<String, Number>(xAxis, yAxis);

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button GenerateBtn;

    @FXML
    void initialize() {

        ObservableList<String> typeDataList = FXCollections.observableArrayList(
                "Open", "High", "Low", "Close"
        );
        comboBox.setItems(typeDataList);

        GenerateBtn.setOnAction(event -> {
       //     graphicList =
            graphPane.getData().clear();

            int n = graphicList.size();
            double[] x = new double[n];
            double[] y = new double[n];
            for (int i = 0; i < n; i++) {
                x[i] = i + 1;
                y[i] = graphicList.get(i).getValue();
            }
            Maths.mnk(x, y);
            Maths.polynom2nd(x, y);
            Maths.exponential(x, y);
            System.out.println(Maths.lnA3 + " " + Maths.b3);

            XYChart.Series series = new XYChart.Series();
            series.setName(comboBox.getValue());
            XYChart.Series mnkSeries = new XYChart.Series();
            mnkSeries.setName("least square");
            XYChart.Series polynom2ndSeries = new XYChart.Series();
            mnkSeries.setName("nonlinear graphic");
            XYChart.Series exponentialSeries = new XYChart.Series();
            exponentialSeries.setName("exponential graphic");

            ObservableList<XYChart.Data> graphicData = FXCollections.observableArrayList();
            ObservableList<XYChart.Data> mnkData = FXCollections.observableArrayList();
            ObservableList<XYChart.Data> polynom2ndData = FXCollections.observableArrayList();
            ObservableList<XYChart.Data> exponentialData = FXCollections.observableArrayList();


            for (int i = 0; i < n; i++) {
                graphicData.add(new XYChart.Data(graphicList.get(i).getDate(), graphicList.get(i).getValue()));
                mnkData.add(new XYChart.Data(graphicList.get(i).getDate(), Maths.linearFun(x[i])));
                polynom2ndData.add(new XYChart.Data(graphicList.get(i).getDate(), Maths.nonlinearFun(x[i])));
                exponentialData.add(new XYChart.Data(graphicList.get(i).getDate(), Maths.expFun(x[i])));

            }
            series.setData(graphicData);
            mnkSeries.setData(mnkData);
            polynom2ndSeries.setData(polynom2ndData);
            exponentialSeries.setData(exponentialData);


            graphPane.getData().add(series);
            graphPane.getData().add(mnkSeries);
            graphPane.getData().add(polynom2ndSeries);
            graphPane.getData().add(exponentialSeries);
        });

        createtableBtn.setOnAction(event -> {
            dateColumn.setCellValueFactory(new PropertyValueFactory<TableInfo, String>("date"));
            openColumn.setCellValueFactory(new PropertyValueFactory<TableInfo, Number>("openPrice"));
            highColumn.setCellValueFactory(new PropertyValueFactory<TableInfo, Number>("highPrice"));
            lowColumn.setCellValueFactory(new PropertyValueFactory<TableInfo, Number>("lowPrice"));
            closeColumn.setCellValueFactory(new PropertyValueFactory<TableInfo, Number>("closePrice"));

            infoTabView.setItems(tableList);
        });

        downloadBtn.setOnAction(event -> {
            String dateFrom = dateStart.getValue().toString();
            String dateTo = dateEnd.getValue().toString();
            System.out.println(dateFrom + "\n" + dateTo);
            try {
                FileReaderTable.downloadCSV(dateFrom, dateTo);
                Thread.currentThread().sleep(2000);
                data = FileReaderTable.parseCSV();
                tableList = FXCollections.observableArrayList(data);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}
