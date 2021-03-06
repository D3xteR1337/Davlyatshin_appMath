import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DB_connection {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/Course_DB";
    static final String USER = "postgres";
    static final String PASS = "83926110";
    static private Scanner sc = new Scanner(System.in);
    static Connection connection = null;

    public static void connect() throws SQLException {
        System.out.println("Testing connection to PostgreSQL JDBC");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");


        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

    public static boolean signDB(String fname, String lname, String log, String pass) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM signup WHERE username = '" + log + "'");
        if (resultSet.next()) {
            statement.close();
            resultSet.close();
            return false;
        } else {
            statement.executeUpdate("INSERT INTO signup VALUES('" + fname + "', '" + lname + "', '" + log + "', '" + pass + "')");
            statement.executeUpdate("INSERT INTO logandpass VALUES('" + log + "', '" + pass + "')");
            statement.close();
            resultSet.close();
            return true;
        }
    }

    public static boolean loginDB(String log, String pass) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM logandpass WHERE login = '" + log + "' AND pass = '" + pass + "'");
        if (resultSet.next()) {
            statement.close();
            resultSet.close();
            return true;
        } else {
            statement.close();
            resultSet.close();
            return false;
        }
    }

    public static void addData(ArrayList<TableInfo> data) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            for (TableInfo inf : data) {
                statement.executeUpdate("INSERT INTO simpletable VALUES('" + inf.getYear() + "-" + inf.getMonth() + "-" + inf.getDay()
                        + "', '" + inf.getOpenPrice() + "', '" + inf.getLowPrice() + "', '" + inf.getHighPrice() + "', '" + inf.getClosePrice() + "')");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ObservableList<SimpleInfo> getSimpleData(String name, String dateFrom, String dateTo) {

        ObservableList<SimpleInfo> infoList = FXCollections.observableArrayList();
        String sql = "SELECT dateb, " + name + " FROM simpletable WHERE dateb BETWEEN '" + dateFrom + "' AND '" + dateTo + "' ;";
        try {
            PreparedStatement prSt = connection.prepareStatement(sql);
            ResultSet res = prSt.executeQuery();
            while (res.next()) {
                SimpleInfo obj = new SimpleInfo();
                String[] dateArr = res.getString("dateb").split("-");
                obj.setDate(dateArr[2] + dateArr[1] + dateArr[0]);
                obj.setValue(res.getDouble(name));
                infoList.add(obj);
            }
            prSt.close();
            return infoList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}