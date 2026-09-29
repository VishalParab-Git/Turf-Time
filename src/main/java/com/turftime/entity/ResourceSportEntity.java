package com.turftime.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "resource_sports",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_resource_turf_sport",
            columnNames = {"resource_id", "turf_sport_id"}
        )
    }
)
@Getter

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceSportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resource_id", nullable = false)
    private TurfResourceEntity resource;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "turf_sport_id", nullable = false)
    private TurfSportsEntity turfSport;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
}