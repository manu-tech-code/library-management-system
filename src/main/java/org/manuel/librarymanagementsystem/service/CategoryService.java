package org.manuel.librarymanagementsystem.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.manuel.librarymanagementsystem.config.LibraryException;
import org.manuel.librarymanagementsystem.entity.Category;
import org.manuel.librarymanagementsystem.repository.CategoryRepository;
import org.manuel.librarymanagementsystem.service.util.LibraryInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements LibraryInterface<Category> {
    private final CategoryRepository categoryRepository;

    /**
     * Gets all authors.
     *
     * @return the all authors
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Gets author by id.
     *
     * @param id the id
     * @return the author by id
     */
    public Category getCategoryById(Long id) {
        return getCategory(id);
    }

    /**
     * Save author.
     *
     * @param category the author
     */
    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    /**
     * Update author.
     *
     * @param category the author
     */
    @Override
    public void update(Long id,@NonNull Category category) {
        Category oldCategory = getCategory(id);

        oldCategory.setName(category.getName());
        oldCategory.setBooks(category.getBooks());

        categoryRepository.save(oldCategory);
    }


    /**
     * Delete author.
     *
     * @param id the category id
     */
    @Override
    public void delete(Long id) {
        categoryRepository.delete(getCategory(id));
    }

    private Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new LibraryException("Category not found"));
    }
}
