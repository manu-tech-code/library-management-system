package org.manuel.librarymanagementsystem.controller;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.manuel.librarymanagementsystem.config.LibraryException;
import org.manuel.librarymanagementsystem.entity.Author;
import org.manuel.librarymanagementsystem.service.AuthorService;
import org.manuel.librarymanagementsystem.util.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.manuel.librarymanagementsystem.enums.FileUploadPaths.PROFILE_IMAGES;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final FileUploadUtil fileUploadUtil;

    @GetMapping("/authors")
    public String getAllAuthors(@NonNull Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "pages/author";
    }

    @GetMapping("/add-author")
    public String addBook(@NonNull Author author, @NonNull Model model) {
        model.addAttribute("author", author);
        return "pages/create/add_author";
    }

    @PostMapping("/add-author")
    public String saveBook(@Valid Author author, @NonNull BindingResult bindingResult, Model model, @RequestParam(name = "profile_image") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            return "pages/create/add_author";
        }
        model.addAttribute("author", author);
        try {
            if (!file.isEmpty()){
                author.setProfileImage(fileUploadUtil.uploadFile(file, PROFILE_IMAGES.getPath()));
            }
            authorService.save(author);
        } catch (IOException e) {
            throw new LibraryException(e.getMessage());
        }
        return "redirect:/authors";
    }

    @GetMapping("/update-author/{authorId}")
    public String updateBook(@NonNull Model model, @PathVariable Long authorId) {
        model.addAttribute("author", authorService.getAuthorById(authorId));
        return "pages/edit/update_author";
    }

    @PostMapping("/update-author/{authorId}")
    public String updateBook(@Valid Author author, @NonNull BindingResult bindingResult,
                             Model model,
                             @RequestParam(name = "profile_image") MultipartFile file,
                             @PathVariable Long authorId) {
        if (bindingResult.hasErrors()) {
            return "pages/edit/update_author";
        }
        model.addAttribute("author", author);
        try {
            if (!file.isEmpty()){
                author.setProfileImage(fileUploadUtil.uploadFile(file, PROFILE_IMAGES.getPath()));
            }
            authorService.update(authorId, author);
        } catch (IOException e) {
            throw new LibraryException(e.getMessage());
        }
        return "redirect:/authors";
    }

    @PostMapping("/delete-author/{authorId}")
    public String deleteAuthor(@PathVariable Long authorId) {
        authorService.delete(authorId);
        return "redirect:/authors";
    }
}