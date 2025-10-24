package dev.patika.veterinary.Management.System.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "doctors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="doctor_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String phone;

    @NotNull
    private String mail;

    @NotNull
    private String address;

    @NotNull
    private String city;

    // ================= İLİŞKİLER =================

    // Doktorun birden fazla randevusu olabilir
    //@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonIgnore
    //private List<Appointment> appointments;

    // Doktorun birden fazla müsait günü olabilir
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<AvailableDate> availableDates;
}
