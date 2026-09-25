package com.turftime.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name="turf_owner")
public class TurfOwnersEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(
			name="user_id",
			foreignKey=@ForeignKey(name="fk_owner_user"),
			nullable=false,
			unique=true
			)
	private UserEntity User;
	
	@Column(name="owner_name",nullable=false,length=200)
	private String ownerName;
	
	@Column(name="phone",nullable=false, length=10)
	private String phone;
	
	@Column(name="business_name", unique=true, nullable=false, length=200)
	private String businessName;
	
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TurfsEntity> turfs=new ArrayList<>();
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;

}
