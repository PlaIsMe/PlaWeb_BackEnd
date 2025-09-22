package com.pla.springboot.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @RequestMapping("/admin")
    public String index(Model model, @RequestParam Map<String, String> params) {
        if (params.containsKey("error")) {
            model.addAttribute("loginError", true);
        }
        return "pages/login";
    }

    @RequestMapping("/admin/dashboard")
    public String dashBoard(Model model) {
        return "pages/dashboard";
    }
}
