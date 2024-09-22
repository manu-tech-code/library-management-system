package org.manuel.librarymanagementsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.manuel.librarymanagementsystem.entity.Category;
import org.manuel.librarymanagementsystem.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "pages/category";
    }

    @GetMapping("/add-category")
    public String addCategory(Category category, Model model) {
        model.addAttribute("category", category);
        return "pages/create/add_category";
    }

    @PostMapping("/add-category")
    public String addCategory(@Valid Category category, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pages/create/add_category";
        }

        model.addAttribute("category", category);
        categoryService.save(category);

        return "redirect:/categories";
    }

    @GetMapping("/update-category/{categoryId}")
    public String updateCategory(Model model, @PathVariable Long categoryId) {
        model.addAttribute("category", categoryService.getCategoryById(categoryId));
        return "pages/edit/update_category";
    }

    @PostMapping("/update-category/{categoryId}")
    public String updateCategory(@Valid Category category, Model model, BindingResult bindingResult, @PathVariable Long categoryId) {
        if (bindingResult.hasErrors()) {
            return "pages/create/add_category";
        }

        model.addAttribute("category", category);
        categoryService.update(categoryId,category);

        return "redirect:/categories";
    }

    @PostMapping("delete-category/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
        return "redirect:/categories";
    }
}
