package com.demo.bankingms.enums;

public enum Ordering {
    DESC("DESC"), ASC("ASC");

    private String ordering;

    Ordering(String ordering) {
        this.ordering = ordering;
    }

    public Ordering getOrdering(String ordering){
        if (ordering.equals(DESC)) {
            return DESC;
        }
        else if (ordering.equals(ASC)){
            return ASC;
        }
        else
            throw new IllegalArgumentException();

    }

}
