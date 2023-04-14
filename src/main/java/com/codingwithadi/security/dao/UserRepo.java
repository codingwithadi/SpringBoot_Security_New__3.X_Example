package com.codingwithadi.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingwithadi.security.model.UserInfo;

public interface UserRepo extends JpaRepository<UserInfo, Integer>{

	Optional<UserInfo> findByName(String username);

}
