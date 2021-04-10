package com.klid.demodockercompose;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ivan Kaptue
 */
@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String index() {
        return "Hello world";
    }

}
