package dev.patika.veterinary.Management.System.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "available_dates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"doctor"}) // Sonsuz döngüyü engeller
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate availableDate;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnore // JSON cevabında döngüyü engeller
    private Doctor doctor;
}
