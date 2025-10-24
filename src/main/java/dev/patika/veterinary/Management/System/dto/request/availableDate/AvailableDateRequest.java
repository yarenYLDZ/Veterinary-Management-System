package dev.patika.veterinary.Management.System.dto.request.availableDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateRequest {
    private Long doctorId;
    private LocalDate availableDate;


}
