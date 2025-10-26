package dev.patika.veterinary.Management.System.dto.request.appointment;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentFilterByAnimalRequest {
    private Long animalId;
    private LocalDate startDate;
    private LocalDate endDate;
}
