package dev.patika.veterinary.Management.System.dto.response.animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalSummaryResponse {
    private String name;
    private String species;
    private String breed;
}
