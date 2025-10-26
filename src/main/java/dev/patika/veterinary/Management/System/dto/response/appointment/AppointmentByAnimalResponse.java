package dev.patika.veterinary.Management.System.dto.response.appointment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentByAnimalResponse {
    private Long appointmentId;
    private LocalDateTime appointmentDateTime;
    private String doctorName;
}
