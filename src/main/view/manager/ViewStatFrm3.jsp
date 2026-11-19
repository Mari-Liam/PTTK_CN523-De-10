<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="model.Manager3" %>
        <% Manager3 staff=(Manager3) session.getAttribute("staff"); if (staff==null) {
            response.sendRedirect(request.getContextPath() + "/user/LoginFrm3.jsp" ); return; }
            session.removeAttribute("listProductStat"); session.removeAttribute("inStartDate");
            session.removeAttribute("inEndDate"); session.removeAttribute("outTotalRevenue"); %>
            <!DOCTYPE html>
            <html lang="vi">

            <head>
                <meta charset="UTF-8">
                <title>Chọn loại báo cáo thống kê</title>
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
                        margin: 40px auto;
                        background: #fff;
                        padding: 28px 34px 40px 34px;
                        border-radius: 8px;
                        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.06);
                    }

                    .top {
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        margin-bottom: 18px;
                    }

                    .btn-back {
                        background: #e5e7eb;
                        color: #1f2937;
                        text-decoration: none;
                        border: 1px solid #cbd5e1;
                        border-radius: 4px;
                        padding: 6px 14px;
                        font-size: 14px;
                        font-weight: bold;
                    }

                    h2 {
                        color: #084298;
                        text-align: center;
                        margin: 0 0 26px 0;
                        font-size: 22px;
                    }

                    .menu {
                        display: flex;
                        flex-direction: column;
                        align-items: center;
                        gap: 10px;
                    }

                    .btn {
                        display: block;
                        width: 440px;
                        max-width: 100%;
                        padding: 11px 18px;
                        text-align: center;
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

                    .btn-disabled {
                        background: #e2e8f0;
                        color: #64748b;
                        border-color: #cbd5e1;
                        cursor: not-allowed;
                        pointer-events: none;
                    }
                </style>
            </head>

            <body>
                <div class="system-banner">Hệ thống Quản lí Siêu thị Điện máy Maririn</div>

                <div class="box">
                    <div class="top">
                        <a class="btn-back"
                            href="${pageContext.request.contextPath}/manager/ManagerHomePageFrm3.jsp">Quay lại</a>
                        <span style="font-size:13px; color:#6b7280;">Quản lí: <strong>
                                <%= staff.getName() !=null ? staff.getName().getFullName() : staff.getUsername() %>
                            </strong></span>
                    </div>

                    <h2>Chọn loại báo cáo thống kê</h2>
                    <div class="menu">
                        <a id="subProductRevenueStat" class="btn"
                            href="${pageContext.request.contextPath}/manager/ProductRevenueStatFrm3.jsp">Thống kê Doanh
                            thu theo sản phẩm</a>
                        <span id="subSupplierStat" class="btn btn-disabled">Thống kê Nhà cung cấp</span>
                        <span id="subRevenueStat" class="btn btn-disabled">Thống kê Doanh thu</span>
                    </div>
                </div>
            </body>

            </html>