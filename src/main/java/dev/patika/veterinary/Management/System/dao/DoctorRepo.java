package dev.patika.veterinary.Management.System.dao;

import dev.patika.veterinary.Management.System.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Long>{


}
