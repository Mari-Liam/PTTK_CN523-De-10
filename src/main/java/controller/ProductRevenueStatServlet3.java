package controller;

import dao.InvoiceDAO3;
import dao.ProductRevenueStatDAO3;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Invoice3;
import model.Manager3;
import model.ProductRevenueStat3;

@WebServlet("/ProductRevenueStatServlet3")
public class ProductRevenueStatServlet3 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductRevenueStatDAO3 productRevenueStatDAO;
    private InvoiceDAO3 invoiceDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.productRevenueStatDAO = new ProductRevenueStatDAO3();
        this.invoiceDAO = new InvoiceDAO3();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        Manager3 staff = (session != null) ? (Manager3) session.getAttribute("staff") : null;
        if (staff == null) {
            res.sendRedirect(req.getContextPath() + "/user/LoginFrm3.jsp");
            return;
        }

        String action = req.getParameter("action");
        if ("detail".equals(action)) {
            viewDetailedTransaction(req, res);
        } else {
            viewProductRevenueStat(req, res);
        }
    }

    // Xử lý Xem thống kê mặt hàng theo doanh thu trong khoảng
    private void viewProductRevenueStat(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String startStr = req.getParameter("inStartDate");
        String endStr = req.getParameter("inEndDate");

        HttpSession session = req.getSession();

        // mở lần đầu chưa truyền tham số ngày
        if (startStr == null && endStr == null) {
            req.getRequestDispatcher("/manager/ProductRevenueStatFrm3.jsp").forward(req, res);
            return;
        }

        req.setAttribute("inStartDate", startStr);
        req.setAttribute("inEndDate", endStr);

        String errorMsg = validateDate(startStr, endStr);
        if (errorMsg != null) {
            req.setAttribute("outMessage", "Thời gian không hợp lệ!");
            req.setAttribute("outDetail", errorMsg);
            req.getRequestDispatcher("/manager/ProductRevenueStatFrm3.jsp").forward(req, res);
            return;
        }

        Date startDate = parseDate(startStr.trim());
        Date endDate = parseDate(endStr.trim());

        ProductRevenueStat3[] listProductStat = productRevenueStatDAO.getProductRevenueStat(startDate, endDate);

        float outTotalRevenue = 0f;
        for (ProductRevenueStat3 stat : listProductStat) {
            outTotalRevenue += stat.getTotalRevenue();
        }

        // Lưu kết quả vào session
        session.setAttribute("listProductStat", listProductStat);
        session.setAttribute("inStartDate", startStr.trim());
        session.setAttribute("inEndDate", endStr.trim());
        session.setAttribute("outTotalRevenue", outTotalRevenue);

        req.setAttribute("listProductStat", listProductStat);
        req.setAttribute("outTotalRevenue", outTotalRevenue);

        req.getRequestDispatcher("/manager/ProductRevenueStatFrm3.jsp").forward(req, res);
    }

    // Xử lý luồng Xem danh sách các lần giao dịch chi tiết của một mặt hàng đã chọn
    private void viewDetailedTransaction(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        String productIdStr = req.getParameter("productId");
        ProductRevenueStat3[] listProductStat = (ProductRevenueStat3[]) session.getAttribute("listProductStat");

        ProductRevenueStat3 selectedStat = null;
        if (productIdStr != null && listProductStat != null) {
            try {
                int productId = Integer.parseInt(productIdStr.trim());
                for (ProductRevenueStat3 stat : listProductStat) {
                    if (stat.getId() == productId) {
                        selectedStat = stat;
                        break;
                    }
                }
            } catch (NumberFormatException ignored) {
            }
        }

        if (selectedStat == null) {
            res.sendRedirect(req.getContextPath() + "/manager/ProductRevenueStatFrm3.jsp");
            return;
        }

        Invoice3[] listInvoice = invoiceDAO.getTransactionsOfProduct(selectedStat);

        req.setAttribute("productStat", selectedStat);
        req.setAttribute("listInvoice", listInvoice);
        req.setAttribute("outStartDate", selectedStat.getStartDate());
        req.setAttribute("outEndDate", selectedStat.getEndDate());
        req.setAttribute("outProductID", selectedStat.getId());
        req.setAttribute("outProductName", selectedStat.getName());
        req.setAttribute("outTotalSold", selectedStat.getTotalSold());
        req.setAttribute("outTotalRevenue", selectedStat.getTotalRevenue());

        req.getRequestDispatcher("/manager/DetailedTransactionsOfProductFrm3.jsp").forward(req, res);
    }

    private String validateDate(String start, String end) {
        if (start == null || start.trim().isEmpty() || end == null || end.trim().isEmpty()) {
            return "Vui lòng chọn đầy đủ Ngày bắt đầu và Ngày kết thúc.";
        }
        Date startDate = parseDate(start.trim());
        Date endDate = parseDate(end.trim());
        if (startDate == null || endDate == null) {
            return "Định dạng ngày không hợp lệ (dd/MM/yyyy). Vui lòng chọn lại trên lịch.";
        }
        if (startDate.after(endDate)) {
            return "Ngày bắt đầu không được diễn ra sau Ngày kết thúc. Vui lòng chọn lại.";
        }
        return null;
    }

    private Date parseDate(String str) {
        try {
            if (str.contains("/")) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                return new Date(sdf.parse(str).getTime());
            }
            return Date.valueOf(str);
        } catch (ParseException | IllegalArgumentException e) {
            return null;
        }
    }
}
