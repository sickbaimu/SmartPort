package com.example.lele.smartport.faker;

import com.example.lele.smartport.entity.Family;
import com.example.lele.smartport.entity.Port;
import com.example.lele.smartport.entity.Type;

import java.util.ArrayList;

public class Server {
    static private ArrayList<Port> portlist;
    static private ArrayList<Type> typelist;
    static private ArrayList<Family> familylist;
    int version = 1;
    private String familynames[] = {"蔷薇科","石蒜科","石竹科","兰科"};
    public Server()
    {
        init_list();
        init_data();
    }

    public static int getnum(){
        int cnt = 0;
        for(Port port:portlist){
            if(!port.getTypeID().equals("NULL"))
                cnt++;
        }
        return cnt;
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
        typelist.add(new Type("0001","蓝蔷薇","0"));
        typelist.add(new Type("0002","月季","0"));
        typelist.add(new Type("0003","水仙","1"));
        typelist.add(new Type("0004","康乃馨","2"));
        typelist.add(new Type("0005","兰花","3"));

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

    public static Type getType(String familyid){
        for(Type type:typelist){
            if(type.getFamilyid().equals(familyid))
                return type;
        }
        return null;
    }

    public static ArrayList<Type> getTypelist() {
        return typelist;
    }

    public static ArrayList<Type> getTypelist(String familyid) {
        ArrayList<Type> list = new ArrayList<>();
        for(Type type:typelist)
            if(type.getFamilyid().equals(familyid))
                list.add(type);
        return list;
    }

    public static ArrayList<Port> getPortlist() {
        return portlist;
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
