package com.codeup.blogapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    //This allows uses to manually type these paths in browser, if they were to just click inside the html this class will not be called
    @RequestMapping({"/", "/about", "/posts", "/login", "/home", "/users", "/search"})
    public String showView(){
        //Called a forwarding redirect - if we try to go to any of those, it will direct us to index.html
        return "forward:/index.html";
    }
}
