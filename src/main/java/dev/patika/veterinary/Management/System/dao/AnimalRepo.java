package dev.patika.veterinary.Management.System.dao;

import dev.patika.veterinary.Management.System.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnimalRepo extends JpaRepository<Animal,Long> {

   List<Animal> findAllByCustomer_Name(String customerName);
   List<Animal> findAllByName (String animalName);

}
