package com.qsp.sb_employee_management_system.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@NotBlank(message = "Please Enter Name")
@NotNull(message = "Name Can not Be Null")
private String name;
@Column(unique = true)
@NotBlank(message = "Please Enter Email ")
@NotNull(message = "email Can not Be Null")
@Email(regexp = "[a-z 0-9 . _ $]+@[a-z]+\\.[a-z]{2,5}",message="Please Enter Vali d EMail")
private String email;
@Column(unique = true)
@Min(value = 6000000000l)
@Max(value = 9999999999l)
private long phone;
@NotBlank(message = "Please Enter Address")
@NotNull(message = "address Can not Be Null")
private String address;
@Min(value = 1)
private double salary;
private char grade;
}
