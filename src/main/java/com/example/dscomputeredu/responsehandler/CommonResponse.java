package com.example.dscomputeredu.responsehandler;

import com.example.dscomputeredu.model.GetCustomResponseBO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

