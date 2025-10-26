package dev.patika.veterinary.Management.System.business.abstracts;

import dev.patika.veterinary.Management.System.dto.request.appointment.AppointmentFilterByDrRequest;
import dev.patika.veterinary.Management.System.dto.response.appointment.AppointmentResponse;
import dev.patika.veterinary.Management.System.entities.Appointment;
import dev.patika.veterinary.Management.System.entities.AvailableDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {

    List<Appointment> getAllByDoctorIdAndDateRange(Long doctorId, LocalDateTime start, LocalDateTime end);

    List<Appointment> getAllByAnimalIdAndDateRange(Long animalId, LocalDateTime start, LocalDateTime end);

    Appointment save(Long doctorId, Long animalId, LocalDateTime appointmentDateTime);

    List<AvailableDate> getAvailableDatesByDoctorAndDateRange(Long doctorId, LocalDate start, LocalDate end);

    List<AppointmentResponse> getAppointmentsByDoctorAndDateRange(AppointmentFilterByDrRequest request);
}
