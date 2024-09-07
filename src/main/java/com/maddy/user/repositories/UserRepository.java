package com.maddy.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maddy.user.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
