package dev.patika.veterinary.Management.System.dto.response.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {
    private Long appointmentId;
    private LocalDateTime appointmentDateTime;
    private Long doctorId;
    private String doctorName;
    private Long animalId;
    private String animalName;
    private Long customerId;
    private String customerName;
}

