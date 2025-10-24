package dev.patika.veterinary.Management.System.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String species;     // Tür
    private String breed;       // Irk
    private String gender;      // Cinsiyet
    private String colour;      // Renk
    @PastOrPresent(message = "Doğum tarihi bugünden ileri olamaz")
    private LocalDate dateOfBirth;


    // ================= İLİŞKİLER =================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore  // Sonsuz döngüyü önlemek için
    private Customer customer;
}
