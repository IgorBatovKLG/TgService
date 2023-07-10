package com.example.tgservice.controller;

import com.example.tgservice.repository.TgRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    private final TgRepository tgRepository;

    public IndexController(TgRepository tgRepository) {
        this.tgRepository = tgRepository;
    }

    @GetMapping("/index2")
    public String index2(Model model){
        List<String> list = tgRepository.getImgNames(1, 200);
        model.addAttribute("list", list);
        return "index2";
    }

    @GetMapping("/deleteImg")
    public String deleteImg(Model model,
                            @RequestParam(name = "id", required = false, defaultValue = "") int id){
        tgRepository.updateOffImg(id);
        model.addAttribute("id", id);
        return "index";
    }
}
