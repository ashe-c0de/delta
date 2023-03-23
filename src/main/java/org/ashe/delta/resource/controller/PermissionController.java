package org.ashe.delta.resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class PermissionController {

    @GetMapping("/yes")
    public String yes () {
        return "welcome to token world!";
    }
}
