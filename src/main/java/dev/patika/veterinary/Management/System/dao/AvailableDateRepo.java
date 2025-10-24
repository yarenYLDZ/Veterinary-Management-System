package dev.patika.veterinary.Management.System.dao;

import dev.patika.veterinary.Management.System.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate, Long> {
    List<AvailableDate> findAllByDoctorId(Long doctorId);
}

