package dev.patika.veterinary.Management.System.business.concretes;

import dev.patika.veterinary.Management.System.business.abstracts.IAppointmentService;
import dev.patika.veterinary.Management.System.business.abstracts.IDoctorService;
import dev.patika.veterinary.Management.System.business.abstracts.IAnimalService;
import dev.patika.veterinary.Management.System.core.exception.CustomException;
import dev.patika.veterinary.Management.System.dao.AppointmentRepo;
import dev.patika.veterinary.Management.System.entities.Appointment;
import dev.patika.veterinary.Management.System.entities.Doctor;
import dev.patika.veterinary.Management.System.entities.Animal;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final IDoctorService doctorService;
    private final IAnimalService animalService;

    public AppointmentManager(AppointmentRepo appointmentRepo, IDoctorService doctorService, IAnimalService animalService) {
        this.appointmentRepo = appointmentRepo;
        this.doctorService = doctorService;
        this.animalService = animalService;
    }

    @Override
    public List<Appointment> getAllByDoctorIdAndDateRange(Long doctorId, LocalDateTime start, LocalDateTime end) {
        Doctor doctor = doctorService.get(doctorId);
        return appointmentRepo.findByDoctorAndAppointmentDateTimeBetween(doctor, start, end);
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
}
