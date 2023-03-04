package com.user.userops;


public class Phone {

    private String model;
    private String version;
    private String name;

    public Phone(){}

    public Phone(String model, String version, String name) {
        this.model = model;
        this.version = version;
        this.name = name;
    }



    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }







}
