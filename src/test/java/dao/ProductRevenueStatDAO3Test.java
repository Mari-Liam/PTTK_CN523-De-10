package dao;

import java.sql.Date;
import model.ProductRevenueStat3;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductRevenueStatDAO3Test {
    private static ProductRevenueStatDAO3 dao;

    @BeforeClass
    public static void setUp() {
        dao = new ProductRevenueStatDAO3();
    }

    /**
     * Test thống kê mặt hàng theo doanh thu trong khoảng ngày có dữ liệu (10/09/2026 - 19/09/2026)
     */
    @Test
    public void testGetProductRevenueStat_WithData() {
        Date start = Date.valueOf("2026-09-10");
        Date end = Date.valueOf("2026-09-19");

        ProductRevenueStat3[] result = dao.getProductRevenueStat(start, end);
        Assert.assertNotNull("Danh sách thống kê không được null", result);
        Assert.assertTrue("Phải có ít nhất 1 mặt hàng được bán trong kỳ mẫu", result.length > 0);
        for (int i = 1; i < result.length; i++) {
            Assert.assertTrue("Danh sách phải được sắp xếp giảm dần theo doanh thu",
                    result[i - 1].getTotalRevenue() >= result[i].getTotalRevenue());
        }
    }

    /**
     * Test thống kê trong khoảng ngày không có giao dịch nào -> trả về mảng rỗng
     */
    @Test
    public void testGetProductRevenueStat_EmptyRange() {
        Date start = Date.valueOf("2020-01-01");
        Date end = Date.valueOf("2020-01-10");

        ProductRevenueStat3[] result = dao.getProductRevenueStat(start, end);
        Assert.assertNotNull("Danh sách trả về không được null", result);
        Assert.assertEquals("Khoảng thời gian không có hóa đơn phải trả về 0 dòng", 0, result.length);
    }
}
