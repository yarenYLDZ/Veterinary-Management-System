package dev.patika.veterinary.Management.System.dao;

import dev.patika.veterinary.Management.System.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Long> {
    // Bir hayvana ait tüm aşılar
    List<Vaccine> findByAnimalId(Long animalId);

    // Aşı koruyuculuk bitiş tarihine göre filtreleme
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate start, LocalDate end);

    // Aynı hayvanda aynı kodlu aşılar
    List<Vaccine> findByAnimalIdAndCode(Long animalId, String code);

    // 🔹 Belirli hayvana ait ve bitiş tarihine göre filtreleme
    List<Vaccine> findByAnimalIdAndProtectionFinishDateBetween(Long animalId, LocalDate start, LocalDate end);
}