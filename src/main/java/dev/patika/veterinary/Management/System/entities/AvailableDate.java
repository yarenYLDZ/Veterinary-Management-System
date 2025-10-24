package dev.patika.veterinary.Management.System.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "available_dates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate availableDate;  // Sadece tarih bilgisi

    // Doktor ile ilişki
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
