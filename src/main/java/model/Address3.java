package model;

import java.io.Serializable;

public class Address3 implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String houseNumber;
    private String building;
    private String street;
    private String ward;
    private String district;
    private String city;

    public Address3() {
    }

    public Address3(String houseNumber, String building, String street, String ward, String district, String city) {
        this.houseNumber = houseNumber;
        this.building = building;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
    }

    public Address3(int id, String houseNumber, String building, String street, String ward, String district, String city) {
        this.id = id;
        this.houseNumber = houseNumber;
        this.building = building;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();
        if (houseNumber != null && !houseNumber.isEmpty()) sb.append(houseNumber);
        if (building != null && !building.isEmpty()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(building);
        }
        if (street != null && !street.isEmpty()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(street);
        }
        if (ward != null && !ward.isEmpty()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(ward);
        }
        if (district != null && !district.isEmpty()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(district);
        }
        if (city != null && !city.isEmpty()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(city);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getFullAddress();
    }
}

