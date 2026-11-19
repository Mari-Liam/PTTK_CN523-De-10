<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="model.Manager3" %>
        <% Manager3 staff=(Manager3) session.getAttribute("staff"); if (staff==null) {
            response.sendRedirect(request.getContextPath() + "/user/LoginFrm3.jsp" ); return; } %>
            <!DOCTYPE html>
            <html lang="vi">

            <head>
                <meta charset="UTF-8">
                <title>Trang chủ Quản lí</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        margin: 0;
                        background: #f4f7fb;
                        color: #1f2937;
                    }

                    .system-banner {
                        background: #084298;
                        color: #fff;
                        text-align: left;
                        padding: 14px 28px;
                        font-size: 20px;
                        font-weight: bold;
                    }

                    .box {
                        max-width: 720px;
                        margin: 50px auto;
                        background: #fff;
                        padding: 40px 30px;
                        border-radius: 8px;
                        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.06);
                        text-align: center;
                    }

                    .info {
                        font-size: 14px;
                        color: #4b5563;
                        margin-bottom: 10px;
                    }

                    h2 {
                        color: #084298;
                        margin: 0 0 30px 0;
                        font-size: 22px;
                    }

                    .btn {
                        display: inline-block;
                        width: 420px;
                        max-width: 100%;
                        padding: 11px 20px;
                        background: #bfe1f6;
                        color: #0b2545;
                        text-decoration: none;
                        border: 1px solid #7eaac7;
                        border-radius: 5px;
                        font-size: 15px;
                        font-weight: bold;
                    }

                    .btn:hover {
                        background: #a3d2ef;
                    }
                </style>
            </head>

            <body>
                <div class="system-banner">Hệ thống Quản lí Siêu thị Điện máy Maririn</div>

                <div class="box">
                    <div class="info">Xin chào quản lí: <strong>
                            <%= staff.getName() !=null ? staff.getName().getFullName() : staff.getUsername() %>
                        </strong></div>
                    <h2>Trang chủ Nhân viên quản lí Siêu thị điện máy</h2>
                    <a id="subViewStat" class="btn"
                        href="${pageContext.request.contextPath}/manager/ViewStatFrm3.jsp">Xem báo cáo thống kê</a>
                </div>
            </body>

            </html>