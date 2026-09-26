package com.turftime.entity;

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
@Table(name = "user_profile_table")
public class UserProfileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(
			name="user_id",
			referencedColumnName="id",
			foreignKey=@ForeignKey(name="fk_profile_user"),
			nullable=false,
			unique=true
			
			)
	private UserEntity user;			//userId

	@Column(name="first_name", nullable=false, length=100)
	private String firstName;
	
	@Column(name="last_name", nullable=false, length=100)
	private String lastName;
	
	@Column(name="phone", nullable=false, length=10)
	private String phone;
	
	@Column(name="address", nullable=false, length=200)
	private String address;
	
	@Column(name="city", nullable=false, length=100)
	private String city;
	
	@Column(name="state", nullable=false, length=100)
	private String state;
	
	@Column(name="country", nullable=false, length=100)
	private String country;
	
	@Column(name="pincode", nullable=false, length=100)
	private String pincode;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
}
