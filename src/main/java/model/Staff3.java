package model;

import java.sql.Date;

public class Staff3 extends User3 {
    private static final long serialVersionUID = 1L;

    private String position;

    public Staff3() {
        super();
    }

    public Staff3(String position) {
        super();
        this.position = position;
    }

    public Staff3(int id, String username, String password, FullName3 name, Date bod, Address3 address,
                  String number, String email, String role, String position) {
        super(id, username, password, name, bod, address, number, email, role);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
