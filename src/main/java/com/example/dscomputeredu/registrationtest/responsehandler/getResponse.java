package com.example.dscomputeredu.registrationtest.responsehandler;

import com.example.dscomputeredu.registrationtest.model.GetCustomResponseBO;
import com.example.dscomputeredu.registrationtest.model.getResponseBO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class getResponse {
    private final ObjectMapper objectMapper;
@Autowired
    public getResponse(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> getResponseBO<T> createGetResponse(String name, T data) {
        getResponseBO<T> response = new getResponseBO<>();
        response.setCode(200);
        response.setDesc("success");
        response.setErrors(new ArrayList<>());
        response.setSuccess(true);
        response.setName(name);
        response.setData(data);
        return response;
    }
}
/*
  }
    public <T> getResponseBO<T> createGetResponse(String name, T data) {
        getResponseBO<T> response = new getResponseBO<>();
        response.setCode(200);
        response.setDesc("success");
        response.setErrors(new ArrayList<>());
        response.setSuccess(true);
        response.setName(name);
        response.setData(data); // Set data without casting to List<T>
        return response;
    }*/
