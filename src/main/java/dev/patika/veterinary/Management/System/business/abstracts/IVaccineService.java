package dev.patika.veterinary.Management.System.business.abstracts;

import dev.patika.veterinary.Management.System.dto.response.vaccine.VaccineAnimalResponse;
import dev.patika.veterinary.Management.System.dto.response.vaccine.VaccineFiltreResponse;
import dev.patika.veterinary.Management.System.entities.Vaccine;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    Vaccine save(Vaccine vaccine);
    List<Vaccine> getAllByAnimalId(Long animalId);
    List<VaccineAnimalResponse> getVaccinesWithAnimalByDateRange(LocalDate start, LocalDate end);
    List<VaccineFiltreResponse> getVaccinesByAnimalId(Long animalId);

    List<VaccineFiltreResponse> getVaccinesByFinishDateRange(LocalDate start, LocalDate end);
}
