package com.example.dscomputeredu.registrationtest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationBO {
    private int reg_id;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile;
    private String dob;
    private String education;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String registration_date;
    private String gender;
    private String created_date;
    private String modified_date;
    private String father_name;
    private String course_name;
}
