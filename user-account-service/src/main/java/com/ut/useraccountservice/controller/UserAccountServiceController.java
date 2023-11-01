package com.ut.useraccountservice.controller;

import com.ut.useraccountservice.dto.UserAccountDto;
import com.ut.useraccountservice.entity.UserAccount;
import com.ut.useraccountservice.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserAccountServiceController {

    private static final Logger logger = LoggerFactory.getLogger(UserAccountServiceController.class);

    private UserAccountService userAccountService;

    @Autowired
    public UserAccountServiceController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }


    @GetMapping(value = "/healthcheck")
    public String sayHello(HttpServletRequest request) {
        return "I am UP and Running!";
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get user account"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public ResponseEntity<UserAccount> getDetailsOfUser(@PathVariable Long id) {
        UserAccount userAccount = userAccountService.getUserAccountDetails(id);

        if (userAccount != null) {
            return ResponseEntity.ok(userAccount);
        } else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created user account"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public String addNewEntry(@RequestBody UserAccountDto userAccountDto) {
        return userAccountService.createUserAccountDetails(userAccountDto);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Update a user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated user account"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public ResponseEntity<UserAccount> updateEntry(@RequestBody UserAccountDto userAccountDto, @PathVariable Long id) {
        Optional<UserAccount> userAccount = userAccountService.updateUserAccountDetails(userAccountDto, id);

        return userAccount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete a user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted user account"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    public String deleteEntry(@PathVariable Long id){
        return userAccountService.deleteUserAccount(id);
    }

}
