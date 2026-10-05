package com.turftime.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turftime.entity.TurfResourceEntity;
import com.turftime.enums.ResourceStatus;

@Repository
public interface TurfResourceRepository extends JpaRepository<TurfResourceEntity, Long> {

	List<TurfResourceEntity> findAllByTurfIdAndStatusNot(Long id, ResourceStatus staus);
	
	Optional<TurfResourceEntity> findByTurfIdAndId(Long turfId, Long TurfResourceId);
	
}
