package com.yc.jpaplus.example.enums;

public enum Gender {
    MAIL("男性"), FMAIL("女性");

    private String value;

    Gender(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
