package com.jitendra.logasservice.enums;

import java.util.HashMap;
import java.util.Map;

public enum Level {
	
	INFO(1,"INFO","INFO"),
	DEBUG(2,"DEBUG","DEBUG"),
	ERROR(3,"ERROR","ERROR");
	
	
	private static final Map<Integer, Level> byId = new HashMap<Integer, Level>();
	private static final Map<String, Level> byValue = new HashMap<String, Level>();
	 
	
	static {
		  
	     for (Level e : Level.values() ) {
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
		 for (Level e : Level.values() ) {
			 map.put(e.id, e.value);
		 }
		return map;
	}
	
	public static Level getById(int id) {
	    return byId.get(id);
	 }
	 
	public static Level getByValue(String value) {
		    return byValue.get(value);
	}
	
	private int id;
	
	private String value;
	
	private String name;

	/**
	 * @param id
	 * @param value
	 */
	private Level(int id, String value, String name) {
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
