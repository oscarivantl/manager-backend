package com.company.manager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	/**
	 * Search categories
	 */
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> search() {
		
		CategoryResponseRest response = new CategoryResponseRest();
		try {
			List<Category> category = (List<Category>) categoryDao.findAll();
			response.getCategoryResponse().setCategory(category);
			response.setMetadata("Response ok", " 0", "success");
			
		}catch(Exception e) {
			response.setMetadata("Response error", "-1", "Error");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(
					response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}
	
	/**
	 * Search categories by Id 
	 */
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchById(Long id) {
		
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> List = new ArrayList<>();
		
		try {
			
			Optional<Category> category = categoryDao.findById(id);
			if(category.isPresent()) {
				List.add(category.get());
				response.getCategoryResponse().setCategory(List);
				response.setMetadata("Response success", "0", "Categoria encontrada");
			}else {
				response.setMetadata("Response error", "-1", "Categoria no encontrada");
				return new ResponseEntity<CategoryResponseRest>
				(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			response.setMetadata("Response error", "-1", "Error al buscar por id");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(
					response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	/**
	 * save new category 
	 */
	
	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> save(Category category) {
		
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> List = new ArrayList<>();
		
		try {
			Category categorySaved = categoryDao.save(category);
			if(categorySaved != null) {
				List.add(categorySaved);
				response.getCategoryResponse().setCategory(List);
				response.setMetadata("Response success", "0", "Categoria guardada.");
			}else {
				response.setMetadata("Response error", "-1", "No se guardo la categoria");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
			}	 
		}catch(Exception e) {
			response.setMetadata("Response error", "-1", "Error al guardar la categoria");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(
					response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	/**
	 * Update a category 
	 */
	
	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
		
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> List = new ArrayList<>();
		
		try {
			Optional<Category> categorySearch = categoryDao.findById(id);
			if(categorySearch.isPresent()) {
				categorySearch.get().setName(category.getName());
				categorySearch.get().setDescription(category.getDescription());
				
				Category categoryToUpdate = categoryDao.save(categorySearch.get());
				
				if(categoryToUpdate != null) {
					List.add(categoryToUpdate);
					response.getCategoryResponse().setCategory(List);
					response.setMetadata("Response success", "0", "Categoria actualizada.");	
				}else {
					response.setMetadata("Response error", "-1", "No se actualizo la categoria");
					return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			}else {
				response.setMetadata("Error", "-1", "No se encontro la categoria");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			response.setMetadata("Response error", "-1", "Error al actualizar la categoria");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(
					response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	/**
	 * Update a category 
	 */
	
	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
		
		CategoryResponseRest response = new CategoryResponseRest();
		
		try {
			categoryDao.deleteById(id);
			response.setMetadata("Response success", "0", "Registro eliminado");
			
		}catch(Exception e) {
			response.setMetadata("Response error", "-1", "Error al eliminar la categoria");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(
					response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

}
