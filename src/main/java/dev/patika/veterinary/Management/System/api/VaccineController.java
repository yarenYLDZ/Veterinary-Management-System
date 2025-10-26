package dev.patika.veterinary.Management.System.api;

import dev.patika.veterinary.Management.System.business.abstracts.IVaccineService;
import dev.patika.veterinary.Management.System.core.result.ResultData;
import dev.patika.veterinary.Management.System.core.utilies.ResultHelper;
import dev.patika.veterinary.Management.System.dto.request.vaccine.VaccinaByAnimal;
import dev.patika.veterinary.Management.System.dto.request.vaccine.VaccineFiltreRequest;
import dev.patika.veterinary.Management.System.dto.response.vaccine.VaccineFiltreResponse;
import dev.patika.veterinary.Management.System.entities.Vaccine;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {

    private final IVaccineService vaccineService;

    public VaccineController(IVaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    // Yeni aşı ekleme
    @PostMapping
    public ResultData<Vaccine> addVaccine(@RequestBody Vaccine vaccine){
        Vaccine saved = vaccineService.save(vaccine);
        return ResultHelper.created(saved);
    }

    @PostMapping("/by-animal")
    public ResultData<List<VaccineFiltreResponse>> getVaccinesByAnimal(
            @RequestBody VaccinaByAnimal request) {

        List<VaccineFiltreResponse> vaccines = vaccineService.getVaccinesByAnimalId(request.getAnimalId());
        return ResultHelper.success(vaccines);
    }



    // 🔹 Koruyuculuk bitiş tarihine göre filtreleme
    @PostMapping("/by-protection-date")
    public ResultData<List<VaccineFiltreResponse>> getVaccinesByFinishDateRange(
            @RequestBody VaccineFiltreRequest request){

        List<VaccineFiltreResponse> result = vaccineService.getVaccinesByFinishDateRange(
                request.getStart(),
                request.getEnd()
        );
        return ResultHelper.success(result);
    }
}
