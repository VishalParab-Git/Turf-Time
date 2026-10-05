package com.turftime.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turftime.entity.TurfsEntity;
import com.turftime.enums.TurfStatus;

@Repository
public interface TurfRepository  extends JpaRepository<TurfsEntity, Long>{

	List<TurfsEntity> findByOwnerUserUsernameAndStatusNot(String username, TurfStatus status);
	
	List<TurfsEntity> findAllByCityIgnoreCaseAndStateIgnoreCaseAndStatus(String city, String state, TurfStatus status);
	
    List<TurfsEntity> findByCityAndStatus(String city, com.turftime.enums.TurfStatus status);
    
    Optional<TurfsEntity> findByIdAndStatus(Long turfId, TurfStatus status );
}
