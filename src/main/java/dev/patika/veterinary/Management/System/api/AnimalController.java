package dev.patika.veterinary.Management.System.api;

import dev.patika.veterinary.Management.System.business.abstracts.IAnimalService;
import dev.patika.veterinary.Management.System.business.abstracts.ICustomerService;
import dev.patika.veterinary.Management.System.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinary.Management.System.core.exception.NotFoundException;
import dev.patika.veterinary.Management.System.core.result.ResultData;
import dev.patika.veterinary.Management.System.core.utilies.ResultHelper;
import dev.patika.veterinary.Management.System.dto.request.animal.AnimalSaveRequest;
import dev.patika.veterinary.Management.System.dto.response.animal.AnimalResponse;
import dev.patika.veterinary.Management.System.dto.response.animal.AnimalSummaryResponse;
import dev.patika.veterinary.Management.System.entities.Animal;
import dev.patika.veterinary.Management.System.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {

    private final IAnimalService animalService;
    private final IModelMapperService modelMapper;
    private final ICustomerService customerService;

    public AnimalController(IAnimalService animalService, IModelMapperService modelMapper, ICustomerService customerService) {
        this.animalService = animalService;
        this.modelMapper = modelMapper;
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {

        // DTO -> Entity manuel mapping
        Animal saveAnimal = new Animal();
        saveAnimal.setName(animalSaveRequest.getName());
        saveAnimal.setSpecies(animalSaveRequest.getSpecies());
        saveAnimal.setBreed(animalSaveRequest.getBreed());
        saveAnimal.setGender(animalSaveRequest.getGender());
        saveAnimal.setColour(animalSaveRequest.getColour());
        saveAnimal.setDateOfBirth(animalSaveRequest.getDateOfBirth());

        // Customer ilişkilendirme
        Customer customer = this.customerService.get(animalSaveRequest.getCustomerId());
        saveAnimal.setCustomer(customer);

        // Kaydet
        this.animalService.save(saveAnimal);

        // Response dön
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAnimal, AnimalResponse.class));
    }



    @GetMapping("/by-customer-name")
    public ResultData<List<AnimalSummaryResponse>> getAnimalsByCustomerName(@RequestParam String name) {
        List<Animal> animals = animalService.getAllByCustomerName(name); // Boş liste -> NotFoundException servis içinde
        List<AnimalSummaryResponse> responseList = animals.stream()
                .map(a -> new AnimalSummaryResponse(a.getName(), a.getSpecies(), a.getBreed()))
                .collect(Collectors.toList());
        return ResultHelper.success(responseList);
    }

    @GetMapping("/by-animal-name")
    public ResultData<List<AnimalSummaryResponse>> getAnimalsByAnimalName(@RequestParam String name) {
        List<Animal> animals = animalService.getAllByAnimalName(name); // Boş liste -> NotFoundException servis içinde
        List<AnimalSummaryResponse> responseList = animals.stream()
                .map(a -> modelMapper.forResponse().map(a, AnimalSummaryResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(responseList);
    }





}
