package com.company.manager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.manager.dao.CategoryDao;
import com.company.manager.model.Category;
import com.company.manager.response.CategoryResponseRest;

@Service
public class CategoryServiceImplement implements CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> search() {
		
		CategoryResponseRest response = new CategoryResponseRest();
		try {
			List<Category> category = (List<Category>) categoryDao.findAll();
			response.getCategoryResponse().setCategory(category);
			response.setMetadata("Response ok", "0", "Response success");
			
		}catch(Exception e) {
			response.setMetadata("Response error", "-1", "Response Error");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(
					response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

}
