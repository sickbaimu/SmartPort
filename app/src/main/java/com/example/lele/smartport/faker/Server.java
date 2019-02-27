package com.example.lele.smartport.faker;

import com.example.lele.smartport.entity.Family;
import com.example.lele.smartport.entity.Port;
import com.example.lele.smartport.entity.Type;

import java.util.ArrayList;

public class Server {
    static private ArrayList<Port> portlist;
    static private ArrayList<Type> typelist;
    static private ArrayList<Family> familylist;
    String familynames[] = {"蔷薇科","石蒜科","石竹科","兰科"};
    public Server()
    {
        init_list();
        init_data();
    }

    private void init_list(){
        portlist = new ArrayList<>();
        typelist = new ArrayList<>();
        familylist = new ArrayList<>();
    }
    private void init_data()
    {
        /*初始化类属*/
        for(int i =0;i<familynames.length;i++)
            familylist.add(new Family(String.valueOf(i),familynames[i]));
        /*初始化花种*/
        typelist.add(new Type("0001","蓝蔷薇","1"));
        typelist.add(new Type("0002","月季","1"));
        typelist.add(new Type("0003","水仙","2"));
        typelist.add(new Type("0004","康乃馨","3"));
        typelist.add(new Type("0005","兰花","4"));

        /*初始化花盆*/
        for(int i = 0;i<16;i++)
        portlist.add(new Port("NULL",i));
        FillPort(2,"0001");
        FillPort(3,"0002");
        FillPort(10,"0004");

    }

    public static ArrayList<Family> getFamilylist() {
        return familylist;
    }

    public static void setFamilylist(ArrayList<Family> familylist) {
        Server.familylist = familylist;
    }


    public static ArrayList<Type> getTypelist() {
        return typelist;
    }

    public static void setTypelist(ArrayList<Type> typelist) {
        Server.typelist = typelist;
    }

    public static ArrayList<Port> getPortlist() {
        return portlist;
    }

    public static void setPortlist(ArrayList<Port> portlist) {
        Server.portlist = portlist;
    }

    public static void FillPort(int address,String typeID)
    {
        for(Port port:getPortlist()) {
            if(port.getAddress()==address) {
                port.setTypeID(typeID);
                port.init();
            }
        }
    }

    public static Port getPort(int address) {
        for(Port port:getPortlist()) {
            if(port.getAddress()==address) {
                return port;
            }
        }
        return null;
    }

    public static String TypeIDtoName(String ID)
    {
        for (Type type:getTypelist())
        {
            if(type.getId().equals(ID))
                return type.getName();
        }
        return null;
    }
}