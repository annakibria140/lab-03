package com.example.listycity;
import java.io.Serializable;

public class City implements Serializable{

    //city class
    //Serializable implemented to create a bundle (how we pass the data into fragments)
    private String name;
    private String province;

    public City(String name, String province){
        this.name = name;
        this.province = province;
    }


    //getters
    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }


    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
