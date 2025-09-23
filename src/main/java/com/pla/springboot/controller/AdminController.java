package com.pla.springboot.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @RequestMapping("/admin")
    public String login(Model model, @RequestParam Map<String, String> params) {
        if (params.containsKey("error")) {
            model.addAttribute("loginError", true);
        }
        return "pages/login";
    }

    @RequestMapping("/admin/dashboard")
    public String dashboard(Model model) {
        return "pages/dashboard";
    }

    @RequestMapping("/admin/categories")
    public String categories(Model model) {
        return "pages/categories";
    }

    @RequestMapping("/admin/bosses")
    public String bosses(Model model) {
        return "pages/bosses";
    }
}
