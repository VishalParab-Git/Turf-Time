package com.turftime.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;

import com.turftime.enums.BookingStatus;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingsEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="booking_number",nullable=false,unique=true)
	private String bookingNumber;	//book code
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(
			name="user_id",
			foreignKey=@ForeignKey(name="fk_booking_user")
			,nullable=false)
	private UserEntity user;		//user_id
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(
			name="turf_id",
			foreignKey=@ForeignKey(name="fk_booking_turfs")
			,nullable=false)
	private TurfsEntity turf;		//turf_id
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(
			name="turf_resource_id",
			foreignKey=@ForeignKey(name="fk_booking_Turf_resource"),
			nullable=false
			)
	private TurfResourceEntity turfResource;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(
			name="sport_id",
			foreignKey=@ForeignKey(name="fk_booking_sport")
			,nullable=false)
	private TurfSportsEntity sport;  	//sport_id
	
	@Column(name="booking_date",nullable=false)
	private LocalDate bookingDate;
	
	@Column(name="start_time",nullable=false)
	private LocalTime startTime;
	
	@Column(name="end_time",nullable=false)
	private LocalTime endTime;
	
	@Column(name="amount",nullable=false)
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	@Column(name="booking_status",nullable=false)
	private BookingStatus status;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	
}
