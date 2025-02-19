package com.javaguide.banking_app.service;

import java.util.List;

import com.javaguide.banking_app.dto.AccountDto;


public interface AccountService {


    AccountDto createrAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id , double amount);

    AccountDto withdraw(Long id ,double amount);

    List<AccountDto> getAllAccount();

    void deleteAccount(Long id);

}
