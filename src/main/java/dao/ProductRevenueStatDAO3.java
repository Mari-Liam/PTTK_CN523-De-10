package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ProductRevenueStat3;

public class ProductRevenueStatDAO3 extends DAO3 {

    public ProductRevenueStatDAO3() {
        super();
    }

    public ProductRevenueStat3[] getProductRevenueStat(Date start, Date end) {
        List<ProductRevenueStat3> list = new ArrayList<>();
        if (con == null || start == null || end == null) {
            return new ProductRevenueStat3[0];
        }

        String sql = "SELECT p.id, p.name, p.brand, "
                + "SUM(d.quantity) AS totalSold, "
                + "SUM(d.quantity * d.listedPrice * (100.0 + d.VAT) / 100.0) AS totalRevenue "
                + "FROM tblProduct3 p "
                + "INNER JOIN tblInvoiceDetail3 d ON d.tblProduct3id = p.id "
                + "INNER JOIN tblInvoice3 i ON d.tblInvoice3id = i.id "
                + "WHERE DATE(i.createdAt) BETWEEN ? AND ? "
                + "GROUP BY p.id, p.name, p.brand "
                + "ORDER BY totalRevenue DESC";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, start);
            ps.setDate(2, end);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductRevenueStat3 stat = new ProductRevenueStat3(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("brand"),
                            rs.getInt("totalSold"),
                            rs.getFloat("totalRevenue"),
                            start,
                            end);
                    list.add(stat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list.toArray(new ProductRevenueStat3[0]);
    }
}
