package com.api;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Bundle {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String image;
	private String info;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Path> paths;
	
	public Bundle(){}
	
	public Bundle(String name, String info, String image, List<Path> paths){
		this.name = name;
		this.info = info;
		this.image = image;
		this.paths = paths;
	}
	
	//GETTERS
	public long getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getInfo(){
		return info;
	}
	
	public String getImage(){
		return image;
	}
	
	public List<Path> getPaths(){
		return paths;
	}
	
	//SETTERS
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setInfo(String info){
		this.info = info;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	
	public void setPaths(List<Path> paths){
		this.paths = paths;
	}
	
}
