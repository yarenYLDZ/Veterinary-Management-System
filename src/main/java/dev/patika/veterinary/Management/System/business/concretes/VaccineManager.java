package dev.patika.veterinary.Management.System.business.concretes;

import dev.patika.veterinary.Management.System.business.abstracts.IVaccineService;
import dev.patika.veterinary.Management.System.core.exception.ValidationException;
import dev.patika.veterinary.Management.System.core.result.ResultData;
import dev.patika.veterinary.Management.System.core.utilies.ResultHelper;
import dev.patika.veterinary.Management.System.dao.VaccineRepo;
import dev.patika.veterinary.Management.System.dto.request.vaccine.VaccinaByAnimal;
import dev.patika.veterinary.Management.System.dto.response.vaccine.VaccineAnimalResponse;
import dev.patika.veterinary.Management.System.dto.response.vaccine.VaccineFiltreResponse;
import dev.patika.veterinary.Management.System.entities.Animal;
import dev.patika.veterinary.Management.System.entities.Vaccine;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccineManager implements IVaccineService {

    private final VaccineRepo vaccineRepo;

    public VaccineManager(VaccineRepo vaccineRepo) {
        this.vaccineRepo = vaccineRepo;
    }

    @Override
    public Vaccine save(Vaccine vaccine) {

        Animal animal = vaccine.getAnimal();
        if (animal == null || animal.getId() == null) {
            throw new ValidationException("Vaccine", "Hayvan bilgisi eksik veya geçersiz.");
        }

        // Aynı hayvanda aynı kodlu aşı varsa kontrol et
        List<Vaccine> existingVaccines = vaccineRepo.findByAnimalIdAndCode(animal.getId(), vaccine.getCode());

        for (Vaccine existingVaccine : existingVaccines) {
            // Yeni aşı başlangıç tarihi mevcut aşının bitiş tarihinden önce veya eşit ise çakışma var
            if (!vaccine.getProtectionStartDate().isAfter(existingVaccine.getProtectionFinishDate())) {
                throw new ValidationException("Vaccine",
                        "Bu aşı zaten hayvanda mevcut ve koruyuculuk bitmemiş veya çakışıyor.");
            }
        }

        return vaccineRepo.save(vaccine);
    }

    @Override
    public List<Vaccine> getAllByAnimalId(Long animalId) {
        return vaccineRepo.findByAnimalId(animalId);
    }

    @Override
    public List<VaccineAnimalResponse> getVaccinesWithAnimalByDateRange(LocalDate start, LocalDate end) {
        List<Vaccine> vaccines = vaccineRepo.findByProtectionFinishDateBetween(start, end);

        return vaccines.stream()
                .map(v -> new VaccineAnimalResponse(
                        v.getId(),
                        v.getName(),
                        v.getCode(),
                        v.getProtectionStartDate(),
                        v.getProtectionFinishDate(),
                        v.getAnimal().getId(),
                        v.getAnimal().getName()
                ))
                .toList();
    }

    // 🔹 Bitim tarihine göre filtreleme ve sadece gerekli alanları döndür
    @Override
    public List<VaccineFiltreResponse> getVaccinesByFinishDateRange(LocalDate start, LocalDate end) {
        List<Vaccine> vaccines = vaccineRepo.findByProtectionFinishDateBetween(start, end);

        return vaccines.stream()
                .map(v -> new VaccineFiltreResponse(
                        v.getName(),                   // vaccineName
                        v.getProtectionFinishDate(),   // protectionFinishDate
                        v.getAnimal().getId(),         // animalId
                        v.getAnimal().getName()        // animalName
                ))
                .toList();
    }


    @Override
    public List<VaccineFiltreResponse> getVaccinesByAnimalId(Long animalId) {
        List<Vaccine> vaccines = vaccineRepo.findByAnimalId(animalId);

        return vaccines.stream()
                .map(v -> new VaccineFiltreResponse(
                        v.getName(),
                        v.getProtectionFinishDate(),
                        v.getAnimal().getId(),
                        v.getAnimal().getName()
                ))
                .toList();
    }



}
