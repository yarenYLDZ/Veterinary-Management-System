package dev.patika.veterinary.Management.System.business.abstracts;

import dev.patika.veterinary.Management.System.entities.Doctor;
import org.springframework.stereotype.Service;



public interface IDoctorService {

    Doctor save(Doctor doctor);
    Doctor get(Long id);

}
