<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.text.DecimalFormat" %>
        <%@ page import="java.text.DecimalFormatSymbols" %>
            <%@ page import="model.Manager3" %>
                <%@ page import="model.ProductRevenueStat3" %>
                    <% Manager3 staff=(Manager3) session.getAttribute("staff"); if (staff==null) {
                        response.sendRedirect(request.getContextPath() + "/user/LoginFrm3.jsp" ); return; } String
                        inStartDate=(String) request.getAttribute("inStartDate"); if (inStartDate==null)
                        inStartDate=(String) session.getAttribute("inStartDate"); if (inStartDate==null) inStartDate=""
                        ; String inEndDate=(String) request.getAttribute("inEndDate"); if (inEndDate==null)
                        inEndDate=(String) session.getAttribute("inEndDate"); if (inEndDate==null) inEndDate="" ;
                        ProductRevenueStat3[] listProductStat=(ProductRevenueStat3[])
                        request.getAttribute("listProductStat"); if (listProductStat==null &&
                        request.getAttribute("outMessage")==null) { listProductStat=(ProductRevenueStat3[])
                        session.getAttribute("listProductStat"); } Number outTotalRevenue=(Number)
                        request.getAttribute("outTotalRevenue"); if (outTotalRevenue==null &&
                        request.getAttribute("outMessage")==null) { outTotalRevenue=(Number)
                        session.getAttribute("outTotalRevenue"); } DecimalFormatSymbols symbols=new
                        DecimalFormatSymbols(); symbols.setGroupingSeparator('.'); DecimalFormat df=new
                        DecimalFormat("#,###", symbols); %>
                        <!DOCTYPE html>
                        <html lang="vi">

                        <head>
                            <meta charset="UTF-8">
                            <title>Thống kê Doanh thu theo Sản phẩm</title>
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
                                    max-width: 920px;
                                    margin: 32px auto;
                                    background: #fff;
                                    padding: 24px 32px 32px 32px;
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
                                    margin: 0 0 20px 0;
                                    font-size: 22px;
                                }

                                .filter {
                                    display: flex;
                                    justify-content: center;
                                    gap: 28px;
                                    margin-bottom: 20px;
                                    font-size: 14px;
                                    font-weight: bold;
                                }

                                .date-field {
                                    position: relative;
                                    display: inline-block;
                                    width: 140px;
                                }

                                .date-field input[type="text"] {
                                    width: 100%;
                                    padding: 6px 10px;
                                    border: 1px solid #cbd5e1;
                                    border-radius: 4px;
                                    font-size: 14px;
                                    text-align: center;
                                    background: #fff;
                                    box-sizing: border-box;
                                    cursor: pointer;
                                }

                                .date-field input[type="date"] {
                                    position: absolute;
                                    inset: 0;
                                    opacity: 0;
                                    cursor: pointer;
                                    width: 100%;
                                    height: 100%;
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
                                    font-size: 14px;
                                }

                                th,
                                td {
                                    border: 1px solid #cbd5e1;
                                    padding: 8px 10px;
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

                                .select-product {
                                    color: #0b5ed7;
                                    font-weight: bold;
                                    text-decoration: none;
                                }

                                .select-product:hover {
                                    text-decoration: underline;
                                }

                                .btn-row {
                                    text-align: right;
                                    margin-top: 14px;
                                    padding-right: 36px;
                                }

                                .btn-view {
                                    background: #bfe1f6;
                                    color: #0b2545;
                                    border: 1px solid #7eaac7;
                                    border-radius: 4px;
                                    padding: 7px 28px;
                                    font-size: 14px;
                                    font-weight: bold;
                                    cursor: pointer;
                                }

                                .modal {
                                    position: fixed;
                                    inset: 0;
                                    background: rgba(0, 0, 0, 0.35);
                                    display: flex;
                                    justify-content: center;
                                    align-items: center;
                                }

                                .dialog {
                                    background: #fff;
                                    border: 1px solid #94a3b8;
                                    border-radius: 8px;
                                    width: 420px;
                                    max-width: 90%;
                                    padding: 24px;
                                    text-align: center;
                                }
                            </style>
                        </head>

                        <body>
                            <div class="system-banner">Hệ thống Quản lí Siêu thị Điện máy Maririn</div>

                            <div class="box">
                                <div class="top">
                                    <a id="btnReturn" class="btn-back"
                                        href="${pageContext.request.contextPath}/manager/ViewStatFrm3.jsp">Quay lại</a>
                                    <span style="font-size:13px; color:#6b7280;">Quản lí: <strong>
                                            <%= staff.getName() !=null ? staff.getName().getFullName() :
                                                staff.getUsername() %>
                                        </strong></span>
                                </div>

                                <h2>Thống kê Doanh thu theo Sản phẩm</h2>

                                <form action="${pageContext.request.contextPath}/ProductRevenueStatServlet3"
                                    method="GET">
                                    <div class="filter">
                                        <div>
                                            Ngày bắt đầu:
                                            <span class="date-field">
                                                <input type="text" id="inStartDate" name="inStartDate"
                                                    value="<%= inStartDate %>" placeholder="dd/mm/yyyy" readonly>
                                                <input type="date" onchange="syncDate(this, 'inStartDate')"
                                                    onclick="this.showPicker && this.showPicker()">
                                            </span>
                                        </div>
                                        <div>
                                            Ngày kết thúc:
                                            <span class="date-field">
                                                <input type="text" id="inEndDate" name="inEndDate"
                                                    value="<%= inEndDate %>" placeholder="dd/mm/yyyy" readonly>
                                                <input type="date" onchange="syncDate(this, 'inEndDate')"
                                                    onclick="this.showPicker && this.showPicker()">
                                            </span>
                                        </div>
                                    </div>

                                    <div class="table-wrap">
                                        <button type="button" class="arrow" onclick="changePage(-1)">&lt;</button>
                                        <table id="tblProductRevenue">
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Mã mặt hàng</th>
                                                    <th>Tên mặt hàng</th>
                                                    <th>Hãng</th>
                                                    <th>Số lượng đã bán</th>
                                                    <th>Tổng doanh thu (VNĐ)</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% if (listProductStat==null) { %>
                                                    <tr>
                                                        <td colspan="6" class="center"
                                                            style="color:#6b7280; padding:20px;">Vui lòng chọn Ngày bắt
                                                            đầu, Ngày kết thúc và nhấn <strong>Xem</strong>.</td>
                                                    </tr>
                                                    <% } else if (listProductStat.length==0) { %>
                                                        <tr>
                                                            <td colspan="6" class="center"
                                                                style="color:#6b7280; padding:20px;">Không có mặt hàng
                                                                nào được bán trong khoảng thời gian đã chọn.</td>
                                                        </tr>
                                                        <% } else { for (int i=0; i < listProductStat.length; i++) {
                                                            ProductRevenueStat3 item=listProductStat[i]; %>
                                                            <tr class="data-row">
                                                                <td class="center">
                                                                    <%= i + 1 %>
                                                                </td>
                                                                <td class="center">
                                                                    <%= item.getId() %>
                                                                </td>
                                                                <td>
                                                                    <a class="select-product"
                                                                        href="${pageContext.request.contextPath}/ProductRevenueStatServlet3?action=detail&productId=<%= item.getId() %>">
                                                                        <%= item.getName() %>
                                                                    </a>
                                                                </td>
                                                                <td class="center">
                                                                    <%= item.getBrand() %>
                                                                </td>
                                                                <td class="center">
                                                                    <%= item.getTotalSold() %>
                                                                </td>
                                                                <td class="right">
                                                                    <%= df.format(Math.round(item.getTotalRevenue())) %>
                                                                </td>
                                                            </tr>
                                                            <% } } %>
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <td colspan="5" class="right"><i>Tổng:</i></td>
                                                    <td id="outTotalRevenue" class="right" style="color:#084298;">
                                                        <%= outTotalRevenue !=null ?
                                                            df.format(Math.round(outTotalRevenue.doubleValue())) : "0" %>
                                                    </td>
                                                </tr>
                                            </tfoot>
                                        </table>
                                        <button type="button" class="arrow" onclick="changePage(1)">&gt;</button>
                                    </div>

                                    <div class="btn-row">
                                        <button type="submit" id="btnView" class="btn-view">Xem</button>
                                    </div>
                                </form>
                            </div>

                            <% if (request.getAttribute("outMessage") !=null) { %>
                                <div id="outMessage" class="modal">
                                    <div class="dialog">
                                        <div
                                            style="font-size:18px; font-weight:bold; color:#dc2626; margin-bottom:8px;">
                                            ${outMessage}</div>
                                        <div style="font-size:14px; margin-bottom:18px;">${outDetail}</div>
                                        <button type="button" class="btn-view"
                                            onclick="document.getElementById('outMessage').style.display='none'">Đóng</button>
                                    </div>
                                </div>
                                <% } %>

                                    <script>
                                        function syncDate(picker, targetId) {
                                            if (!picker.value) return;
                                            var parts = picker.value.split('-');
                                            document.getElementById(targetId).value = parts[2] + '/' + parts[1] + '/' + parts[0];
                                        }
                                        var currentPage = 1, pageSize = 5;
                                        function renderPage() {
                                            var rows = document.querySelectorAll('#tblProductRevenue tbody tr.data-row');
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