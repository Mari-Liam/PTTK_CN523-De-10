package model;

import java.sql.Timestamp;

public class OnlineInvoice3 extends Invoice3 {
    private static final long serialVersionUID = 1L;

    private float shippingFee;
    private String carrier;
    private String trackingNumber;
    private String shippingStatus;
    private WarehouseStaff3 warehouseStaff;
    private OnlineOrder3 onlineOrder;

    public OnlineInvoice3() {
        super();
    }

    public OnlineInvoice3(int id, Timestamp createdAt, float totalVAT, float total, String paymentMethod,
                          String paymentStatus, Member3 customer, InvoiceDetail3[] invoiceDetails,
                          float shippingFee, String carrier, String trackingNumber, String shippingStatus,
                          WarehouseStaff3 warehouseStaff, OnlineOrder3 onlineOrder) {
        super(id, createdAt, totalVAT, total, paymentMethod, paymentStatus, customer, invoiceDetails);
        this.shippingFee = shippingFee;
        this.carrier = carrier;
        this.trackingNumber = trackingNumber;
        this.shippingStatus = shippingStatus;
        this.warehouseStaff = warehouseStaff;
        this.onlineOrder = onlineOrder;
    }

    public float getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(float shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public WarehouseStaff3 getWarehouseStaff() {
        return warehouseStaff;
    }

    public void setWarehouseStaff(WarehouseStaff3 warehouseStaff) {
        this.warehouseStaff = warehouseStaff;
    }

    public OnlineOrder3 getOnlineOrder() {
        return onlineOrder;
    }

    public void setOnlineOrder(OnlineOrder3 onlineOrder) {
        this.onlineOrder = onlineOrder;
    }
}
