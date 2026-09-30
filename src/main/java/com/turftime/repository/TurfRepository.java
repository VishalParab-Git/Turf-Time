package com.turftime.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turftime.entity.TurfsEntity;

@Repository
public interface TurfRepository  extends JpaRepository<TurfsEntity, Long>{

	Optional<TurfsEntity> findByOwnerUserUsername(String username);
	
	List<TurfsEntity> findAllByCityAndState(String city, String state);
	
    List<TurfsEntity> findByCityAndStatus(String city, com.turftime.enums.TurfStatus status);
}
