package com.turftime.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turftime.entity.TurfSportsEntity;

@Repository
public interface TurfSportsRepository  extends JpaRepository<TurfSportsEntity,Long>{

	Optional<List<TurfSportsEntity>> findAllByTurfId(Long id);
	
    List<TurfSportsEntity> findByTurfIdAndActive(Long turfId, boolean active);
    
    List<TurfSportsEntity> findByTurfCityIgnoreCaseAndSportIdAndActiveTrue(String city, Long sportId);
}
