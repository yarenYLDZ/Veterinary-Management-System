package dev.patika.veterinary.Management.System.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column (name="customer_name")
    private String name;

    @NotNull
    @Column (name="customer_phone")
    private String phone;

    private String mail;

    private String address;

    private String city;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Animal> animals;

}

