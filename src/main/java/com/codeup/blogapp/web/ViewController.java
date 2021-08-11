package com.codeup.blogapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping({"/", "/about", "/posts", "/login", "/home"})
    public String showView(){
        //Called a forwarding redirect - if we try to go to any of those, it will direct us to index.html
        return "forward:/index.html";
    }
}
