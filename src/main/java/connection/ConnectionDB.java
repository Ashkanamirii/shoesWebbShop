package connection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * Created by Ashkan Amiri
 * Date:  2021-02-11
 * Time:  09:23
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class ConnectionDB {
    private Properties p= new Properties();
    private Connection con;

    public ConnectionDB() throws IOException, SQLException, ClassNotFoundException {
            p.load(new FileInputStream("src/main/resources/setting.properties"));
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(p.getProperty("address"),p.getProperty("name"),p.getProperty("password"));
    }
    public Connection getConnection(){
        return con;
    }
}
