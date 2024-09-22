package org.manuel.librarymanagementsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.manuel.librarymanagementsystem.entity.Publisher;
import org.manuel.librarymanagementsystem.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping("/publishers")
    public String getAllCategories(Model model) {
        model.addAttribute("publishers", publisherService.getAllPublishers());
        return "pages/publisher";
    }

    @GetMapping("/add-publisher")
    public String addPublisher(Publisher publisher, Model model) {
        model.addAttribute("publisher", publisher);
        return "pages/create/add_publisher";
    }

    @PostMapping("/add-publisher")
    public String addPublisher(@Valid Publisher publisher, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pages/create/add_publisher";
        }

        model.addAttribute("publisher", publisher);
        publisherService.save(publisher);

        return "redirect:/publishers";
    }

    @GetMapping("/update-publisher/{publisherId}")
    public String updatePublisher(Model model, @PathVariable Long publisherId) {
        model.addAttribute("publisher", publisherService.getPublisherById(publisherId));
        return "pages/edit/update_publisher";
    }

    @PostMapping("/update-publisher/{publisherId}")
    public String updatePublisher(@Valid Publisher publisher, Model model, BindingResult bindingResult, @PathVariable Long publisherId) {
        if (bindingResult.hasErrors()) {
            return "pages/edit/update_publisher";
        }

        model.addAttribute("publisher", publisher);
        publisherService.update(publisherId,publisher);

        return "redirect:/publishers";
    }

    @PostMapping("delete-publisher/{publisherId}")
    public String deletePublisher(@PathVariable Long publisherId) {
        publisherService.delete(publisherId);
        return "redirect:/publishers";
    }
}
