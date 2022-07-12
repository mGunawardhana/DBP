/*
 *  * Developed by - mGunawardhana
 *  * Contact email - mrgunawardhana27368@gmail.com
 *  * what's app - 071 - 9043372
 */

package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection = null;
    private final Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/IJSE", "root", "1234");
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        return (dbConnection == null) ? dbConnection = new DBConnection() : dbConnection;
    }


    public Connection getConnection() {
        return connection;
    }
}
