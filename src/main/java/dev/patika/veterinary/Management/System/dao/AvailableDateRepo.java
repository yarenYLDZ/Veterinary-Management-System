package dev.patika.veterinary.Management.System.dao;

import dev.patika.veterinary.Management.System.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate, Long> {
    List<AvailableDate> findAllByDoctorIdAndAvailableDateBetween(Long doctorId, LocalDate startDate, LocalDate endDate);
}


