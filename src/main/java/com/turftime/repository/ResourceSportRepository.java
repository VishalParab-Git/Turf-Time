package com.turftime.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turftime.entity.ResourceSportEntity;

@Repository
public interface ResourceSportRepository extends JpaRepository<ResourceSportEntity, Long> {

	List<ResourceSportEntity> findByResourceTurfIdAndResourceId(Long turfId, Long resourceId);
}
