package com.Subrat.BBank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/xy/")
    public String print(){
        return "subrat bank";
    }
}
