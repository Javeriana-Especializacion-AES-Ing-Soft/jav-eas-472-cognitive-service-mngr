package co.edu.javeriana2.cognitive.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*") // NO SONAR
@RequestMapping("health")
@RestController
public class HealthController {

    @GetMapping
    public String health() {
        return "OK...";
    }

}
