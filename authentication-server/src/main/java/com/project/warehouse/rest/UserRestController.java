package com.project.warehouse.rest;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Data
@Slf4j
@RestController
@RequestMapping("/account")
public class UserRestController {

    @GetMapping("/userInfo")
    public Principal userInfo(Principal principal){

        return principal;
    }
}