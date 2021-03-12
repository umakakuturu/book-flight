package com.star.bookflight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.star.bookflight.entity.User;

public interface UserRepository extends JpaRepository<User , Long> {
	
	public User findByEmailAndPassword(String email,String password);
	
	public User findByUserId(Long userId);

}
