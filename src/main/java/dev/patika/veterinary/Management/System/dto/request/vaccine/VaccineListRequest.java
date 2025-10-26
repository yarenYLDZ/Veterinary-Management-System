package dev.patika.veterinary.Management.System.dto.request.vaccine;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccineListRequest {
    private LocalDate start;
    private LocalDate end;
}
