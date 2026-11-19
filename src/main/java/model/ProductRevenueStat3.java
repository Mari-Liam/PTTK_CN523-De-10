package model;

import java.sql.Date;

public class ProductRevenueStat3 extends Product3 {
    private static final long serialVersionUID = 1L;

    private int totalSold;
    private float totalRevenue;
    private Date startDate;
    private Date endDate;

    public ProductRevenueStat3() {
        super();
    }

    public ProductRevenueStat3(int id, String name, String brand, int totalSold, float totalRevenue,
                               Date startDate, Date endDate) {
        super();
        setId(id);
        setName(name);
        setBrand(brand);
        this.totalSold = totalSold;
        this.totalRevenue = totalRevenue;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }

    public float getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(float totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
