package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CounterInvoice3;
import model.FullName3;
import model.Invoice3;
import model.InvoiceDetail3;
import model.Member3;
import model.OnlineInvoice3;
import model.Product3;
import model.ProductRevenueStat3;
import model.SalesStaff3;
import model.WarehouseStaff3;

public class InvoiceDAO3 extends DAO3 {

    public InvoiceDAO3() {
        super();
    }

    /**
     * Lấy danh sách các hóa đơn (lần giao dịch) của 1 mặt hàng trong khoảng thời gian thống kê.
     * Áp dụng tính đa hình: trả về CounterInvoice3 (tại quầy) hoặc OnlineInvoice3 (trực tuyến)
     * bên trong mảng Invoice3[], kèm InvoiceDetail3 tương ứng của mặt hàng đang xem.
     */
    public Invoice3[] getTransactionsOfProduct(ProductRevenueStat3 productStat) {
        List<Invoice3> list = new ArrayList<>();
        if (con == null || productStat == null || productStat.getStartDate() == null || productStat.getEndDate() == null) {
            return new Invoice3[0];
        }

        String sql = "SELECT i.id AS invoiceId, i.createdAt, i.paymentMethod, i.paymentStatus, "
                   + "d.id AS detailId, d.quantity, d.listedPrice, d.VAT, "
                   + "p.id AS productId, p.name AS productName, p.brand AS productBrand, "
                   + "m.tblUser3id AS memberId, m.memberCode, "
                   + "mn.last AS memLast, mn.middle AS memMiddle, mn.first AS memFirst, "
                   + "ci.tblSalesStaff3tblStaff3tblUser3id AS salesStaffId, "
                   + "sn.last AS salesLast, sn.middle AS salesMiddle, sn.first AS salesFirst, "
                   + "oi.tblWarehouseStaff3tblStaff3tblUser3id AS warehouseStaffId, "
                   + "oi.shippingFee, oi.carrier, oi.trackingNumber, oi.shippingStatus, "
                   + "wn.last AS whLast, wn.middle AS whMiddle, wn.first AS whFirst, "
                   + "(SELECT SUM(d2.quantity * d2.listedPrice * (100.0 + d2.VAT) / 100.0) "
                   + " FROM tblInvoiceDetail3 d2 WHERE d2.tblInvoice3id = i.id) AS invoiceTotal "
                   + "FROM tblInvoiceDetail3 d "
                   + "INNER JOIN tblInvoice3 i ON d.tblInvoice3id = i.id "
                   + "INNER JOIN tblProduct3 p ON d.tblProduct3id = p.id "
                   + "LEFT JOIN tblMember3 m ON i.tblMember3tblUser3id = m.tblUser3id "
                   + "LEFT JOIN tblUser3 mu ON m.tblUser3id = mu.id "
                   + "LEFT JOIN tblFullName3 mn ON mu.tblFullName3id = mn.id "
                   + "LEFT JOIN tblCounterInvoice3 ci ON ci.tblInvoice3id = i.id "
                   + "LEFT JOIN tblUser3 su ON ci.tblSalesStaff3tblStaff3tblUser3id = su.id "
                   + "LEFT JOIN tblFullName3 sn ON su.tblFullName3id = sn.id "
                   + "LEFT JOIN tblOnlineInvoice3 oi ON oi.tblInvoice3id = i.id "
                   + "LEFT JOIN tblUser3 wu ON oi.tblWarehouseStaff3tblStaff3tblUser3id = wu.id "
                   + "LEFT JOIN tblFullName3 wn ON wu.tblFullName3id = wn.id "
                   + "WHERE d.tblProduct3id = ? "
                   + "AND DATE(i.createdAt) BETWEEN ? AND ? "
                   + "ORDER BY i.createdAt ASC, i.id ASC";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productStat.getId());
            ps.setDate(2, productStat.getStartDate());
            ps.setDate(3, productStat.getEndDate());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product3 product = new Product3();
                    product.setId(rs.getInt("productId"));
                    product.setName(rs.getString("productName"));
                    product.setBrand(rs.getString("productBrand"));

                    int qty = rs.getInt("quantity");
                    float listedPrice = rs.getFloat("listedPrice");
                    float vat = rs.getFloat("VAT");
                    float detailTotal = (float) (qty * (double) listedPrice * (100.0 + vat) / 100.0);

                    InvoiceDetail3 detail = new InvoiceDetail3(
                        rs.getInt("detailId"),
                        qty,
                        listedPrice,
                        vat,
                        detailTotal,
                        product
                    );
                    InvoiceDetail3[] details = new InvoiceDetail3[] { detail };

                    // Khách hàng thành viên (có thể NULL nếu là khách vãng lai)
                    Member3 customer = null;
                    if (rs.getObject("memberId") != null) {
                        customer = new Member3();
                        customer.setId(rs.getInt("memberId"));
                        customer.setMemberCode(rs.getString("memberCode"));
                        customer.setName(new FullName3(
                            rs.getString("memLast"),
                            rs.getString("memFirst"),
                            rs.getString("memMiddle")
                        ));
                    }

                    int invoiceId = rs.getInt("invoiceId");
                    java.sql.Timestamp createdAt = rs.getTimestamp("createdAt");
                    String paymentMethod = rs.getString("paymentMethod");
                    String paymentStatus = rs.getString("paymentStatus");
                    float invoiceTotal = rs.getFloat("invoiceTotal");

                    Invoice3 invoice;
                    if (rs.getObject("salesStaffId") != null) {
                        SalesStaff3 salesStaff = new SalesStaff3();
                        salesStaff.setId(rs.getInt("salesStaffId"));
                        salesStaff.setName(new FullName3(
                            rs.getString("salesLast"),
                            rs.getString("salesFirst"),
                            rs.getString("salesMiddle")
                        ));
                        invoice = new CounterInvoice3(
                            invoiceId, createdAt, 0f, invoiceTotal, paymentMethod, paymentStatus,
                            customer, details, salesStaff
                        );
                    } else if (rs.getObject("warehouseStaffId") != null) {
                        WarehouseStaff3 whStaff = new WarehouseStaff3();
                        whStaff.setId(rs.getInt("warehouseStaffId"));
                        whStaff.setName(new FullName3(
                            rs.getString("whLast"),
                            rs.getString("whFirst"),
                            rs.getString("whMiddle")
                        ));
                        invoice = new OnlineInvoice3(
                            invoiceId, createdAt, 0f, invoiceTotal, paymentMethod, paymentStatus,
                            customer, details,
                            rs.getFloat("shippingFee"),
                            rs.getString("carrier"),
                            rs.getString("trackingNumber"),
                            rs.getString("shippingStatus"),
                            whStaff, null
                        );
                    } else {
                        invoice = new Invoice3(
                            invoiceId, createdAt, 0f, invoiceTotal, paymentMethod, paymentStatus,
                            customer, details
                        );
                    }

                    list.add(invoice);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list.toArray(new Invoice3[0]);
    }
}
