package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Invoice3 implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Timestamp createdAt;
    private float totalVAT;
    private float total;
    private String paymentMethod;
    private String paymentStatus;
    private Member3 customer;
    private InvoiceDetail3[] invoiceDetails;

    public Invoice3() {
    }

    public Invoice3(int id, Timestamp createdAt, float totalVAT, float total, String paymentMethod,
                    String paymentStatus, Member3 customer, InvoiceDetail3[] invoiceDetails) {
        this.id = id;
        this.createdAt = createdAt;
        this.totalVAT = totalVAT;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.customer = customer;
        this.invoiceDetails = invoiceDetails;
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

    public float getTotalVAT() {
        return totalVAT;
    }

    public void setTotalVAT(float totalVAT) {
        this.totalVAT = totalVAT;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Member3 getCustomer() {
        return customer;
    }

    public void setCustomer(Member3 customer) {
        this.customer = customer;
    }

    public InvoiceDetail3[] getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(InvoiceDetail3[] invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
}
