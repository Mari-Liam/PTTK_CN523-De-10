package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.FullName3;
import model.Manager3;

public class UserDAO3 extends DAO3 {

    public UserDAO3() {
        super();
    }

    /**
     * Xác thực đăng nhập cho Nhân viên quản lí (phục vụ session Manager3 cho Module 2)
     * @param username Tên đăng nhập
     * @param password Mật khẩu
     * @return Đối tượng Manager3 nếu hợp lệ, null nếu sai thông tin
     */
    public Manager3 checkManagerLogin(String username, String password) {
        if (con == null) return null;
        String sql = "SELECT u.id, u.username, u.bod, u.number, u.email, u.role, "
                   + "s.position, fn.id AS fnId, fn.last, fn.middle, fn.first "
                   + "FROM tblUser3 u "
                   + "INNER JOIN tblStaff3 s ON u.id = s.tblUser3id "
                   + "INNER JOIN tblManager3 m ON s.tblUser3id = m.tblStaff3tblUser3id "
                   + "INNER JOIN tblFullName3 fn ON u.tblFullName3id = fn.id "
                   + "WHERE u.username = ? AND u.password = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    FullName3 fullName = new FullName3(
                        rs.getInt("fnId"),
                        rs.getString("last"),
                        rs.getString("first"),
                        rs.getString("middle")
                    );
                    return new Manager3(
                        rs.getInt("id"),
                        rs.getString("username"),
                        "",
                        fullName,
                        rs.getDate("bod"),
                        null,
                        rs.getString("number"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("position")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
