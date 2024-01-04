package com.istasyon.backend.controllers;

import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
    private JsonCreator jsonCreator;

    public DashboardController(JsonCreator jsonCreator){
        this.jsonCreator=jsonCreator;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<CustomJson<Object>> dashboard() {
        return jsonCreator.create(true);

        //return "dashboard page";
    }
}
