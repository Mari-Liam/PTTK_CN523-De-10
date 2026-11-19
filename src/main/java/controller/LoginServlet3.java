package controller;

import dao.UserDAO3;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Manager3;

@WebServlet("/LoginServlet3")
public class LoginServlet3 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO3 userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userDAO = new UserDAO3();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Manager3 manager = userDAO.checkManagerLogin(
            username != null ? username.trim() : "",
            password != null ? password : ""
        );

        if (manager != null) {
            HttpSession session = req.getSession();
            session.setAttribute("staff", manager);
            res.sendRedirect(req.getContextPath() + "/manager/ManagerHomePageFrm3.jsp");
        } else {
            req.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu quản lí không chính xác!");
            req.getRequestDispatcher("/user/LoginFrm3.jsp").forward(req, res);
        }
    }
}
