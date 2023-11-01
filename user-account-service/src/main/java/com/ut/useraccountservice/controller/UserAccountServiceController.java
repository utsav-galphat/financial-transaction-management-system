package com.ut.useraccountservice.controller;

import com.ut.useraccountservice.dto.UserAccountDto;
import com.ut.useraccountservice.entity.UserAccount;
import com.ut.useraccountservice.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/user-account")
public class UserAccountServiceController {

    private static final Logger logger = LoggerFactory.getLogger(UserAccountServiceController.class);

    private UserAccountService userAccountService;

    @Autowired
    public UserAccountServiceController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }


    @GetMapping(value = "/hello")
    public String sayHello(HttpServletRequest request) {
        logger.info("request received for endpoint [" + request.getRequestURI() + "]");
        return "Hello World!";
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserAccount> getDetailsOfUser(@PathVariable Long id) {
        UserAccount userAccount = userAccountService.getUserAccountDetails(id);

        if (userAccount != null) {
            return ResponseEntity.ok(userAccount);
        } else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewEntry(@RequestBody UserAccountDto userAccountDto) {
        return userAccountService.createUserAccountDetails(userAccountDto);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserAccount> updateEntry(@RequestBody UserAccountDto userAccountDto, @PathVariable Long id) {
        Optional<UserAccount> userAccount = userAccountService.updateUserAccountDetails(userAccountDto, id);

        return userAccount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEntry(@PathVariable Long id){
        return userAccountService.deleteUserAccount(id);
    }

}
