package org.manuel.librarymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.manuel.librarymanagementsystem.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "pages/category";
    }

//    @GetMapping("/add-category")
//    public String addCategory(Model model) {
//        model.addAttribute("categories", model);
//        return "pages/create/add_category";
//    }
}
