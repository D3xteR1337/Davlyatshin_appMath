package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {
    static double x1, x2, D;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Вычисление корней квадратного уравнения");
        stage.setScene(new Scene(root, 400, 200));
        stage.show();
    }


    public static void main(String[] args) {launch(args);}

    public static void QuadEquationSolution(double quadX, double X, double free){
        D = X * X - 4 * quadX * free;
        if (D >= 0) {
            x1 = (-X + Math.sqrt(D)) / 2 * quadX;
            x2 = (-X - Math.sqrt(D)) / 2 * quadX;
        }else{
            x1 = (-X + Math.sqrt(-D)) / 2 * quadX;
            x2 = (-X - Math.sqrt(-D)) / 2 * quadX;
        }
    }
}