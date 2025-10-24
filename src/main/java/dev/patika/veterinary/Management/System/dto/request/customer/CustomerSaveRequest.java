package dev.patika.veterinary.Management.System.dto.request.customer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {

    @NotNull (message = "name alani bos veya null olamaz")
    private String name;

    @NotNull (message = "telefon alani bos veya null olamaz")
    private String phone;

    @NotNull (message = "mail alani bos veya null olamaz")
    private String mail;

    @NotNull (message = "adress alani bos veya null olamaz")
    private String address;

    @NotNull (message = "sehir alani bos veya null olamaz")
    private String city;
}

