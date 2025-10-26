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

        // Aynı doktor ve tarih var mı kontrol et
        boolean exists = availableDateRepo
                .findAllByDoctorIdAndAvailableDateBetween(doctorId, availableDate, availableDate)
                .stream()
                .anyMatch(d -> d.getAvailableDate().equals(availableDate));

        if (exists) {
            throw new IllegalStateException("Bu doktor için aynı tarih zaten mevcut!");
        }

        AvailableDate date = new AvailableDate();
        date.setDoctor(doctor);
        date.setAvailableDate(availableDate);

        return availableDateRepo.save(date);
    }


    @Override
    public List<AvailableDate> getAllByDoctorId(Long doctorId) {
        // Tüm günleri almak için geniş bir tarih aralığı veriyoruz
        return availableDateRepo.findAllByDoctorIdAndAvailableDateBetween(
                doctorId,
                LocalDate.of(1900,1,1),
                LocalDate.of(3000,1,1)
        );
    }

    @Override
    public List<AvailableDate> getAvailableDatesByDoctorAndDateRange(Long doctorId, LocalDate start, LocalDate end) {
        // Tarih aralığına göre filtrelenmiş müsait günler
        return availableDateRepo.findAllByDoctorIdAndAvailableDateBetween(doctorId, start, end);
    }
}
