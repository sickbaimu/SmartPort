package com.example.lele.smartport.entity;

import java.io.Serializable;

public class Family implements Serializable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Family(String id,String name){
        this.id = id;
        this.name = name;
    }

}
