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
    public UserAccount getUserAccountDetails(Long id) {
        Optional<UserAccount> userAccount = userAccountRepository.findById(id);
        return userAccount.get();
    }

    public String createUserAccountDetails(UserAccountDto userAccountDto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(userAccountDto.getName());
        userAccount.setEmail(userAccountDto.getEmail());
        userAccount.setPassword(userAccountDto.getPassword());
        userAccountRepository.save(userAccount);
        return "Data Saved Successfully";
    }

    public Optional<UserAccount> updateUserAccountDetails(UserAccountDto userAccountDto, Long id) {
        Optional<UserAccount> userAccount = userAccountRepository.findById(id);
        if(userAccount.isPresent()) {
            userAccount.get().setUsername(userAccountDto.getName());
            userAccount.get().setEmail(userAccountDto.getEmail());
            userAccount.get().setPassword(userAccountDto.getPassword());

            userAccount.get().setUpdatedAt(new Date());
            userAccountRepository.save(userAccount.get());
            return userAccountRepository.findById(id);

        }
        else {
            return Optional.empty();
        }
    }

    public String deleteUserAccount(Long id) {
        Optional<UserAccount> userAccount = userAccountRepository.findById(id);

        if(userAccount.isPresent()) {
            userAccountRepository.deleteById(id);
            return "Account Deleted of " + id + ":" + userAccount.get().getUsername();
        }
        else
            return "Account not Found for id " + id;

    }
}
