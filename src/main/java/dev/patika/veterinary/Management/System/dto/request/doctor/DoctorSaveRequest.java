package dev.patika.veterinary.Management.System.dto.request.doctor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSaveRequest {


    @NotNull (message = "isim alani bos veya null olamaz")
    private String name;
    @NotNull
    private String phone;
    @NotNull (message = "mail alani bos veya null olamaz")
    @Email
    private String mail;
    @NotNull (message = "adres alani bos veya null olamaz")
    private String address;
    @NotNull (message = "sehir alani bos veya null olamaz")
    private String city;


}
