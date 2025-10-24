package dev.patika.veterinary.Management.System.business.concretes;


import dev.patika.veterinary.Management.System.business.abstracts.IDoctorService;
import dev.patika.veterinary.Management.System.core.exception.NotFoundException;
import dev.patika.veterinary.Management.System.dao.DoctorRepo;
import dev.patika.veterinary.Management.System.entities.Doctor;
import org.springframework.stereotype.Service;

@Service
public class DoctorManager implements IDoctorService {

    private final DoctorRepo doctorRepo;

    public DoctorManager(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepo.save(doctor);
    }

    @Override
    public Doctor get(Long id) {
        return doctorRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Doktor bulunamadı! ID: " + id));

    }

}
