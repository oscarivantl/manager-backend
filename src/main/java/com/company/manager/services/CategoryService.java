package com.company.manager.services;

import org.springframework.http.ResponseEntity;

import com.company.manager.response.CategoryResponseRest;

public interface CategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();
}
