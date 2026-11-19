package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO3 {
    protected static Connection con;

    public DAO3() {
        try {
            if (con == null || con.isClosed()) {
                String dbUrl = "jdbc:mysql://localhost:3306/qlst_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Ho_Chi_Minh&useUnicode=true&characterEncoding=UTF-8&connectionCollation=utf8mb4_unicode_ci";
                String dbClass = "com.mysql.cj.jdbc.Driver";
                Class.forName(dbClass);
                con = DriverManager.getConnection(dbUrl, "root", "root");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Lỗi kết nối CSDL trong DAO3: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
