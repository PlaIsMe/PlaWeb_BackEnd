package com.pla.springboot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pla.springboot.dto.response.CategoryResponse;
import com.pla.springboot.dto.response.ItemResponse;
import com.pla.springboot.service.CategoryService;
import com.pla.springboot.service.ItemService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {
    int PAGE_SIZE = 10;
    ItemService itemService;
    CategoryService categoryService;

    @GetMapping
    public String login(Model model, @RequestParam Map<String, String> params) {
        if (params.containsKey("error")) {
            model.addAttribute("loginError", true);
        }
        return "pages/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "pages/dashboard";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        List<CategoryResponse> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "pages/categories";
    }

    @GetMapping("/bosses")
    public String bosses(Model model) {
        CategoryResponse category = categoryService.getCategoryByName("Bosses");
        List<ItemResponse> bosses = itemService.getBossByCategoryId(category.getId());
        model.addAttribute("bosses", bosses);
        return "pages/bosses";
    }
}
