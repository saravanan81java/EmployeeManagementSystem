package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data ;


@Entity
@Data
public class Persons {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column
	private String name;

}
