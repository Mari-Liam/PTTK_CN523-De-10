# HỆ THỐNG QUẢN LÍ SIÊU THỊ ĐIỆN MÁY MARIRIN (QLST)

---

### 1. Cấu trúc thư mục dự án

```text
Code/
├── pom.xml                                   # Cấu hình Maven (Tomcat 9, Servlet 4.0, MySQL Connector, JUnit 5)
├── docker-compose.yml                        # Cấu hình Docker chạy MySQL 8.0 & phpMyAdmin
├── start-db.bat                              # Khởi chạy MySQL qua Docker
├── run-web.bat                               # Biên dịch và khởi chạy Web Server (Tomcat 9 nhúng)
├── run-test.bat                              # Chạy toàn bộ Unit Test (JUnit)
├── sql/
│   └── init.sql                              # Đầy đủ 18 bảng theo ERD + dữ liệu mẫu kiểm thử (80 hóa đơn)
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── model/                        # FullName3, Address3, User3, Member3, Staff3, Manager3, SalesStaff3, WarehouseStaff3, Product3, ProductRevenueStat3, Invoice3, CounterInvoice3, OnlineInvoice3, InvoiceDetail3, OnlineOrder3, OrderDetail3
    │   │   ├── dao/                          # DAO3, MemberDAO3, UserDAO3, ProductRevenueStatDAO3, InvoiceDAO3
    │   │   ├── controller/                   # MemberRegisterServlet3, LoginServlet3, ProductRevenueStatServlet3
    │   │   └── Main.java                     # Khởi chạy máy chủ Tomcat 9 nhúng
    │   └── view/
    │       ├── WEB-INF/web.xml
    │       ├── user/
    │       │   ├── HomePageFrm3.jsp          # Trang chủ Khách hàng
    │       │   └── LoginFrm3.jsp             # Trang đăng nhập Nhân viên quản lí
    │       ├── customer/
    │       │   └── MemberRegisterFrm3.jsp    # Trang đăng kí Thành viên
    │       └── manager/
    │           ├── ManagerHomePageFrm3.jsp   # Trang chủ Nhân viên quản lí
    │           ├── ViewStatFrm3.jsp          # Chọn loại báo cáo thống kê
    │           ├── ProductRevenueStatFrm3.jsp        # Thống kê Doanh thu theo Sản phẩm
    │           └── DetailedTransactionsOfProductFrm3.jsp # Chi tiết Các lần giao dịch của sản phẩm
    └── test/
        └── java/dao/                         # MemberDAO3Test, ProductRevenueStatDAO3Test, InvoiceDAO3Test
```

---

### 2. Hướng dẫn chạy & kiểm thử

#### Bước 1: Khởi tạo CSDL MySQL qua Docker
Chạy file `start-db.bat` hoặc gõ lệnh:
```bash
docker compose up -d
```
*(Nếu trước đó đã từng chạy container cũ và muốn nạp lại `sql/init.sql` mới nhất, chạy `docker compose down -v` rồi `docker compose up -d`.)*

* **phpMyAdmin:** http://localhost:8081 (tài khoản `root` / mật khẩu `root`)

#### Bước 2: Chạy Web Server
Chạy file `run-web.bat` hoặc gõ lệnh:
```bash
mvn compile exec:java -Dexec.mainClass="Main"
```
* **Trang chủ Khách hàng (Module 1 - Đăng kí thành viên):** http://localhost:8080/user/HomePageFrm3.jsp
* **Đăng nhập Quản lí (Module 2 - Xem thống kê mặt hàng theo doanh thu):** http://localhost:8080/user/LoginFrm3.jsp
  * Tài khoản Quản lí mẫu: `manager` / `123456`
  * Khoảng thời gian có dữ liệu mẫu: `02/01/2026 → 19/11/2026`

#### Bước 3: Chạy Kiểm thử Đơn vị (JUnit Test)
Chạy file `run-test.bat` hoặc gõ `mvn test` (khi Docker MySQL đang bật).
