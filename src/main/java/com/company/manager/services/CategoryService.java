package com.company.manager.services;

import org.springframework.http.ResponseEntity;

import com.company.manager.model.Category;
import com.company.manager.response.CategoryResponseRest;

public interface CategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();
	public ResponseEntity<CategoryResponseRest> searchById(Long id);
	public ResponseEntity<CategoryResponseRest> save(Category category);
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id);
	public ResponseEntity<CategoryResponseRest> deleteById(Long id);
}
