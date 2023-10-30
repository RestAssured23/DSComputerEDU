package com.example.dscomputeredu.registrationtest.dao;

import com.example.dscomputeredu.registrationtest.model.CourseCompletionBO;
import com.example.dscomputeredu.registrationtest.model.LoginBO;
import com.example.dscomputeredu.registrationtest.model.RegistrationBO;
import javassist.NotFoundException;

import java.util.List;
public interface RegistrationDAO {
    List<RegistrationBO> getall();
    List<RegistrationBO> getbyregid(int reg_id) throws NotFoundException;
    List<RegistrationBO> insert(RegistrationBO registrationBO);
    List<CourseCompletionBO> getallcoursecompletion();
    List<CourseCompletionBO> getbycourseregid(int regId);
    List<LoginBO> getAllLogin();

   // LoginBO getAllLogin();
    LoginBO getLoginDetails(int regID);

    List<LoginBO> createLogin(LoginBO loginBO);
}

