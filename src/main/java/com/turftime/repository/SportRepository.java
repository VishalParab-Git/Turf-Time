package com.turftime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turftime.entity.SportsEntity;

@Repository
public interface SportRepository extends JpaRepository<SportsEntity, Long> {

}
