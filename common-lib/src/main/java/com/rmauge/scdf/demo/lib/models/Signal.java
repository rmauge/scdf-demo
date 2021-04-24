package com.rmauge.scdf.demo.lib.models;

public class Signal {
    private String key;
    private String value;
    private int custId;

    public Signal() {
    }

    public Signal(final String key, final String value, final int custId) {
        this.key = key;
        this.value = value;
        this.custId = custId;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public int getCustId() {
        return custId;
    }
}
