package com.cgi.dentistapp.visits;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class FamilyDoctor {

    private String firstName;
    private String lastName;

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public static List<String> getFullNames(List<FamilyDoctor> physicians){
        return physicians.stream()
                .map(FamilyDoctor::getFullName)
                .collect(Collectors.toList());
    }
}
