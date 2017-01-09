package models;

import java.io.Serializable;

public class Coordinate implements Serializable{
	
	private static final long serialVersionUID = -1632002939598308967L;
	private double latitude;
	private double longitude;
	
	public Coordinate(){}
	
	public Coordinate(double lat, double lon){
		latitude = lat;
		latitude = lon;
	}
	
	public void setLatitude(double lat){
		latitude = lat;
	}
	
	public void setLongitude(double lon){
		longitude = lon;
	}
	
	public double getLatitude(){
		return latitude;
	}
	public double getLongitude(){
		return longitude;
	}
}
