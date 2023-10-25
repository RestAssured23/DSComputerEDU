package com.example.dscomputeredu.registrationtest.dao;

import com.example.dscomputeredu.registrationtest.model.CourseCompletionBO;
import com.example.dscomputeredu.registrationtest.model.LoginBO;
import com.example.dscomputeredu.registrationtest.model.RegistrationBO;

import java.util.List;
public interface RegistrationDAO {
    List<RegistrationBO> getall();

    List<RegistrationBO> getbyregid(int reg_id);

    List<RegistrationBO> insert(RegistrationBO registrationBO);

 //   List<RegistrationBO> insert(RegistrationBO registrationBO,CourseCompletionBO courseCompletionBO);

    List<CourseCompletionBO> getallcoursecompletion();


    List<CourseCompletionBO> getbycourseregid(int regId);
   // List<LoginBO> getAllLogin();

    List<LoginBO> postlogin();

    List<LoginBO> getAllLogin();

    LoginBO getLoginDetails(int regID);
}

