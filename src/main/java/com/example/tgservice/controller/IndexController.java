package com.example.tgservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @GetMapping("/index2")
    public String index2(Model model){
        List<String> list = new ArrayList<>();
        list.add("Image1");
        list.add("Image2");
        model.addAttribute("list", list);
        return "index2";
    }
}
