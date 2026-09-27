package com.turftime.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turftime.entity.TurfsEntity;

@Repository
public interface TurfRepository  extends JpaRepository<TurfsEntity, Long>{

	Optional<TurfsEntity> findByOwnerUserUsername(String username);
	
	//Optional<TurfsEntity> findAllOwnerUserUsername(String username);
}
