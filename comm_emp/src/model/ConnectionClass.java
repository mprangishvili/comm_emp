package model;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
    static Connection connection = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://10.3.12.28:5432/postgres", "postgres", "18052010M+m");
//                    (Connection) DriverManager.getConnection("jdbc:mysql://localhost/"+db_name+"?user="+user_name+"&password="+password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
