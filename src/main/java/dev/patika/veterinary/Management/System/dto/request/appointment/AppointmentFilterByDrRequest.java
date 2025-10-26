package dev.patika.veterinary.Management.System.dto.request.appointment;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentFilterByDrRequest {
    private Long doctorId;
    private LocalDate startDate;
    private LocalDate endDate;
}
