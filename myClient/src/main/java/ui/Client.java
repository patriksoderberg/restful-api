package ui;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import models.Bundle;

public class Client {

	static private RestTemplate restTemplate = new RestTemplate();
	
    public static Bundle get(int id) {
    	
        Bundle bundle = restTemplate.getForObject("http://localhost:8080/rest/bundles/" + id, Bundle.class);
        System.out.println(bundle);
        return bundle;
    }
    
    public static Bundle[] get(){
        Bundle[] bundles = restTemplate.getForObject("http://localhost:8080/rest/bundles/", Bundle[].class);
        System.out.println(bundles);
        return bundles;
    }
    
    public static void delete(long id){
    	restTemplate.delete("http://localhost:8080/rest/bundles/" + id);
    }
    
    public static Bundle post(Bundle bundle){
        Bundle createdBundle = restTemplate.postForObject("http://localhost:8080/rest/bundles/", bundle, Bundle.class);
        return createdBundle;
    }
    
    public static void put(Bundle bundle){
        restTemplate.put("http://localhost:8080/rest/bundles/" + bundle.getId(), bundle );
    }
}
