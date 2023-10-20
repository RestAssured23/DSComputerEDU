package com.example.dscomputeredu.registrationtest.responsehandler;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class CommonResponse {
    public <T> CustomResponseBO<T> createCustomResponse(String name, T data) {
        CustomResponseBO<T> response = new CustomResponseBO<>();
        response.setCode(200);
        response.setDesc("success");
        response.setErrors(new ArrayList<>());
        response.setSuccess(true);
        response.setName(name);
        response.setData((List<T>) data);
        return response;
    }
  }
