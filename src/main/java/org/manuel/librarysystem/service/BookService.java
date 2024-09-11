package org.manuel.librarysystem.service;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.manuel.librarysystem.config.LibraryException;
import org.manuel.librarysystem.entity.Book;
import org.manuel.librarysystem.repository.BookRepository;
import org.manuel.librarysystem.service.util.LibraryInterface;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Book service.
 */
@Service
@RequiredArgsConstructor
public class BookService implements LibraryInterface<Book> {
    private final BookRepository bookRepository;

    /**
     * Get all books.
     *
     * @return the books
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Gets book by id.
     *
     * @param id the id
     * @return the book by id
     */
    public Book getBookById(Long id) {
        return getBook(id);
    }

    /**
     * Create a book.
     *
     * @param book the book
     */
    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    /**
     * Update a book.
     *
     * @param book the book
     */
    @Override
    public void update(@NonNull Long id, @NonNull Book book) {
        Book existingBook = getBook(id);

        if (existingBook != null) {
            existingBook.setIsbn(book.getIsbn());
            existingBook.setName(book.getName());
            existingBook.setDescription(book.getDescription());
            existingBook.setAuthors(book.getAuthors());
            existingBook.setCategories(book.getCategories());
            existingBook.setPublishers(book.getPublishers());
            bookRepository.save(existingBook);
        }
    }

    private Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new LibraryException("Book not found"));
    }

    /**
     * Delete a book.
     *
     * @param id the id
     */
    @Override
    public void delete(Long id) {
        Book book = getBook(id);
        bookRepository.delete(book);
    }
}
