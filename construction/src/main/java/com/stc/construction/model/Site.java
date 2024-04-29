package com.stc.construction.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Site {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer site_id;
    private String firstname;
    private String lastname;
    private String email;
    private String NIC;
    private String contact_no;
    private String address;
    private Date start_date;
    private Date end_date;
    private String status;
}