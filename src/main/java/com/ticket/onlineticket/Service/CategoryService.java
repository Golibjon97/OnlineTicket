package com.ticket.onlineticket.Service;

import com.ticket.onlineticket.Domain.Category;
import com.ticket.onlineticket.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> findOne(Integer id){
        return categoryRepository.findById(id);
    }
}
