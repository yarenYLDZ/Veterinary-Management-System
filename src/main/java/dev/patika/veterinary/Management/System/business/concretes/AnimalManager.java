package dev.patika.veterinary.Management.System.business.concretes;

import dev.patika.veterinary.Management.System.business.abstracts.IAnimalService;
import dev.patika.veterinary.Management.System.core.exception.NotFoundException;
import dev.patika.veterinary.Management.System.core.utilies.Messages;
import dev.patika.veterinary.Management.System.dao.AnimalRepo;
import dev.patika.veterinary.Management.System.entities.Animal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepo animalRepo;

    public AnimalManager(AnimalRepo animalRepo) {
        this.animalRepo = animalRepo;
    }

    @Override
    public Animal save(Animal animal) {
        return this.animalRepo.save(animal);
    }

    @Override
    public List<Animal> getAllByCustomerName(String name) {
        List<Animal> animals = animalRepo.findAllByCustomer_Name(name.trim());
        if (animals.isEmpty()) {
            throw new NotFoundException(Messages.CUSTOMER_NOT_FOUND + name);
        }
        return animals;
    }

    @Override
    public List<Animal> getAllByAnimalName(String name) {
        List<Animal> animals = animalRepo.findAllByName(name.trim());
        if (animals.isEmpty()) {
            throw new NotFoundException(Messages.ANIMAL_NOT_FOUND + name);
        }
        return animals;
    }

    @Override
    public Animal get(Long id) {
        return animalRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(Messages.ANIMAL_NOT_FOUND + id));
    }



}
