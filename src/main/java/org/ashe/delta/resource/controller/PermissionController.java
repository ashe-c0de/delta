package org.ashe.delta.resource.controller;

import org.ashe.delta.infra.TokenParse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class PermissionController {

    @GetMapping("/yes")
    public String yes () {
        return String.format("%s------------->welcome to token world!", TokenParse.getUserCode());
    }
}
