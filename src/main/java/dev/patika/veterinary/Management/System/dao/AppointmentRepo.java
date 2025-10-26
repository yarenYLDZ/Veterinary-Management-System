package dev.patika.veterinary.Management.System.dao;

import dev.patika.veterinary.Management.System.entities.Appointment;
import dev.patika.veterinary.Management.System.entities.Doctor;
import dev.patika.veterinary.Management.System.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    // Aynı doktor ve saatte randevu var mı kontrolü
    List<Appointment> findByDoctorAndAppointmentDateTime(Doctor doctor, LocalDateTime dateTime);

    // Doktor ID ve tarih aralığına göre randevular
    List<Appointment> findByDoctorIdAndAppointmentDateTimeBetween(Long doctorId, LocalDateTime start, LocalDateTime end);
    // Belirli tarih aralığında ve hayvana göre filtreleme
    List<Appointment> findByAnimalAndAppointmentDateTimeBetween(Animal animal, LocalDateTime start, LocalDateTime end);
}
