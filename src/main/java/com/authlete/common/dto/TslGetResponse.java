package com.authlete.common.dto;

import java.io.Serializable;

public class TslGetResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String format;

    private String tsl;

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setTsl(String tsl) {
        this.tsl = tsl;
    }

    public String getTsl() {
        return tsl;
    }
 }
