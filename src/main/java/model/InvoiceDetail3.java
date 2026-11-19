package model;

import java.io.Serializable;

public class InvoiceDetail3 implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int quantity;
    private float listedPrice;
    private float VAT;
    private float total;
    private Product3 product;

    public InvoiceDetail3() {
    }

    public InvoiceDetail3(int id, int quantity, float listedPrice, float VAT, float total, Product3 product) {
        this.id = id;
        this.quantity = quantity;
        this.listedPrice = listedPrice;
        this.VAT = VAT;
        this.total = total;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getListedPrice() {
        return listedPrice;
    }

    public void setListedPrice(float listedPrice) {
        this.listedPrice = listedPrice;
    }

    public float getVAT() {
        return VAT;
    }

    public void setVAT(float VAT) {
        this.VAT = VAT;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Product3 getProduct() {
        return product;
    }

    public void setProduct(Product3 product) {
        this.product = product;
    }
}
