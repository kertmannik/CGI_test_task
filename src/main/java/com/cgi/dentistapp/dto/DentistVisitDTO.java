package com.cgi.dentistapp.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by serkp on 2.03.2017.
 */

@NoArgsConstructor
@AllArgsConstructor
public class DentistVisitDTO {

    @Size(min = 1, max = 50)
    String dentistName;

    @Size(min = 1, max = 50)
    String familyDoctorName;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    LocalDateTime visitTime;

    public DentistVisitDTO(String dentistName,  LocalDateTime visitTime, String familyDoctorName) {
        this.dentistName = dentistName;
        this.visitTime = visitTime;
        this.familyDoctorName = familyDoctorName;
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

    public void setfamilyDoctorName(String familyDoctorName) {
        this.familyDoctorName = familyDoctorName;
    }

    public  LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime( LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }
}
