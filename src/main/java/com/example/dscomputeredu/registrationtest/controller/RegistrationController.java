package com.example.dscomputeredu.registrationtest.controller;

import com.example.dscomputeredu.registrationtest.dao.RegistrationDAO;
import com.example.dscomputeredu.registrationtest.model.RegistrationBO;
import com.example.dscomputeredu.registrationtest.responsehandler.CustomResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/test")
public class RegistrationController {
private final RegistrationDAO registrationDAO;

    public RegistrationController(RegistrationDAO registrationDAO) {
        this.registrationDAO = registrationDAO;
    }

    @GetMapping("/reg/all")
    public CustomResponse<RegistrationBO> getallstudent() {
        CustomResponse<RegistrationBO> response = new CustomResponse<>();
        response.setCode(200);
        response.setDesc("success");
        response.setErrors(new ArrayList<>()); // You can add error messages if needed
        response.setSuccess(true);
        response.setName("StudentDetails");
        response.setData(registrationDAO.getall());
        return response;
    }


    @GetMapping("/reg/regid")
    public CustomResponse<RegistrationBO> regid(@RequestParam int reg_id) {
        CustomResponse<RegistrationBO> response = new CustomResponse<>();
        response.setCode(200);
        response.setDesc("success");
        response.setErrors(new ArrayList<>());
        response.setSuccess(true);
        response.setName("StudentDetails");
        response.setData(registrationDAO.getbyregid(reg_id));
        return response;
    }

    @PostMapping("/reg/save")
    public CustomResponse<RegistrationBO> newregistration(@RequestBody RegistrationBO registrationBO){
        CustomResponse<RegistrationBO> response = new CustomResponse<>();
        response.setCode(200);
        response.setDesc("success");
        response.setErrors(new ArrayList<>()); // You can add error messages if needed
        response.setSuccess(true);
        response.setName("StudentDetailsInsert");
        response.setData(registrationDAO.insert(registrationBO));
        return response;
    }
}
