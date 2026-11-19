package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OnlineOrder3 implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Timestamp createdAt;
    private Address3 shippingAddress;
    private String receiverName;
    private String receiverPhone;
    private String paymentMethod;
    private float total;
    private String status;
    private Timestamp approvedAt;
    private String note;
    private OrderDetail3[] orderDetails;
    private WarehouseStaff3 warehouseStaff;
    private Member3 customer;

    public OnlineOrder3() {
    }

    public OnlineOrder3(int id, Timestamp createdAt, Address3 shippingAddress, String receiverName,
                        String receiverPhone, String paymentMethod, float total, String status,
                        Timestamp approvedAt, String note, OrderDetail3[] orderDetails,
                        WarehouseStaff3 warehouseStaff, Member3 customer) {
        this.id = id;
        this.createdAt = createdAt;
        this.shippingAddress = shippingAddress;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.paymentMethod = paymentMethod;
        this.total = total;
        this.status = status;
        this.approvedAt = approvedAt;
        this.note = note;
        this.orderDetails = orderDetails;
        this.warehouseStaff = warehouseStaff;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Address3 getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address3 shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(Timestamp approvedAt) {
        this.approvedAt = approvedAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OrderDetail3[] getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetail3[] orderDetails) {
        this.orderDetails = orderDetails;
    }

    public WarehouseStaff3 getWarehouseStaff() {
        return warehouseStaff;
    }

    public void setWarehouseStaff(WarehouseStaff3 warehouseStaff) {
        this.warehouseStaff = warehouseStaff;
    }

    public Member3 getCustomer() {
        return customer;
    }

    public void setCustomer(Member3 customer) {
        this.customer = customer;
    }
}
