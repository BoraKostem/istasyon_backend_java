package com.istasyon.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotFoundController {
    @RequestMapping("/404")
    public String notFound() {
        return "404 Not Found";
    }
}
