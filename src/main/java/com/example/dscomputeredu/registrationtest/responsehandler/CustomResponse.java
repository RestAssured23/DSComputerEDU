package com.example.dscomputeredu.registrationtest.responsehandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse<T>{
    private int code;
    private String desc;
    private List<String> errors;
    private boolean success;
    private String name;
    private List<T> data;
}
