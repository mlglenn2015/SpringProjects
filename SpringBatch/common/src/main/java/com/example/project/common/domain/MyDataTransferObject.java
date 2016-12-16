package com.example.project.common.domain;

/**
 * Data Transfer Object //TODO.
 *
 * @author mlglenn.
 */
public class MyDataTransferObject {

    private Long id;
    private String property1;
    private String property2;
    private String property3;

    public MyDataTransferObject() {
    }

    public MyDataTransferObject(Long id, String property1, String property2, String property3) {
        this.id = id;
        this.property1 = property1;
        this.property2 = property2;
        this.property3 = property3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public String getProperty3() {
        return property3;
    }

    public void setProperty3(String property3) {
        this.property3 = property3;
    }
}
