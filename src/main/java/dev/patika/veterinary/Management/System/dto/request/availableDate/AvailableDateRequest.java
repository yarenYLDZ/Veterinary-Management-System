package dev.patika.veterinary.Management.System.dto.request.availableDate;


import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableDateRequest {
    private Long doctorId;
    private LocalDate availableDate;


}
