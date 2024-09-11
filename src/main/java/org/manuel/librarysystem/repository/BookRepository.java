package org.manuel.librarysystem.repository;

import org.manuel.librarysystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
