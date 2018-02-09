package com.cgi.dentistapp.dao.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dentist_visit")
public class DentistVisitEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "visit_time")
    private LocalDateTime visitTime;

    @Column(name = "dentist_name")
    private String dentistName;

    @Column(name = "doctor_name")
    private String doctorName;

    public DentistVisitEntity(LocalDateTime visitTime, String dentistName, String doctorName) {
        this.visitTime = visitTime;
        this.dentistName = dentistName;
        this.doctorName = doctorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public  LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime( LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }

}
