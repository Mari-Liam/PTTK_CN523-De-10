<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="vi">

    <head>
        <meta charset="UTF-8">
        <title>Đăng kí Thành viên</title>
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
                max-width: 540px;
                margin: 36px auto;
                background: #fff;
                padding: 30px 34px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.07);
            }

            h2 {
                color: #084298;
                text-align: center;
                margin: 0 0 8px 0;
                font-size: 22px;
            }

            .note {
                text-align: center;
                font-size: 13px;
                color: #6b7280;
                margin-bottom: 20px;
            }

            .star {
                color: #dc2626;
                font-weight: bold;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            td {
                padding: 6px 0;
            }

            td.lbl {
                width: 175px;
                font-size: 14px;
                font-weight: bold;
            }

            input {
                width: 100%;
                padding: 7px 10px;
                border: 1px solid #cbd5e1;
                border-radius: 4px;
                box-sizing: border-box;
                font-size: 14px;
            }

            .btns {
                display: flex;
                justify-content: flex-end;
                gap: 10px;
                margin-top: 14px;
            }

            .btn-cancel {
                background: #e5e7eb;
                color: #1f2937;
                text-decoration: none;
                border: 1px solid #cbd5e1;
                border-radius: 4px;
                padding: 7px 20px;
                font-size: 14px;
                font-weight: bold;
            }

            .btn-submit {
                background: #bfe1f6;
                color: #0b2545;
                border: 1px solid #7eaac7;
                border-radius: 4px;
                padding: 7px 24px;
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
                width: 440px;
                max-width: 90%;
                padding: 24px;
                text-align: center;
            }

            .dialog-title {
                font-size: 18px;
                font-weight: bold;
                margin-bottom: 8px;
            }

            .dialog-title.error {
                color: #dc2626;
            }

            .dialog-title.success {
                color: #15803d;
            }

            .dialog-detail {
                font-size: 14px;
                margin-bottom: 18px;
            }
        </style>
    </head>

    <body>
        <div class="system-banner">Hệ thống Quản lí Siêu thị Điện máy Maririn</div>

        <div class="box">
            <h2>Đăng kí Thành viên</h2>
            <div class="note">Các trường có dấu <span class="star">*</span> là thông tin bắt buộc</div>

            <form action="${pageContext.request.contextPath}/MemberRegisterServlet3" method="POST">
                <table>
                    <tr>
                        <td class="lbl">Họ tên <span class="star">*</span>:</td>
                        <td><input type="text" id="inName" name="inName" value="${inName != null ? inName : ''}"
                                required></td>
                    </tr>
                    <tr>
                        <td class="lbl">Ngày sinh <span class="star">*</span>:</td>
                        <td><input type="text" id="inDob" name="inDob" value="${inDob != null ? inDob : ''}"
                                placeholder="dd/MM/yyyy" required></td>
                    </tr>
                    <tr>
                        <td class="lbl">Số điện thoại <span class="star">*</span>:</td>
                        <td><input type="text" id="inNumber" name="inNumber" value="${inNumber != null ? inNumber : ''}"
                                required></td>
                    </tr>
                    <tr>
                        <td class="lbl">Địa chỉ <span class="star">*</span>:</td>
                        <td><input type="text" id="inAddress" name="inAddress"
                                value="${inAddress != null ? inAddress : ''}"
                                placeholder="Đường, Phường, Quận, Thành phố" required></td>
                    </tr>
                    <tr>
                        <td class="lbl">Email <span class="star">*</span>:</td>
                        <td><input type="text" id="inEmail" name="inEmail" value="${inEmail != null ? inEmail : ''}"
                                required></td>
                    </tr>
                    <tr>
                        <td class="lbl">Tên đăng nhập <span class="star">*</span>:</td>
                        <td><input type="text" id="inUsername" name="inUsername"
                                value="${inUsername != null ? inUsername : ''}" required></td>
                    </tr>
                    <tr>
                        <td class="lbl">Mật khẩu <span class="star">*</span>:</td>
                        <td><input type="password" id="inPassword" name="inPassword" required></td>
                    </tr>
                    <tr>
                        <td class="lbl">Xác nhận mật khẩu <span class="star">*</span>:</td>
                        <td><input type="password" id="inCfPassword" name="inCfPassword" required></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <div class="btns">
                                <a id="btnCancel" class="btn-cancel"
                                    href="${pageContext.request.contextPath}/user/HomePageFrm3.jsp">Hủy</a>
                                <button type="submit" id="subRegister" class="btn-submit">Đăng kí</button>
                            </div>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <% if (request.getAttribute("outMessage") !=null) { %>
            <div id="outMessage" class="modal" data-success="${messageType == 'success'}">
                <div class="dialog">
                    <div class="dialog-title ${messageType}">${outMessage}</div>
                    <div class="dialog-detail">${outDetail}</div>
                    <button type="button" class="btn-submit" onclick="closeDialog()">Đóng</button>
                </div>
            </div>
            <script>
                function closeDialog() {
                    var modal = document.getElementById('outMessage');
                    modal.style.display = 'none';
                    if (modal.getAttribute('data-success') === 'true') {
                        window.location.href = "${pageContext.request.contextPath}/user/HomePageFrm3.jsp";
                    }
                }
            </script>
            <% } %>
    </body>

    </html>