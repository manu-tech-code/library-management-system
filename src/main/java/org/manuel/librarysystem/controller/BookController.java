package org.manuel.librarysystem.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.manuel.librarysystem.entity.Book;
import org.manuel.librarysystem.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public String getAllBooks(@NonNull Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "pages/book";
    }

    @GetMapping("/books/{id}")
    public String getBookById(@NonNull Model model, @NonNull @PathVariable Long id) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "pages/view/book_details";
    }
}
