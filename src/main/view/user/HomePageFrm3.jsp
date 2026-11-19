<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="vi">

    <head>
        <meta charset="UTF-8">
        <title>Trang chủ</title>
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
                max-width: 680px;
                margin: 50px auto;
                background: #fff;
                padding: 40px 30px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.06);
                text-align: center;
            }

            h2 {
                color: #084298;
                margin: 0 0 30px 0;
                font-size: 22px;
            }

            .btn-register {
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

            .btn-register:hover {
                background: #a3d2ef;
            }
        </style>
    </head>

    <body>
        <div class="system-banner">Hệ thống Quản lí Siêu thị Điện máy Maririn</div>

        <div class="box">
            <h2>Trang chủ Siêu thị Điện máy Maririn</h2>
            <a id="subMemberRegister" class="btn-register"
                href="${pageContext.request.contextPath}/customer/MemberRegisterFrm3.jsp">Đăng kí Thành viên</a>
        </div>
    </body>

    </html>