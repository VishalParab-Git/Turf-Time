package com.turftime.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "turfs_sports")
public class TurfSportsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "turf_id", foreignKey = @ForeignKey(name = "fk_turfsport_turf"), nullable = false)
	private TurfsEntity turf; // turfId

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sport_id", foreignKey = @ForeignKey(name = "fk_turfsport_sport"), nullable = false)
	private SportsEntity sport; // sportId

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private boolean active;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

}
