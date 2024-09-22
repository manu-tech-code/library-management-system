package org.manuel.librarymanagementsystem.controller;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.manuel.librarymanagementsystem.config.LibraryException;
import org.manuel.librarymanagementsystem.entity.Book;
import org.manuel.librarymanagementsystem.service.AuthorService;
import org.manuel.librarymanagementsystem.service.BookService;
import org.manuel.librarymanagementsystem.service.CategoryService;
import org.manuel.librarymanagementsystem.service.PublisherService;
import org.manuel.librarymanagementsystem.enums.FileUploadPaths;
import org.manuel.librarymanagementsystem.util.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final FileUploadUtil fileUploadUtil;

    @GetMapping("/books")
    public String getAllBooks(@NonNull Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "pages/book";
    }

    @GetMapping("/book/{id}")
    public String getBookById(@NonNull Model model, @NonNull @PathVariable Long id) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "pages/view/book_details";
    }

    @GetMapping("/add-book")
    public String addBook(@NonNull Book book,@NonNull Model model) {
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "pages/create/add_book";
    }

    @PostMapping("/add-book")
    public String saveBook(@Valid Book book, @NonNull BindingResult bindingResult, Model model,@RequestParam(name = "cover_image") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "pages/create/add_book";
        }
        return saveBookData(book, model, file);
    }

    private String saveBookData(Book book, Model model, MultipartFile file) {
        model.addAttribute("book", book);
        try {
            String coverImage = fileUploadUtil.uploadFile(file, FileUploadPaths.BOOK_COVERS.getPath());
            book.setCoverImage(coverImage);
            bookService.save(book);
        } catch (IOException e) {
            throw new LibraryException(e.getMessage());
        }
        return "redirect:/books";
    }

    @GetMapping("/update-book/{bookId}")
    public String updateBook(@NonNull Model model, @PathVariable Long bookId) {
        model.addAttribute("book", bookService.getBookById(bookId));
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "pages/edit/update_book";
    }

    @PostMapping("/update-book/{bookId}")
    public String updateBook(@Valid Book book,@NonNull BindingResult bindingResult, Model model, @PathVariable Long bookId, @RequestParam(name = "image") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "pages/edit/update_book";
        }
        model.addAttribute("book", book);
        try {
            String coverImage = fileUploadUtil.uploadFile(file, FileUploadPaths.BOOK_COVERS.getPath());
            book.setCoverImage(coverImage);
            bookService.update(bookId, book);
        } catch (Exception e) {
            throw new LibraryException(e.getMessage());
        }
        return "redirect:/books";
    }

    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}