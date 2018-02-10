package com.cgi.dentistapp.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.dentistapp.dao.DentistVisitDao;
import com.cgi.dentistapp.dao.entity.DentistVisitEntity;
import sun.security.krb5.internal.crypto.Des;

@Service
@Transactional
public class DentistVisitService {

    @Autowired
    private DentistVisitDao dentistVisitDao;

    public void addVisit(DentistVisitDTO dentistVisitDTO) {
        DentistVisitEntity visit = new DentistVisitEntity(Timestamp.valueOf(dentistVisitDTO.getVisitTime()), dentistVisitDTO.getDentistName(), dentistVisitDTO.getFamilyDoctorName());
        dentistVisitDao.create(visit);
    }

    public List<DentistVisitEntity> listVisits () {
        return dentistVisitDao.getAllVisits();
    }

    public boolean isVisitOverlaping(DentistVisitDTO dto){
        if (dentistVisitDao.countOverlaps(Timestamp.valueOf(dto.getVisitTime()), dto.getDentistName())>0){
            return true;
        }
        return false;
    }

}
