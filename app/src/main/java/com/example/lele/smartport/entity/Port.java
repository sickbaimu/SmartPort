package com.example.lele.smartport.entity;

import java.io.Serializable;

/**
 * Created by lele on 2019/1/27.
 */

public class Port implements Serializable {
    private String TypeID = "NULL";
    private int TEMP;//温度
    private int HUM;//湿度
    private float PH;//酸碱度
    private int address;//所在位置
    public Port(String TypeID,int address)
    {
        this.TypeID = TypeID;
        this.address = address;
        init();
    }

    public void init(){
        TEMP = 20;
        HUM = 50;
        PH = 7;
    }
    public int getHUM() {
        return HUM;
    }

    public float getPH() {
        return PH;
    }

    public int getTEMP() {
        return TEMP;
    }

    public void setHUM(int HUM) {
        this.HUM = HUM;
    }

    public void setPH(float PH) {
        this.PH = PH;
    }

    public void setTEMP(int TEMP) {
        this.TEMP = TEMP;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getAddress() {
        return address;
    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String typeID) {
        TypeID = typeID;
    }
}

