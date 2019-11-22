import java.sql.*;
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

    public static short signDB(String log, String pass) throws SQLException {
        boolean is = false;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM logandpass");
        if (log != "" && pass != "") {
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                if (resultSet.getString(1) == log) {
                    is = true;
                }
            }
            if(!is){
                statement.executeUpdate("INSERT INTO logandpass VALUES('" + log + "', '" + pass + "')");
                statement.close();
                resultSet.close();
                return 1;
            }else{return 2;}
        } else {
            statement.close();
            resultSet.close();
            return 0;
        }
    }
}
