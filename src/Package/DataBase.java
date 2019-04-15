package Package;

import java.sql.*;
import java.util.*;

public class DataBase {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1:3307/db";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public List<String> Domain = new ArrayList<>();

    public List<Map<String, String>> query(String sql) {
        Connection conn = null;
        Statement stmt = null;
        List<Map<String, String>> RawData = new ArrayList<Map<String, String>>();
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, String> row = new HashMap<String, String>();
                for (int i = 1; i <= count; i++) {
                    String columnName = metaData.getColumnName(i);
                    if(!this.Domain.contains(columnName))
                        this.Domain.add(columnName);
                    row.put(columnName, rs.getString(columnName));
                }
                RawData.add(row);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return RawData;
    }
}
