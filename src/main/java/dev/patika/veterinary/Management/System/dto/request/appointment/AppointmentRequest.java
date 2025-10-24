package dev.patika.veterinary.Management.System.dto.request.appointment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentRequest {

    @NotNull(message = "Doktor bilgisi boş olamaz")
    private Long doctorId;

    @NotNull(message = "Hayvan bilgisi boş olamaz")
    private Long animalId;

    @NotNull(message = "Randevu tarihi boş olamaz")
    @Future(message = "Randevu tarihi geçmiş olamaz")
    private LocalDateTime appointmentDateTime;
}
