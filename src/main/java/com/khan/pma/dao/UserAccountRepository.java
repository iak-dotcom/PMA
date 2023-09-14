package com.khan.pma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khan.pma.entities.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
