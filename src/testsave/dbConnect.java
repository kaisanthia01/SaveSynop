package testsave;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author illusion
 */
public class dbConnect {
    Connection connect() throws ClassNotFoundException, SQLException {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url_1 = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "CLIMATE4";
        String password = "CLIMATE4";
        Class.forName(driver); // load Oracle driver
        // แก้ ip host เลือก url ที่ต้องการ
        Connection conn = DriverManager.getConnection(url_1, username, password);
        return conn;
    }
}
