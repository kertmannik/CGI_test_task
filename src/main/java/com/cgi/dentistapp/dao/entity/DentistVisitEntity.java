package com.cgi.dentistapp.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dentist_visit")
public class DentistVisitEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "visit_time")
    private Timestamp visitTime;

    @Column(name = "dentist_name")
    private String dentistName;

    @Column(name = "doctor_name")
    private String familyDoctorName;

    public DentistVisitEntity(Timestamp visitTime, String dentistName, String familyDoctorName) {
        this.visitTime = visitTime;
        this.dentistName = dentistName;

        this.familyDoctorName = familyDoctorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public  Timestamp getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Timestamp visitTime) {
        this.visitTime = visitTime;
    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public String getFamilyDoctorName() {
        return familyDoctorName;
    }

    public void setFamilyDoctorName(String familyDoctorName) {
        this.familyDoctorName = familyDoctorName;
    }
}
