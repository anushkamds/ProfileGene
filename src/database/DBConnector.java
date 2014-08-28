package database; /**
 * Created by Anushka on 2014-08-22.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnector {
    public static void main(String ar[]){
        DBConnector.getConnection();
    }

    public static Connection getConnection(){

        Connection conn = null;
        Properties prop = new Properties();

        String url = null;
        String dbName = null;
        String driver = null;
        String userName = null;
        String password = null;

        //load a properties file
        try {
            prop.load(new FileInputStream("config.properties"));

            url= prop.getProperty("url");
            dbName= prop.getProperty("dbName");
            driver= prop.getProperty("driver");
            userName= prop.getProperty("userName");
            password= prop.getProperty("password");


        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName,userName,password);
            System.out.println("DB Successfully Connected");
            return conn;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }




}

