package com.cgi.dentistapp.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.dentistapp.dao.DentistVisitDao;
import com.cgi.dentistapp.dao.entity.DentistVisitEntity;

@Service
@Transactional
public class DentistVisitService {

    @Autowired
    private DentistVisitDao dentistVisitDao;

    /**
     * Visiidi lisamine andmebaasi.
     *
     * @param dentistVisitDTO objekt kogu vajaliku infoga andmebaasi lisamiseks: hambaarst, perearst, visiidi aeg
     */
    public void addVisit(DentistVisitDTO dentistVisitDTO) {
        DentistVisitEntity visit = new DentistVisitEntity(Timestamp.valueOf(dentistVisitDTO.getVisitTime()), dentistVisitDTO.getDentistName(), dentistVisitDTO.getFamilyDoctorName());
        dentistVisitDao.create(visit);
    }

    /**
     * @return Hetkel andmebaasis olevad visiidid
     */
    public List<DentistVisitEntity> listVisits () {
        return dentistVisitDao.getAllVisits();
    }

    /**
     * Loendab kattuvaid visiite
     *
     * @param dto, dto koos kogu vajaliku infoga
     * @return Kattuvate visiitide koguarv
     */
    public boolean isVisitOverlaping(DentistVisitDTO dto){
        if (dentistVisitDao.countOverlaps(Timestamp.valueOf(dto.getVisitTime()), dto.getDentistName())>0){
            return true;
        }
        return false;
    }

}
