import java.sql.*;
import java.util.Scanner;


public class DB_Uni {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/DB_studying";
    static final String USER = "postgres";
    static final String PASS = "83926110";
    static private Scanner sc = new Scanner(System.in);
    static Connection connection = null;

    public static void main(String[] argv) throws SQLException {
        String name, country, city, adress, phone, email;
        System.out.print("Введите имя: ");
        name = sc.nextLine();
        System.out.print("Введите страну: ");
        country = sc.nextLine();
        System.out.print("Введите город: ");
        city = sc.nextLine();
        System.out.print("Введите адрес: ");
        adress = sc.nextLine();
        System.out.print("Введите номер телефона: ");
        phone = sc.nextLine();
        System.out.print("Введите email: ");
        email = sc.nextLine();



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

        Statement statement = connection.createStatement();

        statement.executeUpdate("INSERT INTO db_in VALUES ('ООО Шуба','Россия','Чебоксары','ул. Петрова, д.4','237-89-08','mex@mail.ru')");
        print_table();
        statement.executeUpdate("INSERT INTO db_in VALUES ('"+(name) +"','"+country+"','"+city+"','"+adress+"','"+phone+"','"+email+"')");
        print_table();
        statement.executeUpdate("UPDATE db_in SET clientname = 'Институт ядерных технологий' WHERE clientname = 'НИИ Атоммашстрой'");
        print_table();
        statement.executeUpdate("DELETE FROM db_in WHERE clientname = 'Левачев Николай'");
        print_table();

        statement.close();
        connection.close();

    }

    public static void print_table() throws SQLException {
        Statement st = connection.createStatement();
        ResultSet rsName = st.executeQuery("SELECT * FROM db_in");
        ResultSetMetaData rsmdName = rsName.getMetaData();

        int columnsNumber = rsmdName.getColumnCount();

        while(rsName.next()){
            for(int i = 1; i <= columnsNumber; i++){
                System.out.print(rsName.getString(i)+"| ");
            }
            System.out.println();
        }
        st.close();
        rsName.close();
    }
}
