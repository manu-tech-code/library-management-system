package org.manuel.librarymanagementsystem.config.exceptions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController {
    // Handle 404 errors
    @GetMapping("/error")
    public String handleError() {
        return "error";
    }

}
