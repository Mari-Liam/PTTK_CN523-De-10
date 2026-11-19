package dao;

import java.sql.Date;
import model.Invoice3;
import model.ProductRevenueStat3;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class InvoiceDAO3Test {
    private static InvoiceDAO3 invoiceDAO;

    @BeforeClass
    public static void setUp() {
        invoiceDAO = new InvoiceDAO3();
    }

    /**
     * Test lấy các lần giao dịch chi tiết của mặt hàng SP001 (id = 1) trong kỳ mẫu
     */
    @Test
    public void testGetTransactionsOfProduct_WithData() {
        ProductRevenueStat3 stat = new ProductRevenueStat3();
        stat.setId(1);
        stat.setStartDate(Date.valueOf("2026-09-10"));
        stat.setEndDate(Date.valueOf("2026-09-19"));

        Invoice3[] invoices = invoiceDAO.getTransactionsOfProduct(stat);
        Assert.assertNotNull("Kết quả hóa đơn không được null", invoices);
        Assert.assertTrue("Sản phẩm 1 phải có giao dịch trong kỳ mẫu", invoices.length >= 2);
        for (Invoice3 inv : invoices) {
            Assert.assertNotNull("Mỗi hóa đơn phải chứa chi tiết sản phẩm", inv.getInvoiceDetails());
            Assert.assertTrue("Phải có ít nhất 1 InvoiceDetail3", inv.getInvoiceDetails().length > 0);
        }
    }

    /**
     * Test lấy chi tiết giao dịch với mặt hàng không bán được trong kỳ -> trả về mảng rỗng
     */
    @Test
    public void testGetTransactionsOfProduct_Empty() {
        ProductRevenueStat3 stat = new ProductRevenueStat3();
        stat.setId(1);
        stat.setStartDate(Date.valueOf("2020-01-01"));
        stat.setEndDate(Date.valueOf("2020-01-10"));

        Invoice3[] invoices = invoiceDAO.getTransactionsOfProduct(stat);
        Assert.assertNotNull(invoices);
        Assert.assertEquals(0, invoices.length);
    }
}
