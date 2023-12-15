package com.example.dscomputeredu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomResponseBO<T> {
    private int code;
    private String desc;
    private List<String> errors;
    private boolean success;
    private String name;
 //   private T data;
 private List<T> data;
}
