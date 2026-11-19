package model;

import java.io.Serializable;

public class FullName3 implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String last;
    private String first;
    private String middle;

    public FullName3() {
    }

    public FullName3(String last, String first, String middle) {
        this.last = last;
        this.first = first;
        this.middle = middle;
    }

    public FullName3(int id, String last, String first, String middle) {
        this.id = id;
        this.last = last;
        this.first = first;
        this.middle = middle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        if (last != null && !last.isEmpty()) sb.append(last);
        if (middle != null && !middle.isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(middle);
        }
        if (first != null && !first.isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(first);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getFullName();
    }
}

