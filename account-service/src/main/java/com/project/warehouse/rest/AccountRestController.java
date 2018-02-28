package com.project.warehouse.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import com.project.warehouse.hateoas.AccountHateoasFactory;
import com.project.warehouse.model.Account;
import com.project.warehouse.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


@Data
@RestController
@RequestMapping("/account")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountHateoasFactory accountHateoasFactory;

    // POST : create new account:
    // Account
    @PostMapping
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")}) // TODO
    public ResponseEntity createAccount(@RequestBody Account account){
        Account savedAccount = accountService.createAccount(account);
        return ResponseEntity.created(MvcUriComponentsBuilder
                .fromMethodName(AccountRestController.class,"findAccount",savedAccount.getUserName())
                .build().toUri()).build();// TODO
        // return the saved account

    }


    //GET get all account list
    @GetMapping
    @PreAuthorize("isAuthenticated()") // TODO
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity findAccountList(){
        return ResponseEntity.ok(accountHateoasFactory.toResources(accountService.findAccountList()));
    }

    // GET findAccount By username
    @GetMapping("/{userName}")
    @PreAuthorize("isAuthenticated()")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity findAccount(@PathVariable String userName){
        return ResponseEntity.ok(accountHateoasFactory.toResource(accountService.findAccount(userName)));
    }

    // find account by id

    @PutMapping("/{userName}")
    @PreAuthorize("isAuthenticated()")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity updateAccount(@PathVariable String userName, @RequestBody Account account){
        account.setUserName(userName);
        accountService.updateAccount(account);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userName}")
    @PreAuthorize("isAuthenticated()")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity deleteAccount(@PathVariable String userName){
        accountService.deleteAccount(userName);
        return ResponseEntity.noContent().build();
    }
}