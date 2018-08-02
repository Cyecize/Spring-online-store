package com.cyecize.skatefixers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {
    @GetMapping("/")
    @ResponseBody
    public String indexAction(){
        return "<h1>It Works</h1>";
    }
}
