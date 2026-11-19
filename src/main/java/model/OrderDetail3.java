package model;

import java.io.Serializable;

public class OrderDetail3 implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Product3 product;
    private int quantity;
    private float listedPrice;
    private float VAT;
    private float total;

    public OrderDetail3() {
    }

    public OrderDetail3(int id, Product3 product, int quantity, float listedPrice, float VAT, float total) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.listedPrice = listedPrice;
        this.VAT = VAT;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product3 getProduct() {
        return product;
    }

    public void setProduct(Product3 product) {
        this.product = product;
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
}
