package dev.patika.veterinary.Management.System.business.concretes;

import dev.patika.veterinary.Management.System.business.abstracts.IAvailableDateService;
import dev.patika.veterinary.Management.System.core.exception.NotFoundException;
import dev.patika.veterinary.Management.System.dao.AvailableDateRepo;
import dev.patika.veterinary.Management.System.dao.DoctorRepo;
import dev.patika.veterinary.Management.System.entities.AvailableDate;
import dev.patika.veterinary.Management.System.entities.Doctor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;
    private final DoctorRepo doctorRepo;

    public AvailableDateManager(AvailableDateRepo availableDateRepo, DoctorRepo doctorRepo) {
        this.availableDateRepo = availableDateRepo;
        this.doctorRepo = doctorRepo;
    }


    @Override
    public AvailableDate save(Long doctorId, LocalDate availableDate) {
        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("ID'si " + doctorId + " olan doktor bulunamadı."));
        AvailableDate date = new AvailableDate();
        date.setDoctor(doctor);
        date.setAvailableDate(availableDate);
        return availableDateRepo.save(date);
    }

    @Override
    public List<AvailableDate> getAllByDoctorId(Long doctorId) {
        return availableDateRepo.findAllByDoctorId(doctorId);
    }
}
