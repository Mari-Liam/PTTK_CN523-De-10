package dao;

import java.sql.Date;
import model.Address3;
import model.FullName3;
import model.Member3;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MemberDAO3Test {
    private static MemberDAO3 memberDAO;

    @BeforeClass
    public static void setUp() {
        memberDAO = new MemberDAO3();
    }

    /**
     * Test kiểm tra tên đăng nhập đã tồn tại trong CSDL
     */
    @Test
    public void testCheckDuplicateUsername_Existing() {
        // Tên đăng nhập 'testuser' đã được chèn ở init.sql
        boolean isDuplicate = memberDAO.checkDuplicateUsername("testuser");
        Assert.assertTrue("Tên đăng nhập 'testuser' phải tồn tại", isDuplicate);
    }

    /**
     * Test kiểm tra tên đăng nhập chưa tồn tại trong CSDL
     */
    @Test
    public void testCheckDuplicateUsername_NonExisting() {
        String randomUsername = "unique_user_" + System.currentTimeMillis();
        boolean isDuplicate = memberDAO.checkDuplicateUsername(randomUsername);
        Assert.assertFalse("Tên đăng nhập ngẫu nhiên không được tồn tại", isDuplicate);
    }

    /**
     * Test kiểm tra số điện thoại đã tồn tại trong CSDL
     */
    @Test
    public void testCheckDuplicateNumber_Existing() {
        // Số điện thoại '0987654321' đã được chèn ở init.sql
        boolean isDuplicate = memberDAO.checkDuplicateNumber("0987654321");
        Assert.assertTrue("Số điện thoại '0987654321' phải tồn tại", isDuplicate);
    }

    /**
     * Test kiểm tra số điện thoại chưa tồn tại trong CSDL
     */
    @Test
    public void testCheckDuplicateNumber_NonExisting() {
        String randomPhone = "09" + (int)(Math.random() * 100000000);
        boolean isDuplicate = memberDAO.checkDuplicateNumber(randomPhone);
        Assert.assertFalse("Số điện thoại ngẫu nhiên không được tồn tại", isDuplicate);
    }

    /**
     * Test thêm thành viên mới thành công
     */
    @Test
    public void testAddMember_Success() {
        long timestamp = System.currentTimeMillis();
        String username = "member_test_" + timestamp;
        String phone = "08" + (timestamp % 100000000);
        String memberCode = "MEM" + (timestamp % 100000000000L);

        FullName3 name = new FullName3("Trần", "Bình", "Thanh");
        Address3 address = new Address3("123", "Toa B", "Nguyen Trai", "Thanh Xuan Bac", "Thanh Xuan", "Ha Noi");
        Date bod = Date.valueOf("2002-05-15");

        Member3 member = new Member3(username, "password123", name, bod, address, phone, "test@ptit.edu.vn", "Member", memberCode);

        boolean result = memberDAO.addMember(member);
        Assert.assertTrue("Thêm thành viên mới phải trả về true", result);
        Assert.assertTrue("ID thành viên phải được tự sinh > 0", member.getId() > 0);
    }
}

