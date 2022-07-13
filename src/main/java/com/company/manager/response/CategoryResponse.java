package com.company.manager.response;

import java.util.List;

import com.company.manager.model.Category;
import lombok.Data;

@Data
public class CategoryResponse {
	
	private List<Category> category;
}
