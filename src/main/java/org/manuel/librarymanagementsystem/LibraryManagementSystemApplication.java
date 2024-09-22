package org.manuel.librarymanagementsystem;

import org.manuel.librarymanagementsystem.entity.Author;
import org.manuel.librarymanagementsystem.entity.Book;
import org.manuel.librarymanagementsystem.entity.Category;
import org.manuel.librarymanagementsystem.service.BookService;
import org.manuel.librarymanagementsystem.entity.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystemApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(BookService bookService){
//        return args -> {
//            Book book = new Book("Wellness", "978-3-16-148410-0", "Book Description 1");
//            Author author = new Author("Emmanuel", "description");
//            Category category = new Category("Business");
//            Publisher publisher = new Publisher("Publisher");
//
//            book.addAuthor(author);
//            book.addCategory(category);
//            book.addPublisher(publisher);
//
//            bookService.save(book);
//
//            Book book2 = new Book("Mindset", "978-3-16-148410-1", "Book Title");
//            Author author2 = new Author("Manuel", "description");
//            Category category2 = new Category("Business 2");
//            Publisher publisher2 = new Publisher("Publisher 2");
//
//            book2.addAuthor(author2);
//            book2.addCategory(category2);
//            book2.addPublisher(publisher2);
//
//            bookService.save(book2);
//        };
//    }
}
