package controller;

import dao.MemberDAO3;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Address3;
import model.FullName3;
import model.Member3;

@WebServlet("/MemberRegisterServlet3")
public class MemberRegisterServlet3 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MemberDAO3 memberDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.memberDAO = new MemberDAO3();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        registerMember(req, res);
    }

    private void registerMember(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // Đọc dữ liệu từ form
        String inName = req.getParameter("inName");
        String inDob = req.getParameter("inDob");
        String inNumber = req.getParameter("inNumber");
        String inAddress = req.getParameter("inAddress");
        String inEmail = req.getParameter("inEmail");
        String inUsername = req.getParameter("inUsername");
        String inPassword = req.getParameter("inPassword");
        String inCfPassword = req.getParameter("inCfPassword");

        // Giữ lại các giá trị đã nhập nếu có lỗi
        req.setAttribute("inName", inName);
        req.setAttribute("inDob", inDob);
        req.setAttribute("inNumber", inNumber);
        req.setAttribute("inAddress", inAddress);
        req.setAttribute("inEmail", inEmail);
        req.setAttribute("inUsername", inUsername);

        // Kiểm tra các trường bắt buộc
        if (isBlank(inName) || isBlank(inDob) || isBlank(inNumber) || isBlank(inAddress)
                || isBlank(inEmail) || isBlank(inUsername) || isBlank(inPassword) || isBlank(inCfPassword)) {
            req.setAttribute("outMessage", "Thông tin chưa chính xác!");
            req.setAttribute("outDetail", "Vui lòng điền đầy đủ tất cả các thông tin bắt buộc (*).");
            req.setAttribute("messageType", "error");
            req.getRequestDispatcher("/customer/MemberRegisterFrm3.jsp").forward(req, res);
            return;
        }

        // Kiểm tra định dạng ngày sinh
        Date bod = parseDate(inDob);
        if (bod == null) {
            req.setAttribute("outMessage", "Thông tin chưa chính xác!");
            req.setAttribute("outDetail",
                    "Ngày sinh không đúng định dạng (dd/MM/yyyy hoặc yyyy-MM-dd). Vui lòng nhập lại.");
            req.setAttribute("messageType", "error");
            req.getRequestDispatcher("/customer/MemberRegisterFrm3.jsp").forward(req, res);
            return;
        }

        // Kiểm tra xác nhận mật khẩu
        if (!inPassword.equals(inCfPassword)) {
            req.setAttribute("outMessage", "Thông tin chưa chính xác!");
            req.setAttribute("outDetail", "Mật khẩu xác nhận không khớp. Vui lòng nhập lại.");
            req.setAttribute("messageType", "error");
            req.getRequestDispatcher("/customer/MemberRegisterFrm3.jsp").forward(req, res);
            return;
        }

        // Yêu cầu MemberDAO3 kiểm tra trùng tên đăng nhập
        boolean isDuplicateUsername = memberDAO.checkDuplicateUsername(inUsername.trim());
        if (isDuplicateUsername) {
            req.setAttribute("outMessage", "Thông tin chưa chính xác!");
            req.setAttribute("outDetail", "Tên đăng nhập đã tồn tại trong hệ thống. Vui lòng chọn tên khác.");
            req.setAttribute("messageType", "error");
            req.getRequestDispatcher("/customer/MemberRegisterFrm3.jsp").forward(req, res);
            return;
        }

        // Yêu cầu MemberDAO3 kiểm tra trùng số điện thoại
        boolean isDuplicateNumber = memberDAO.checkDuplicateNumber(inNumber.trim());
        if (isDuplicateNumber) {
            req.setAttribute("outMessage", "Thông tin chưa chính xác!");
            req.setAttribute("outDetail", "Số điện thoại đã tồn tại trong hệ thống. Vui lòng nhập lại.");
            req.setAttribute("messageType", "error");
            req.getRequestDispatcher("/customer/MemberRegisterFrm3.jsp").forward(req, res);
            return;
        }

        // Bóc tách thông tin họ tên rồi gọi FullName3 đóng gói
        String last = "";
        String middle = "";
        String first = "";
        String[] nameParts = inName.trim().split("\\s+");
        if (nameParts.length == 1) {
            first = nameParts[0];
        } else if (nameParts.length == 2) {
            last = nameParts[0];
            first = nameParts[1];
        } else {
            last = nameParts[0];
            first = nameParts[nameParts.length - 1];
            StringBuilder mid = new StringBuilder();
            for (int i = 1; i < nameParts.length - 1; i++) {
                if (mid.length() > 0)
                    mid.append(" ");
                mid.append(nameParts[i]);
            }
            middle = mid.toString();
        }
        FullName3 fullName = new FullName3(last, first, middle);

        // Bóc tách thông tin địa chỉ, gọi Address3 đóng gói
        String houseNumber = "";
        String building = "";
        String street = "";
        String ward = "";
        String district = "";
        String city = "";
        String[] addrParts = inAddress.trim().split(",");
        for (int i = 0; i < addrParts.length; i++) {
            addrParts[i] = addrParts[i].trim();
        }
        if (addrParts.length >= 4) {
            street = addrParts[0];
            ward = addrParts[1];
            district = addrParts[2];
            city = addrParts[3];
        } else if (addrParts.length == 3) {
            street = addrParts[0];
            district = addrParts[1];
            city = addrParts[2];
        } else if (addrParts.length == 2) {
            street = addrParts[0];
            city = addrParts[1];
        } else {
            street = addrParts[0];
        }
        Address3 address = new Address3(houseNumber, building, street, ward, district, city);

        // Sinh mã thành viên, đóng gói Member3
        String memberCode = "MEM" + new SimpleDateFormat("yyMMddHHmmss").format(new java.util.Date());
        Member3 member = new Member3(
                inUsername.trim(), inPassword, fullName, bod, address,
                inNumber.trim(), inEmail.trim(), "Member", memberCode);

        // Yêu cầu MemberDAO3 lưu thành viên mới vào CSDL
        boolean isSaved = memberDAO.addMember(member);

        // Trả kết quả thông báo lại cho MemberRegisterFrm3.jsp
        if (isSaved) {
            req.setAttribute("outMessage", "Đăng kí thành công!");
            req.setAttribute("outDetail", "Thông tin đăng ký Thành viên của bạn đã được xác nhận.");
            req.setAttribute("messageType", "success");
            req.removeAttribute("inName");
            req.removeAttribute("inDob");
            req.removeAttribute("inNumber");
            req.removeAttribute("inAddress");
            req.removeAttribute("inEmail");
            req.removeAttribute("inUsername");
        } else {
            req.setAttribute("outMessage", "Thông tin chưa chính xác!");
            req.setAttribute("outDetail", "Đăng ký không thành công. Vui lòng kiểm tra lại kết nối hoặc dữ liệu nhập.");
            req.setAttribute("messageType", "error");
        }

        req.getRequestDispatcher("/customer/MemberRegisterFrm3.jsp").forward(req, res);
    }

    private boolean isBlank(String val) {
        return val == null || val.trim().isEmpty();
    }

    private Date parseDate(String dobStr) {
        try {
            String trimmed = dobStr.trim();
            if (trimmed.contains("/")) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                return new Date(sdf.parse(trimmed).getTime());
            } else {
                return Date.valueOf(trimmed);
            }
        } catch (Exception e) {
            return null;
        }
    }
}
