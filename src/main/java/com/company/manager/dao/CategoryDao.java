package com.company.manager.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.manager.model.Category;

public interface CategoryDao extends CrudRepository<Category, Long> {

}
