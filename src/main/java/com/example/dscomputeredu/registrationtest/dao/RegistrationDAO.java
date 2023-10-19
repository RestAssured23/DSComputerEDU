package com.example.dscomputeredu.registrationtest.dao;

import com.example.dscomputeredu.registrationtest.model.CourseCompletionBO;
import com.example.dscomputeredu.registrationtest.model.RegistrationBO;

import java.util.List;
public interface RegistrationDAO {
    List<RegistrationBO> getall();

    List<RegistrationBO> getbyregid(int reg_id);

    List<RegistrationBO> insert(RegistrationBO registrationBO);

 //   List<RegistrationBO> insert(RegistrationBO registrationBO,CourseCompletionBO courseCompletionBO);

    List<CourseCompletionBO> getallcoursecompletion();


    List<CourseCompletionBO> getbycourseregid(int regId);
}
