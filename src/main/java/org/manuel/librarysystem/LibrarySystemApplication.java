package org.manuel.librarysystem;

import org.manuel.librarysystem.entity.Author;
import org.manuel.librarysystem.entity.Book;
import org.manuel.librarysystem.entity.Category;
import org.manuel.librarysystem.service.BookService;
import org.manuel.librarysystem.entity.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibrarySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibrarySystemApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BookService bookService){
        return args -> {
            Book book = new Book("Wellness", "978-3-16-148410-0", "Book Description 1");
            Author author = new Author("Emmanuel", "description");
            Category category = new Category("Business");
            Publisher publisher = new Publisher("Publisher");

            book.addAuthor(author);
            book.addCategory(category);
            book.addPublisher(publisher);

            bookService.save(book);

            Book book2 = new Book("Mindset", "978-3-16-148410-1", "Book Title");
            Author author2 = new Author("Manuel", "description");
            Category category2 = new Category("Business 2");
            Publisher publisher2 = new Publisher("Publisher 2");

            book2.addAuthor(author2);
            book2.addCategory(category2);
            book2.addPublisher(publisher2);

            bookService.save(book2);
        };
    }
}
