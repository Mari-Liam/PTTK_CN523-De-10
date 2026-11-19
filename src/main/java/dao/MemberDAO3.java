package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Address3;
import model.FullName3;
import model.Member3;

public class MemberDAO3 extends DAO3 {

    public MemberDAO3() {
        super();
    }

    public boolean checkDuplicateUsername(String username) {
        if (con == null)
            return false;
        String sql = "SELECT id FROM tblUser3 WHERE username = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkDuplicateNumber(String number) {
        if (con == null)
            return false;
        String sql = "SELECT id FROM tblUser3 WHERE number = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, number);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addMember(Member3 member) {
        if (con == null)
            return false;
        boolean success = false;
        boolean originalAutoCommit = true;

        String insertFullNameSql = "INSERT INTO tblFullName3 (last, first, middle) VALUES (?, ?, ?)";
        String insertAddressSql = "INSERT INTO tblAddress3 (houseNumber, building, street, ward, district, city) VALUES (?, ?, ?, ?, ?, ?)";
        String insertUserSql = "INSERT INTO tblUser3 (username, password, tblFullName3id, bod, tblAddress3id, number, email, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String insertMemberSql = "INSERT INTO tblMember3 (tblUser3id, memberCode) VALUES (?, ?)";

        try {
            originalAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false);

            // Thêm tblFullName3
            int fullNameId = -1;
            try (PreparedStatement psFullName = con.prepareStatement(insertFullNameSql,
                    Statement.RETURN_GENERATED_KEYS)) {
                FullName3 name = member.getName();
                psFullName.setString(1, name != null ? name.getLast() : "");
                psFullName.setString(2, name != null ? name.getFirst() : "");
                psFullName.setString(3,
                        (name != null && name.getMiddle() != null && !name.getMiddle().isEmpty()) ? name.getMiddle()
                                : null);
                psFullName.executeUpdate();

                try (ResultSet rsKeys = psFullName.getGeneratedKeys()) {
                    if (rsKeys.next()) {
                        fullNameId = rsKeys.getInt(1);
                        if (name != null)
                            name.setId(fullNameId);
                    }
                }
            }

            if (fullNameId == -1) {
                con.rollback();
                return false;
            }

            // Thêm tblAddress3
            int addressId = -1;
            try (PreparedStatement psAddress = con.prepareStatement(insertAddressSql,
                    Statement.RETURN_GENERATED_KEYS)) {
                Address3 addr = member.getAddress();
                psAddress.setString(1,
                        (addr != null && addr.getHouseNumber() != null && !addr.getHouseNumber().isEmpty())
                                ? addr.getHouseNumber()
                                : null);
                psAddress.setString(2,
                        (addr != null && addr.getBuilding() != null && !addr.getBuilding().isEmpty())
                                ? addr.getBuilding()
                                : null);
                psAddress.setString(3, addr != null ? addr.getStreet() : "");
                psAddress.setString(4, addr != null ? addr.getWard() : "");
                psAddress.setString(5, addr != null ? addr.getDistrict() : "");
                psAddress.setString(6, addr != null ? addr.getCity() : "");
                psAddress.executeUpdate();

                try (ResultSet rsKeys = psAddress.getGeneratedKeys()) {
                    if (rsKeys.next()) {
                        addressId = rsKeys.getInt(1);
                        if (addr != null)
                            addr.setId(addressId);
                    }
                }
            }

            if (addressId == -1) {
                con.rollback();
                return false;
            }

            // Thêm tblUser3
            int userId = -1;
            try (PreparedStatement psUser = con.prepareStatement(insertUserSql, Statement.RETURN_GENERATED_KEYS)) {
                psUser.setString(1, member.getUsername());
                psUser.setString(2, member.getPassword());
                psUser.setInt(3, fullNameId);
                psUser.setDate(4, member.getBod());
                psUser.setInt(5, addressId);
                psUser.setString(6, member.getNumber());
                psUser.setString(7, member.getEmail());
                psUser.setString(8, member.getRole() != null ? member.getRole() : "Member");
                psUser.executeUpdate();

                try (ResultSet rsKeys = psUser.getGeneratedKeys()) {
                    if (rsKeys.next()) {
                        userId = rsKeys.getInt(1);
                        member.setId(userId);
                    }
                }
            }

            if (userId == -1) {
                con.rollback();
                return false;
            }

            // Thêm tblMember3
            try (PreparedStatement psMember = con.prepareStatement(insertMemberSql)) {
                psMember.setInt(1, userId);
                psMember.setString(2, member.getMemberCode());
                int rows = psMember.executeUpdate();
                if (rows > 0) {
                    con.commit();
                    success = true;
                } else {
                    con.rollback();
                }
            }

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(originalAutoCommit);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return success;
    }
}
