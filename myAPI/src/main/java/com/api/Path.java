package com.api;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Path {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	
	
	private String info;
	private String length;
	private Coordinate[] polyline;
	private String duration;
	private String image;
	
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Place> places;
	
	public Path(){}
	
	public Path(String name, String info, String length, Coordinate[] polyLine, String duration, String image, List<Place> places){
		this.name = name;
		this.info = info;
		this.length = length;
		this.polyline = polyLine;
		this.duration = duration;
		this.image = image;
		this.places = places;
	}
	
	//************GETTERS********************
	public int getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	
	public List<Place> getPlaces(){
		return places;
	}
	
	public String getLength(){
		return length;
	}
	
	public String getDuration(){
		return duration;
	}
	

	public String getImage(){
		return image;
	}
	
	public Coordinate[] getPolyline(){
		return polyline;
	}
	
	public String getInfo(){
		return info;
	}
	
	//************SETTERS********************
	 
	public void setName(String name){
		this.name = name;
	}
	
	public void setPlaces(List<Place> places){
		this.places = places;
	}
	
	
	public void setInfo(String info){
		this.info = info;
	}
	
	public void setLength(String length){
		this.length = length;
	}
	
	public void setDuration(String duration){
		this.duration = duration;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	
	public void setPolyline(Coordinate[] polyline){
		this.polyline = polyline;
	}
	
	
}
