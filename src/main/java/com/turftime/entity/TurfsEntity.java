package com.turftime.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.turftime.enums.TurfStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="turfs")
public class TurfsEntity {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(
			name="owner_id",
			foreignKey=@ForeignKey(name="fk_turfs_owner"),
			nullable=false
			)
	private TurfOwnersEntity owner;	//ownerId
	
	@Column(name="turf_name", nullable=false, length=200)
	private String name;
	
	@Column(name="description",nullable=false,length=500)
	private String description;
	
	@Column(name="address",nullable=false, length=250)
	private String address;
	
	@Column(name="city",nullable=false, length=100)
	private String city;
	
	@Column(name="state",nullable=false, length=100)
	private String state;
	
	@Column(name="pincode",nullable=false, length=20)
	private String pincode;
	
	@Column(name="opening_time",nullable=false, length=20)
	private LocalTime openingTime;
	
	@Column(name="closing_time",nullable=false, length=20)
	private LocalTime closingTime;
	
	@Enumerated(EnumType.STRING)
    @Column(name="status", nullable=false, length=30)
	private TurfStatus status;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime UpdatedAt;

}
