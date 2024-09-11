package org.manuel.librarysystem.repository;

import org.manuel.librarysystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
