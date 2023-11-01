package com.ut.useraccountservice.service;

import com.ut.useraccountservice.dto.UserAccountDto;
import com.ut.useraccountservice.entity.UserAccount;
import com.ut.useraccountservice.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class UserAccountService {

    private UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Transactional
    public UserAccount getUserAccountDetails(Long id){
        Optional<UserAccount> userAccount =  userAccountRepository.findById(id);
        return userAccount.get();
    }

    public String createAccountDetails(UserAccountDto userAccountDto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(userAccountDto.getName());
        userAccount.setEmail(userAccountDto.getEmail());
        userAccount.setPassword(userAccountDto.getPassword());
        userAccountRepository.save(userAccount);
        return "Data Saved Successfully";
    }

    public UserAccount updateAccountDetails(UserAccountDto userAccountDto, Long id){
        UserAccount userAccount = userAccountRepository.findById(id).get();
        userAccount.setUsername(userAccountDto.getName());
        userAccount.setEmail(userAccountDto.getEmail());
        userAccount.setPassword(userAccountDto.getPassword());

        userAccount.setUpdatedAt(new Date());
        userAccountRepository.save(userAccount);

        return userAccountRepository.findById(id).get();
    }
}
