package com.example.gestionAlumni;


import lombok.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
}
