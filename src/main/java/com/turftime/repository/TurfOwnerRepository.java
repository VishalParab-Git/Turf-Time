package com.turftime.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turftime.entity.TurfOwnersEntity;
import com.turftime.entity.UserEntity;

@Repository
public interface TurfOwnerRepository extends JpaRepository<TurfOwnersEntity,Long> {
	
    Optional<TurfOwnersEntity> findByUserUsername(String username);
    
    boolean existsByUser(UserEntity user);



}
