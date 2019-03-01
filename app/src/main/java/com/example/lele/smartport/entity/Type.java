package com.example.lele.smartport.entity;


import java.io.Serializable;

/**
 * Created by lele on 2019/1/26.
 */

public class Type implements Serializable {
    private String id;
    private String name;
    private String familyid;//属
    public Type(String id, String name, String familyid)
    {
        this.id = id;
        this.name = name;
        this.familyid = familyid;

    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyid() {
        return familyid;
    }
}

