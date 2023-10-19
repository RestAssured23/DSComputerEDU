package com.example.dscomputeredu.registrationtest.dao;

import com.example.dscomputeredu.registrationtest.model.RegistrationBO;

import java.util.List;
public interface RegistrationDAO {
    List<RegistrationBO> getall();

    List<RegistrationBO> getbyregid(int reg_id);

    List<RegistrationBO> insert(RegistrationBO registrationBO);
}
