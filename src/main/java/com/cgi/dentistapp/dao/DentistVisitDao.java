package com.cgi.dentistapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cgi.dentistapp.dao.entity.DentistVisitEntity;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class DentistVisitDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(DentistVisitEntity visit) {
        entityManager.persist(visit);
    }

    /**
     * Tagastab andmebaasist kõik visiidid.
     *
     * @return DentistVisitEntity objektide list, mis on hetkel andmebaasis
     */
    public List<DentistVisitEntity> getAllVisits() {
        return entityManager.createQuery("SELECT e FROM DentistVisitEntity e").getResultList();
    }

    /**
     * Kontrollib, kas on juba tehtud registreering sama arsti juurde sama kellaaja ning kuupäevaga.
     *
     * @return Kattuvate visiitide koguarv
     */
    public long countOverlaps(Timestamp visitTime, String dentistName) {
        return entityManager.createQuery(
                "SELECT COUNT (e) FROM DentistVisitEntity e " +
                        " WHERE ((e.visitTime = :visitTime) " +
                        " AND (e.dentistName = :dentistName))", Long.class)
                .setParameter("visitTime", visitTime)
                .setParameter("dentistName", dentistName)
                .getSingleResult();
    }
}
