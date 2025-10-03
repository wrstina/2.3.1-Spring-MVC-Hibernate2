package web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class TestController {
    @GetMapping
    public String testPage(Model model) {
        model.addAttribute("message", "Test Page Works!");
        return "test";
    }
}

