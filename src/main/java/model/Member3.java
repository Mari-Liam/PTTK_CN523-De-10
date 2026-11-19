package model;

import java.sql.Date;

public class Member3 extends User3 {
    private static final long serialVersionUID = 1L;

    private String memberCode;

    public Member3() {
        super();
    }

    public Member3(String memberCode) {
        super();
        this.memberCode = memberCode;
    }

    public Member3(String username, String password, FullName3 name, Date bod, Address3 address, 
                   String number, String email, String role, String memberCode) {
        super(username, password, name, bod, address, number, email, role);
        this.memberCode = memberCode;
    }

    public Member3(int id, String username, String password, FullName3 name, Date bod, Address3 address, 
                   String number, String email, String role, String memberCode) {
        super(id, username, password, name, bod, address, number, email, role);
        this.memberCode = memberCode;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }
}

