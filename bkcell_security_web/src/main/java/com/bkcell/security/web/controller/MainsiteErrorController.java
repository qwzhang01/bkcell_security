package com.bkcell.security.web.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainsiteErrorController implements ErrorController {

    @RequestMapping(value = "/error")
    public String handleError() {
        return "common/404.html";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}