package me.twocat.shareinfo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestNewController {

    @RequestMapping("/admin")
    public String admin(){
        System.out.println("the request already invoke this method");
        return "success";
    }


    @RequestMapping("/index")
    public String index(){
        System.out.println("print this method information");
        return "success";
    }

}
