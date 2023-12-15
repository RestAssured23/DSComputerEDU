package com.example.dscomputeredu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginBO {
    private int reg_id;
    private String email;
    private String password;
    private String acc_createddate;
    private String registration_date;

    public LoginBO(String dbUpdateFailed, Object o, Object o1, int i) {
    }
}
