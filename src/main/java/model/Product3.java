package model;

import java.io.Serializable;

public class Product3 implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String barcode;
    private float listedPrice;
    private float importPrice;
    private float VAT;
    private int inventory;
    private String unit;
    private float warrantyPeriod;
    private String specs;
    private String brand;
    private int manuYear;
    private String origin;
    private String image;
    private String description;
    private boolean status;

    public Product3() {
    }

    public Product3(int id, String name, String barcode, float listedPrice, float importPrice, float VAT,
                    int inventory, String unit, float warrantyPeriod, String specs, String brand,
                    int manuYear, String origin, String image, String description, boolean status) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.listedPrice = listedPrice;
        this.importPrice = importPrice;
        this.VAT = VAT;
        this.inventory = inventory;
        this.unit = unit;
        this.warrantyPeriod = warrantyPeriod;
        this.specs = specs;
        this.brand = brand;
        this.manuYear = manuYear;
        this.origin = origin;
        this.image = image;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public float getListedPrice() {
        return listedPrice;
    }

    public void setListedPrice(float listedPrice) {
        this.listedPrice = listedPrice;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getVAT() {
        return VAT;
    }

    public void setVAT(float VAT) {
        this.VAT = VAT;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(float warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getManuYear() {
        return manuYear;
    }

    public void setManuYear(int manuYear) {
        this.manuYear = manuYear;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
