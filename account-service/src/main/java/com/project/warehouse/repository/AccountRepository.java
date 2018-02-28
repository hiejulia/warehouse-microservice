package com.project.warehouse.repository;


import com.project.warehouse.model.Account;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;


// extends JpaRepo
public interface AccountRepository extends JpaRepository<Account,String> { }