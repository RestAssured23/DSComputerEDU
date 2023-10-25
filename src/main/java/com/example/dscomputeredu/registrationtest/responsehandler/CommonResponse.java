package com.example.dscomputeredu.registrationtest.responsehandler;

import com.example.dscomputeredu.registrationtest.model.GetCustomResponseBO;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class CommonResponse {
    public <T> GetCustomResponseBO<T> createCustomResponse(String name, T data) {
        GetCustomResponseBO<T> response = new GetCustomResponseBO<>();
        response.setCode(200);
        response.setDesc("success");
        response.setErrors(new ArrayList<>());
        response.setSuccess(true);
        response.setName(name);
        response.setData((List<T>) data);
        return response;
    }

  }
