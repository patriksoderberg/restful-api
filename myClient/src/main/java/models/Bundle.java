package models;

import java.util.ArrayList;
import java.util.List;

public class Bundle {
	
	private long id;
	private String name;
	private String image;
	private String info;
	
	private List<Path> paths = new ArrayList<Path>();;
	
	public Bundle(){
	}
	
	public Bundle(String name, String info, String image, List<Path> paths){
		this.name = name;
		this.info = info;
		this.image = image;
		this.paths = paths;
	}
	
	public String toString(){
		return id + " " + name;
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
	
	public void addPath(Path path){
		paths.add(path);
	}
}
