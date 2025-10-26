package dev.patika.veterinary.Management.System.business.concretes;

import dev.patika.veterinary.Management.System.business.abstracts.IAppointmentService;
import dev.patika.veterinary.Management.System.business.abstracts.IDoctorService;
import dev.patika.veterinary.Management.System.business.abstracts.IAnimalService;
import dev.patika.veterinary.Management.System.business.abstracts.IAvailableDateService;
import dev.patika.veterinary.Management.System.core.exception.CustomException;
import dev.patika.veterinary.Management.System.dao.AppointmentRepo;
import dev.patika.veterinary.Management.System.dto.request.appointment.AppointmentFilterByDrRequest;
import dev.patika.veterinary.Management.System.dto.response.appointment.AppointmentResponse;
import dev.patika.veterinary.Management.System.entities.Appointment;
import dev.patika.veterinary.Management.System.entities.Doctor;
import dev.patika.veterinary.Management.System.entities.Animal;
import dev.patika.veterinary.Management.System.entities.AvailableDate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final IDoctorService doctorService;
    private final IAnimalService animalService;
    private final IAvailableDateService availableDateService;

    public AppointmentManager(AppointmentRepo appointmentRepo,
                              IDoctorService doctorService,
                              IAnimalService animalService,
                              IAvailableDateService availableDateService) {
        this.appointmentRepo = appointmentRepo;
        this.doctorService = doctorService;
        this.animalService = animalService;
        this.availableDateService = availableDateService;
    }

    @Override
    public List<Appointment> getAllByDoctorIdAndDateRange(Long doctorId, LocalDateTime start, LocalDateTime end) {
        return appointmentRepo.findByDoctorIdAndAppointmentDateTimeBetween(doctorId, start, end);
    }

    @Override
    public List<Appointment> getAllByAnimalIdAndDateRange(Long animalId, LocalDateTime start, LocalDateTime end) {
        Animal animal = animalService.get(animalId);
        return appointmentRepo.findByAnimalAndAppointmentDateTimeBetween(animal, start, end);
    }

    @Override
    public Appointment save(Long doctorId, Long animalId, LocalDateTime appointmentDateTime) {
        Doctor doctor = doctorService.get(doctorId);
        Animal animal = animalService.get(animalId);

        List<Appointment> existing = appointmentRepo.findByDoctorAndAppointmentDateTime(doctor, appointmentDateTime);
        if (!existing.isEmpty()) {
            throw new CustomException("Girilen saatte doktorun başka bir randevusu mevcut!");
        }

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setAnimal(animal);
        appointment.setAppointmentDateTime(appointmentDateTime);

        return appointmentRepo.save(appointment);
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByDoctorAndDateRange(AppointmentFilterByDrRequest request) {
        List<Appointment> appointments = getAllByDoctorIdAndDateRange(
                request.getDoctorId(),
                request.getStartDate().atStartOfDay(),
                request.getEndDate().atTime(23, 59, 59)
        );

        return appointments.stream().map(app -> {
            AppointmentResponse response = new AppointmentResponse();
            response.setAppointmentId(app.getId());
            response.setAppointmentDateTime(app.getAppointmentDateTime());
            response.setCustomerId(app.getAnimal().getId());
            response.setCustomerName(app.getAnimal().getName());
            response.setDoctorId(app.getDoctor().getId());
            response.setDoctorName(app.getDoctor().getName());
            return response;
        }).collect(Collectors.toList());
    }

    // 🔥 İşte eksik olan method — birebir interface ile aynı imzayla!
    @Override
    public List<AvailableDate> getAvailableDatesByDoctorAndDateRange(Long doctorId, LocalDate start, LocalDate end) {
        return availableDateService.getAvailableDatesByDoctorAndDateRange(doctorId, start, end);
    }
}
