package dev.patika.veterinary.Management.System.dto.response.vaccine;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class VaccineAnimalResponse {
    private Long vaccineId;
    private String vaccineName;
    private String vaccineCode;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;

    private Long animalId;
    private String animalName;
}
