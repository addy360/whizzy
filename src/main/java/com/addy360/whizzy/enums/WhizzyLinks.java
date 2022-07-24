package com.addy360.whizzy.enums;

public enum WhizzyLinks {

    JOBS("/jobs"),
    TENDERS("/tenders");

    private final String endpoint;

    WhizzyLinks(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
