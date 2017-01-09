package com.api;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface BundleRepository extends CrudRepository<Bundle, Long> {
	/*
	 * If there is an entity contained within another entity that is created/managed via a cascade relationship, you don't need to explicitly create a repository for that entity.


	 * Spring Data recognizes that name is an instance variable on Customer and so provides us with an implementation for this query.
	 */
	public List<Bundle> findByName(String name);
}
