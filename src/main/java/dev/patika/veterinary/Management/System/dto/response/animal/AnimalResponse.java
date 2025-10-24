package dev.patika.veterinary.Management.System.dto.response.animal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.patika.veterinary.Management.System.entities.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalResponse {

    private Long id;
    private String name;
    private String species;     // Tür
    private String breed;       // Irk
    private String gender;      // Cinsiyet
    private String colour;      // Renk
    private LocalDate dateOfBirth;
    private Long customerId;

}
