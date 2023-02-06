package com.example.web_giay.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping({"/","","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/cart")
    public String cart(){
        return "cart";
    }

    @RequestMapping("/contact")
    public String contact(){
        return "contact";
    }

    @RequestMapping("/sign-in")
    public String signin() {
        return "sign-in";
    }

    @RequestMapping({"/sign-in/sign-up","/sign-up"})
    public String signup() {
        return "sign-up";
    }
//    @RequestMapping("/shop")
//    public String shop(){
//        return "shop";
//    }
}
