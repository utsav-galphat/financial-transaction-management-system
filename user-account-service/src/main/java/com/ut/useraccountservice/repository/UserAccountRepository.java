package com.ut.useraccountservice.repository;

import com.ut.useraccountservice.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
