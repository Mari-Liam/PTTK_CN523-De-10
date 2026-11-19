package model;

import java.sql.Timestamp;

public class CounterInvoice3 extends Invoice3 {
    private static final long serialVersionUID = 1L;

    private SalesStaff3 salesStaff;

    public CounterInvoice3() {
        super();
    }

    public CounterInvoice3(int id, Timestamp createdAt, float totalVAT, float total, String paymentMethod,
                           String paymentStatus, Member3 customer, InvoiceDetail3[] invoiceDetails,
                           SalesStaff3 salesStaff) {
        super(id, createdAt, totalVAT, total, paymentMethod, paymentStatus, customer, invoiceDetails);
        this.salesStaff = salesStaff;
    }

    public SalesStaff3 getSalesStaff() {
        return salesStaff;
    }

    public void setSalesStaff(SalesStaff3 salesStaff) {
        this.salesStaff = salesStaff;
    }
}
