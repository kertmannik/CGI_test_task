package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.dto.RegistrationsDTO;
import com.cgi.dentistapp.visits.FamilyDoctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter {

    @Autowired
    private DentistVisitService dentistVisitService;
    Logger logger = LoggerFactory.getLogger(DentistAppController.class);

    // Dropdown listi perearstide nimed
    private final List<FamilyDoctor> familyDoctors = Arrays.asList(
            new FamilyDoctor("Mrs", "Doctor"),
            new FamilyDoctor("Mr", "Doctor")
    );

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
        registry.addViewController("/overlaping").setViewName("overlaping");
    }

    @GetMapping("/")
    public String showRegisterForm(DentistVisitDTO dentistVisitDTO, Model model) {
        model.addAttribute("familyDoctors", familyDoctors);
        return "form";
    }

    @PostMapping("/")
    public String postRegisterForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult, Model model) {
        model.addAttribute("familyDoctors", familyDoctors);
        if (bindingResult.hasErrors()) {
            logger.error("Error in registerform posting!");
            return "form";
        }

        // Kontrollib, kas on visiitide kattuvusi
        if(dentistVisitService.isVisitOverlaping(dentistVisitDTO)){
            logger.error("Error in registerform posting: overlaping visits!");
            return "redirect:/overlaping";
        }

        // Veateadete puudumisel lisab visiidi andmebaasi
        dentistVisitService.addVisit(dentistVisitDTO);
        logger.info("Successful added an visit:" +
                dentistVisitDTO.getDentistName() + ", " +
                dentistVisitDTO.getFamilyDoctorName() + ", " +
                dentistVisitDTO.getVisitTime());
        return "redirect:/results";
    }

    // Visiitide kuvamine visits lehel
    @GetMapping("visits")
    public String showRegistrations(Model model,
                             @ModelAttribute("searchQuery") RegistrationsDTO dto) {
        model.addAttribute("familyDoctors", familyDoctors);
        model.addAttribute("registrationsDTOs", dentistVisitService.listVisits());
        return "visits";
    }
}
