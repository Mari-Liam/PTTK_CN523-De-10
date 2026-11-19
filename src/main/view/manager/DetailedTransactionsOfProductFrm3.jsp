<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.text.DecimalFormat" %>
        <%@ page import="java.text.DecimalFormatSymbols" %>
            <%@ page import="java.text.SimpleDateFormat" %>
                <%@ page import="model.CounterInvoice3" %>
                    <%@ page import="model.Invoice3" %>
                        <%@ page import="model.InvoiceDetail3" %>
                            <%@ page import="model.Manager3" %>
                                <%@ page import="model.OnlineInvoice3" %>
                                    <%@ page import="model.ProductRevenueStat3" %>
                                        <% Manager3 staff=(Manager3) session.getAttribute("staff"); if (staff==null) {
                                            response.sendRedirect(request.getContextPath() + "/user/LoginFrm3.jsp" );
                                            return; } ProductRevenueStat3 productStat=(ProductRevenueStat3)
                                            request.getAttribute("productStat"); Invoice3[] listInvoice=(Invoice3[])
                                            request.getAttribute("listInvoice"); if (productStat==null) {
                                            response.sendRedirect(request.getContextPath()
                                            + "/manager/ProductRevenueStatFrm3.jsp" ); return; } SimpleDateFormat
                                            dateFmt=new SimpleDateFormat("dd/MM/yyyy"); SimpleDateFormat dtFmt=new
                                            SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); DecimalFormatSymbols symbols=new
                                            DecimalFormatSymbols(); symbols.setGroupingSeparator('.'); DecimalFormat
                                            df=new DecimalFormat("#,###", symbols); %>
                                            <!DOCTYPE html>
                                            <html lang="vi">

                                            <head>
                                                <meta charset="UTF-8">
                                                <title>Chi tiết Các lần giao dịch</title>
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
                                                        max-width: 1060px;
                                                        margin: 32px auto;
                                                        background: #fff;
                                                        padding: 24px 30px 32px 30px;
                                                        border-radius: 8px;
                                                        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.06);
                                                    }

                                                    .top {
                                                        display: flex;
                                                        justify-content: space-between;
                                                        align-items: center;
                                                        margin-bottom: 16px;
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
                                                        margin: 0 0 18px 0;
                                                        font-size: 22px;
                                                    }

                                                    .info-grid {
                                                        display: grid;
                                                        grid-template-columns: 1fr 1fr;
                                                        gap: 8px 24px;
                                                        max-width: 680px;
                                                        margin: 0 auto 20px auto;
                                                        background: #f8fafc;
                                                        border: 1px solid #e2e8f0;
                                                        border-radius: 5px;
                                                        padding: 12px 18px;
                                                        font-size: 14px;
                                                    }

                                                    .table-wrap {
                                                        display: flex;
                                                        align-items: center;
                                                        gap: 8px;
                                                    }

                                                    .arrow {
                                                        background: #f1f5f9;
                                                        border: 1px solid #cbd5e1;
                                                        border-radius: 4px;
                                                        padding: 7px 10px;
                                                        font-weight: bold;
                                                        cursor: pointer;
                                                    }

                                                    table {
                                                        width: 100%;
                                                        border-collapse: collapse;
                                                        font-size: 13.5px;
                                                    }

                                                    th,
                                                    td {
                                                        border: 1px solid #cbd5e1;
                                                        padding: 8px 9px;
                                                    }

                                                    th {
                                                        background: #e8f3fc;
                                                        color: #0b2545;
                                                        text-align: center;
                                                    }

                                                    tfoot td {
                                                        background: #f8fafc;
                                                        font-weight: bold;
                                                    }

                                                    .center {
                                                        text-align: center;
                                                    }

                                                    .right {
                                                        text-align: right;
                                                    }
                                                </style>
                                            </head>

                                            <body>
                                                <div class="system-banner">Hệ thống Quản lí Siêu thị Điện máy Maririn
                                                </div>

                                                <div class="box">
                                                    <div class="top">
                                                        <a id="btnReturn" class="btn-back"
                                                            href="${pageContext.request.contextPath}/manager/ProductRevenueStatFrm3.jsp">Quay
                                                            lại</a>
                                                        <span style="font-size:13px; color:#6b7280;">Quản lí: <strong>
                                                                <%= staff.getName() !=null ?
                                                                    staff.getName().getFullName() : staff.getUsername()
                                                                    %>
                                                            </strong></span>
                                                    </div>

                                                    <h2>Chi tiết Các lần giao dịch</h2>

                                                    <div class="info-grid">
                                                        <div>Tên sản phẩm: <strong id="outProductName">
                                                                <%= productStat.getName() %>
                                                            </strong></div>
                                                        <div>Mã mặt hàng: <strong id="outProductID">
                                                                <%= productStat.getId() %>
                                                            </strong></div>
                                                        <div>Ngày bắt đầu: <strong id="outStartDate">
                                                                <%= productStat.getStartDate() !=null ?
                                                                    dateFmt.format(productStat.getStartDate()) : "" %>
                                                            </strong></div>
                                                        <div>Ngày kết thúc: <strong id="outEndDate">
                                                                <%= productStat.getEndDate() !=null ?
                                                                    dateFmt.format(productStat.getEndDate()) : "" %>
                                                            </strong></div>
                                                    </div>

                                                    <div class="table-wrap">
                                                        <button type="button" class="arrow"
                                                            onclick="changePage(-1)">&lt;</button>
                                                        <table id="tblDetailedTransactions">
                                                            <thead>
                                                                <tr>
                                                                    <th>STT</th>
                                                                    <th>Mã hóa đơn</th>
                                                                    <th>Ngày giờ</th>
                                                                    <th>NV bán hàng</th>
                                                                    <th>NV kho</th>
                                                                    <th>Khách hàng</th>
                                                                    <th>Số lượng đã bán</th>
                                                                    <th>Đơn giá (VNĐ)</th>
                                                                    <th>Thành tiền (VNĐ)</th>
                                                                    <th>Tổng GT đơn (VNĐ)</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <% if (listInvoice==null || listInvoice.length==0) { %>
                                                                    <tr>
                                                                        <td colspan="10" class="center"
                                                                            style="color:#6b7280; padding:20px;">Không
                                                                            có lần giao dịch nào trong khoảng thời gian
                                                                            đã chọn.</td>
                                                                    </tr>
                                                                    <% } else { for (int i=0; i < listInvoice.length;
                                                                        i++) { Invoice3 inv=listInvoice[i];
                                                                        InvoiceDetail3 detail=(inv.getInvoiceDetails()
                                                                        !=null && inv.getInvoiceDetails().length> 0)
                                                                        ? inv.getInvoiceDetails()[0] : null;

                                                                        String salesStaffDisplay = "";
                                                                        String warehouseStaffDisplay = "";
                                                                        if (inv instanceof CounterInvoice3) {
                                                                            CounterInvoice3 ci = (CounterInvoice3) inv;
                                                                            if (ci.getSalesStaff() != null && ci.getSalesStaff().getName() != null) {
                                                                                salesStaffDisplay = ci.getSalesStaff().getName().getFullName();
                                                                            }
                                                                        } else if (inv instanceof OnlineInvoice3) {
                                                                            OnlineInvoice3 oi = (OnlineInvoice3) inv;
                                                                            if (oi.getWarehouseStaff() != null && oi.getWarehouseStaff().getName() != null) {
                                                                                warehouseStaffDisplay = oi.getWarehouseStaff().getName().getFullName();
                                                                            }
                                                                        }

                                                                        String customerDisplay = "Khách vãng lai";
                                                                        if (inv.getCustomer() != null &&
                                                                        inv.getCustomer().getName() != null) {
                                                                        customerDisplay =
                                                                        inv.getCustomer().getName().getFullName();
                                                                        }
                                                                        %>
                                                                        <tr class="data-row">
                                                                            <td class="center">
                                                                                <%= i + 1 %>
                                                                            </td>
                                                                            <td class="center">
                                                                                <%= inv.getId() %>
                                                                            </td>
                                                                            <td class="center">
                                                                                <%= inv.getCreatedAt() !=null ?
                                                                                    dtFmt.format(inv.getCreatedAt())
                                                                                    : "" %>
                                                                            </td>
                                                                            <td>
                                                                                <%= salesStaffDisplay %>
                                                                            </td>
                                                                            <td>
                                                                                <%= warehouseStaffDisplay %>
                                                                            </td>
                                                                            <td>
                                                                                <%= customerDisplay %>
                                                                            </td>
                                                                            <td class="center">
                                                                                <%= detail !=null ? detail.getQuantity()
                                                                                    : 0 %>
                                                                            </td>
                                                                            <td class="right">
                                                                                <%= detail !=null ?
                                                                                    df.format(Math.round(detail.getListedPrice()))
                                                                                    : "0" %>
                                                                            </td>
                                                                            <td class="right">
                                                                                <%= detail !=null ?
                                                                                    df.format(Math.round(detail.getTotal()))
                                                                                    : "0" %>
                                                                            </td>
                                                                            <td class="right">
                                                                                <%= df.format(Math.round(inv.getTotal()))
                                                                                    %>
                                                                            </td>
                                                                        </tr>
                                                                        <% } } %>
                                                            </tbody>
                                                            <tfoot>
                                                                <tr>
                                                                    <td colspan="6" class="right"><i>Tổng:</i></td>
                                                                    <td id="outTotalSold" class="center"
                                                                        style="color:#084298;">
                                                                        <%= productStat.getTotalSold() %>
                                                                    </td>
                                                                    <td></td>
                                                                    <td id="outTotalRevenue" class="right"
                                                                        style="color:#084298;">
                                                                        <%= df.format(Math.round(productStat.getTotalRevenue()))
                                                                            %>
                                                                    </td>
                                                                    <td></td>
                                                                </tr>
                                                            </tfoot>
                                                        </table>
                                                        <button type="button" class="arrow"
                                                            onclick="changePage(1)">&gt;</button>
                                                    </div>
                                                </div>

                                                <script>
                                                    var currentPage = 1, pageSize = 5;
                                                    function renderPage() {
                                                        var rows = document.querySelectorAll('#tblDetailedTransactions tbody tr.data-row');
                                                        if (!rows.length) return;
                                                        var totalPages = Math.ceil(rows.length / pageSize);
                                                        if (currentPage < 1) currentPage = 1;
                                                        if (currentPage > totalPages) currentPage = totalPages;
                                                        for (var i = 0; i < rows.length; i++) {
                                                            rows[i].style.display = (i >= (currentPage - 1) * pageSize && i < currentPage * pageSize) ? '' : 'none';
                                                        }
                                                    }
                                                    function changePage(d) { currentPage += d; renderPage(); }
                                                    window.addEventListener('DOMContentLoaded', renderPage);
                                                </script>
                                            </body>

                                            </html>