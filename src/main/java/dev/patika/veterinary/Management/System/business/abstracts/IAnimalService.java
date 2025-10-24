package dev.patika.veterinary.Management.System.business.abstracts;

import dev.patika.veterinary.Management.System.entities.Animal;

import java.util.List;

public interface IAnimalService {

    Animal save(Animal animal);
    List<Animal> getAllByCustomerName(String customerName);
    List<Animal> getAllByAnimalName(String animalName);
    Animal get(Long id);


}
