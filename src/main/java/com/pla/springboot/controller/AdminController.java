package com.pla.springboot.controller;

import java.util.List;
import java.util.Map;

import com.pla.springboot.dto.request.CategoryRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

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
    public String categories(Model model, @RequestParam Map<String, String> params) {
        List<CategoryResponse> categories = categoryService.search(params);
        model.addAttribute("categories", categories);
        model.addAttribute("counter", Math.ceil(categoryService.count(params) * 1.0 / 5));

        int page;
        if (params != null) {
            String p = params.get("page");
            if (p != null && !p.isEmpty()) {
                page = Integer.parseInt(p);
            } else {
                page = 1;
            }

            String kw = params.get("kw");
            if (kw != null) {
                model.addAttribute("kw", kw);
            }

        } else {
            page = 1;
        }
        model.addAttribute("currentPage", page);

        CategoryRequest category = new CategoryRequest();
        model.addAttribute("category", category);
        return "pages/categories";
    }

    @PostMapping("/categories")
    public String add(@ModelAttribute("category") @Valid CategoryRequest categoryRequest, BindingResult bindingResult)
            throws Exception {
        try {
            if(bindingResult.hasErrors()){
                return "pages/categories";
            }
            categoryService.addCategory(categoryRequest);
            return "redirect:/admin/categories";
        } catch (Exception e) {
            bindingResult.addError(new ObjectError("exceptionError", e.getMessage()));
            return "pages/categories";
        }
    }

    @GetMapping("/bosses")
    public String bosses(Model model) {
        CategoryResponse category = categoryService.getCategoryByName("Bosses");
        List<ItemResponse> bosses = itemService.getBossByCategoryId(category.getId());
        model.addAttribute("bosses", bosses);
        return "pages/bosses";
    }
}
