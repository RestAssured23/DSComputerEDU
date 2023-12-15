package com.example.dscomputeredu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfoBO<T> {
    private int reg_id;
    private String name;
    private String email;
    private String password;
    private String mobile;
    private String coursename;
    private char flag ;
    private String registration_date;
}
