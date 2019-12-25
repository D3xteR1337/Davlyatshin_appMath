public class Main {
    public static void main(String[] args) throws Exception {
        DB_connection dbConnection = new DB_connection();
        LogIn login = new LogIn();
        dbConnection.connect();
        login.launch();
    }
}
