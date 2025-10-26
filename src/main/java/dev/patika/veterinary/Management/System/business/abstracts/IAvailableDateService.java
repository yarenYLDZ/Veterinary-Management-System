package dev.patika.veterinary.Management.System.business.abstracts;

import dev.patika.veterinary.Management.System.entities.AvailableDate;

import java.time.LocalDate;
import java.util.List;

public interface IAvailableDateService {
    AvailableDate save(Long doctorId, LocalDate availableDate);
    List<AvailableDate> getAllByDoctorId(Long doctorId);
    List<AvailableDate> getAvailableDatesByDoctorAndDateRange(Long doctorId, LocalDate start, LocalDate end);

}

