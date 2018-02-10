package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.dto.RegistrationsDTO;
import com.cgi.dentistapp.visits.FamilyDoctor;
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
            return "form";
        }

        if(dentistVisitService.isOverlaping(dentistVisitDTO)){
            return "redirect:/overlaping";
        }

        dentistVisitService.addVisit(dentistVisitDTO);
        return "redirect:/results";
    }

    @GetMapping("visits")
    public String showRegistrations(Model model,
                             @ModelAttribute("searchQuery") RegistrationsDTO dto) {
        model.addAttribute("familyDoctors", familyDoctors);
        model.addAttribute("registrationsDTOs", dentistVisitService.listVisits());
        return "visits";
    }
}
