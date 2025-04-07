package com.maddy.user.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "user_seq", initialValue = 101, allocationSize = 1)
@Table(name = "user")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_seq")
	private Integer userId;
	private String name;
	private String email;
	private String about;

	@Transient
	private List<Rating> ratingList = new ArrayList<>(); // It will not save in DB
}
