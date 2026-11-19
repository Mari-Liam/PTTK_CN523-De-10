package model;

import java.io.Serializable;
import java.sql.Date;

public class User3 implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String username;
    private String password;
    private FullName3 name;
    private Date bod;
    private Address3 address;
    private String number;
    private String email;
    private String role;

    public User3() {
    }

    public User3(String username, String password, FullName3 name, Date bod, Address3 address, String number, String email, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.bod = bod;
        this.address = address;
        this.number = number;
        this.email = email;
        this.role = role;
    }

    public User3(int id, String username, String password, FullName3 name, Date bod, Address3 address, String number, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.bod = bod;
        this.address = address;
        this.number = number;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FullName3 getName() {
        return name;
    }

    public void setName(FullName3 name) {
        this.name = name;
    }

    public Date getBod() {
        return bod;
    }

    public void setBod(Date bod) {
        this.bod = bod;
    }

    public Address3 getAddress() {
        return address;
    }

    public void setAddress(Address3 address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

