package com.dafiti.challenge.repository;

import java.util.Optional;

import com.dafiti.challenge.model.User;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByClient(String client);

}
