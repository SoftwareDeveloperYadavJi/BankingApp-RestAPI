package com.javaguide.banking_app.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.javaguide.banking_app.dto.AccountDto;
import com.javaguide.banking_app.entity.Account;
import com.javaguide.banking_app.mapper.AccountMapper;
import com.javaguide.banking_app.repository.AccountRepository;
import com.javaguide.banking_app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    
    private AccountRepository accountRepository;
    


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }



    @Override
    public AccountDto createrAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }



    @Override
    public AccountDto getAccountById(Long id) {
        Account account =accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Does not exists"));

        return AccountMapper.mapToAccountDto(account);
    }



    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Does not exists"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }



    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Does not exists"));
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient Amount");

        }

        double total = account.getBalance() - amount;
        account.setBalance(total);

        Account  savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }



    @Override
    public List<AccountDto> getAllAccount() {
        
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }



    @Override
    public void deleteAccount(Long id) {
        Account account =accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Does not exists"));
        accountRepository.deleteById(id);
        
    }

}
