package com.api;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Place {
	@Id
	private String name;
	
	private String info;
	private String image;
	private int radius;
	private Coordinate position;
	private Object[] media;
	
	public Place(){};
	
	//TODO: Remove
	public Place(String name){
		this.name = name;
	}
	public Place(String name, String info, String image, int radius, Coordinate position, Object[] media){
		this.name = name;
		this.info = info;
		this.image = image;
		this.radius = radius;
		this.position = position;
		this.media = media;
	}
	
	/***************************
	 * Getters
	 **************************/
	public String getName(){
		return name;
	}
	
	public String getInfo(){
		return info;
	}
	
	public String getImage(){
		return image;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public Coordinate getPosition(){
		return position;
	}
	
	public Object[] getMedia(){
		return media;
	}
	
	/*******************************************
	 * SETTERS
	 *****************************************/
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setInfo(String info){
		this.info = info;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	
	public void set(int radius){
		this.radius = radius;
	}
	
	public void set(Coordinate position){
		this.position = position;
	}
	
	public void set(Object[] media){
		this.media = media;
	}
	
	
}
