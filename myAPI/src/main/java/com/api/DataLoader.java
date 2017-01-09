package com.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private DataBuilder dataBuilder;
	
	@Autowired
	private BundleRepository bundleRepository;
	
	//On application startup
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
		System.out.println("Loading test data...");
		dataBuilder.createBundles().forEach(bundle -> bundleRepository.save(bundle));
		System.out.println("Test data loaded...");
	}
}
