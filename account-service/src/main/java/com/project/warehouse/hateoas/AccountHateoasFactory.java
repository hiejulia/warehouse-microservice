package com.project.warehouse.hateoas;


import com.project.warehouse.model.Account;
import com.project.warehouse.rest.AccountRestController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@Component
public class AccountHateoasFactory {

    // hateoas factory

    public Resource<Account> toResource(Account account) {
        Resource<Account> accountResource = new Resource<>(account);

        Link link = linkTo(AccountRestController.class).slash(account.getUserName())
                .withSelfRel();
        accountResource.add(link);
        return accountResource;
    }

    public Resources<Account> toResources(List<Account> accountList) {
        Resources<Account> accountResource = new Resources<>(accountList);

        UriTemplate uriTemplate = new UriTemplate(String.format("%s/{userName}",linkTo(AccountRestController.class).toString()));
        Link accountLink = new Link(uriTemplate, "account");
        accountResource.add(accountLink);
        return accountResource;
    }
}