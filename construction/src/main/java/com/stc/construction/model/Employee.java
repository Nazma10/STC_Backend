package com.stc.construction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String NIC;
    private String address;
    private String contact_no;
    private String joinedDate;
    private String role;
}