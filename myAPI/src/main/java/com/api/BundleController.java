package com.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;


@RestController
public class BundleController {
	 @Autowired
	 private BundleRepository bundleRepository;
	 
	    @RequestMapping(value = "/rest/bundles/{bundleId}", method = RequestMethod.GET)
	    public Bundle getBundle(@PathVariable("bundleId") Long bundleId){
	    	
	    	System.out.println("GET id request received"); //TODO remove
	    	if(bundleId == null){
	    		System.out.println("invalid Id request");
	    		//throw new InvalidBundleRequestException();
	    	}
	    	
	    	Bundle bundle = bundleRepository.findOne(bundleId);
	    	
	    	if(bundle == null){
	    		System.out.println("No bundle with that id found");
	    		//throw new BundleNotFoundException();
	    	}
	    	
	    	return bundle;
	    }
	 
	    @RequestMapping(value = "/rest/bundles", method = RequestMethod.GET)
	    public List<Bundle> getCustomers(){
	    	System.out.println("GET all request received"); //TODO remove
	    	return (List<Bundle>) bundleRepository.findAll();
	    }
	    

	   
	    @RequestMapping(value ="/rest/bundles", method = { RequestMethod.POST})
	    public Bundle createBundle(@RequestBody Bundle bundle, HttpServletResponse httpResponse, WebRequest request){
	    	System.out.println("POST request received"); //TODO remove
	    	Bundle createdBundle = null;
	    	createdBundle = bundleRepository.save(bundle);
	    	httpResponse.setStatus(HttpStatus.CREATED.value());
	    	httpResponse.setHeader("Location", String.format("%s/rest/bundles/%s", request.getContextPath(), bundle.getId()));
	    	
	    	return createdBundle;
	    }
	    
	    @RequestMapping(value = {"/rest/bundles/{bundleId}"}, method = { RequestMethod.PUT })
	    public void updateBundle(@RequestBody Bundle bundle, @PathVariable("bundleId") Long bundleId, HttpServletResponse httpResponse){
	    	System.out.println("PUT request received"); //TODO remove
	    	if(!bundleRepository.exists(bundleId)){
	    		httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
	    	}
	    	else{
	    		bundleRepository.save(bundle);
	    		httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
	    	}
	    }
	    
	    @RequestMapping(value = "/rest/bundles/{bundleId}", method = RequestMethod.DELETE)
	    public void removeBundle(@PathVariable("bundleId") Long bundleId, HttpServletResponse httpResponse){
	    	System.out.println("DELETE request received"); //TODO remove
	    	if(bundleRepository.exists(bundleId)){
	    		bundleRepository.delete(bundleId);
	    	}
	    	
	    	httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
	    }
}
