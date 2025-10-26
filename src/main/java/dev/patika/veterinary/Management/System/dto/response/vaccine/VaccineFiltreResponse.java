package dev.patika.veterinary.Management.System.dto.response.vaccine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@Getter
@AllArgsConstructor
public class VaccineFiltreResponse {


        private String vaccineName;
        private LocalDate protectionFinishDate;
        private Long animalId;
        private String animalName;

}
