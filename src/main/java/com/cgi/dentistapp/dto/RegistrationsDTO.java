package com.cgi.dentistapp.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class RegistrationsDTO {

    @Size(max = 50)
    private String dentistName;

    @Size(max = 50)
    private String physicianName;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime visitTime;
}
