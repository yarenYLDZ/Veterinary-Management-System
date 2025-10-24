package dev.patika.veterinary.Management.System.dto.request.animal;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalSaveRequest {


    @NotBlank
    private String name;
    @NotBlank
    private String species;     // Tür
    @NotBlank
    private String breed;       // Irk
    @NotBlank
    private String gender;      // Cinsiyet
    @NotBlank
    private String colour;      // Renk
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private Long customerId;


  
}
