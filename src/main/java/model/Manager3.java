package model;

import java.sql.Date;

public class Manager3 extends Staff3 {
    private static final long serialVersionUID = 1L;

    public Manager3() {
        super();
    }

    public Manager3(int id, String username, String password, FullName3 name, Date bod, Address3 address,
                    String number, String email, String role, String position) {
        super(id, username, password, name, bod, address, number, email, role, position);
    }
}
