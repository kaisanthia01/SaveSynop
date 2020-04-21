package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CheckConnectDatabase {
    Connection connect() throws ClassNotFoundException, SQLException {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "CLIMATE4";
        String password = "CLIMATE4";
        Class.forName(driver); // load Oracle driver
        // แก้ ip host เลือก url ที่ต้องการ
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
}
