package com.example.gestionAlumni.DTO;

public class AlumniDto {
    private Long id;
    private String firstName;
    private String lastName;

    public AlumniDto(Long id, String firstName, String lastName) {
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
    }
}
