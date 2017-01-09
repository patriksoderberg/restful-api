package com.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;



@Component
public class DataBuilder {
	
	public List<Bundle> createBundles(){
		
		List<Place> places = new ArrayList<Place>();
		places.add(new Place("Place1"));
		Coordinate[] coords = {new Coordinate(5,-4), new Coordinate(5,-9)};
		List<Path> paths = new ArrayList<Path>();
		paths.add(new Path("path1", "path info", "2km", coords, "45m", "img url", places));
		
		//String name, String info, String length, Coordinate[] polyLine, String duration, String image, List<Place> places
		Bundle bundle1 = new Bundle("Example1", "example.com/image", "information here", paths );
		return Arrays.asList(bundle1);
		
	}
}

//Bundle(String name, String info, int length, String duration, String image)