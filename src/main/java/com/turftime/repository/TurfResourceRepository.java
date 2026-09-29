package com.turftime.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turftime.entity.TurfResourceEntity;

@Repository
public interface TurfResourceRepository extends JpaRepository<TurfResourceEntity, Long> {

	Optional<List<TurfResourceEntity>> findAllByTurfId(Long id);
	
	TurfResourceEntity findByTurfIdAndId(Long turfId, Long TurfResourceId);
}
