package com.jitendra.logasservice.enums;

import java.util.HashMap;
import java.util.Map;

public enum AdminType {

    SUPERADMIN(1,"SUPERADMIN","SUPERADMIN"),
    ADMINREAD(2,"ADMINREAD","ADMINREAD"),
    ADMIN(3,"ADMIN","ADMIN");


    private static final Map<Integer, AdminType> byId = new HashMap<Integer, AdminType>();
    private static final Map<String, AdminType> byValue = new HashMap<String, AdminType>();


    static {

        for (AdminType e : AdminType.values() ) {
            if (byId.put(e.getId(), e) != null) {
                throw new IllegalArgumentException("duplicate id: " + e.getId());
            }

            if (byValue.put(e.getValue(), e) != null) {
                throw new IllegalArgumentException("duplicate value: " + e.getValue());
            }
        }
    }

    public static Map<Integer, String> getMap(){
        Map<Integer, String> map = new HashMap<>();
        for (AdminType e : AdminType.values() ) {
            map.put(e.id, e.value);
        }
        return map;
    }

    public static AdminType getById(int id) {
        return byId.get(id);
    }

    public static AdminType getByValue(String value) {
        return byValue.get(value);
    }

    private int id;

    private String value;

    private String name;

    /**
     * @param id
     * @param value
     */
    private AdminType(int id, String value, String name) {
        this.id = id;
        this.value = value;
        this.name = name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

}
