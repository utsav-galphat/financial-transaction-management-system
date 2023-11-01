package com.ut.useraccountservice.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDto {
    @NotNull
    private String name;

    @NotNull
    private String email;
    @NotNull
    private String password;

}
