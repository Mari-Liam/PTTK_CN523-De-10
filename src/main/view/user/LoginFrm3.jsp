<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập - Hệ thống Quản lí Siêu thị Điện máy Maririn</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; background: #f4f7fb; color: #1f2937; }
        .system-banner { background: #084298; color: #fff; text-align: left; padding: 14px 28px; font-size: 20px; font-weight: bold; }
        .box { max-width: 420px; margin: 50px auto; background: #fff; padding: 32px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.08); }
        h2 { color: #084298; text-align: center; margin: 0 0 22px 0; font-size: 22px; }
        .row { margin-bottom: 14px; }
        label { display: block; font-size: 14px; font-weight: bold; margin-bottom: 5px; }
        input { width: 100%; padding: 8px 10px; border: 1px solid #cbd5e1; border-radius: 4px; box-sizing: border-box; font-size: 14px; }
        .err { background: #fef2f2; border: 1px solid #fecaca; color: #b91c1c; padding: 8px 12px; border-radius: 4px; font-size: 13px; margin-bottom: 14px; text-align: center; }
        .btn { width: 100%; padding: 9px; background: #bfe1f6; border: 1px solid #7eaac7; border-radius: 4px; font-size: 15px; font-weight: bold; color: #0b2545; cursor: pointer; }
        .btn:hover { background: #a3d2ef; }
    </style>
</head>
<body>
    <div class="system-banner">Hệ thống Quản lí Siêu thị Điện máy Maririn</div>

    <div class="box">
        <h2>Đăng nhập Nhân viên quản lí</h2>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="err">${errorMessage}</div>
        <% } %>
        <form action="${pageContext.request.contextPath}/LoginServlet3" method="POST">
            <div class="row">
                <label for="username">Tên đăng nhập <span style="color:#dc2626">*</span></label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="row">
                <label for="password">Mật khẩu <span style="color:#dc2626">*</span></label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn">Đăng nhập</button>
        </form>
    </div>
</body>
</html>
