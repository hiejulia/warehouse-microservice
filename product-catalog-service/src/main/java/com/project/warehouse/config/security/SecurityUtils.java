package com.project.warehouse.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SecurityUtils {

    public String getPrincipalUserName(){

        String userName = "";

        try{

            // get user name
            userName = SecurityContextHolder.getContext().getAuthentication().getName();

        } catch (Throwable t){


        }
        return userName;
    }

}