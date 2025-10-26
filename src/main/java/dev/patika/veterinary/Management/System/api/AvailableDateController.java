package dev.patika.veterinary.Management.System.api;

import dev.patika.veterinary.Management.System.business.abstracts.IAvailableDateService;
import dev.patika.veterinary.Management.System.core.result.ResultData;
import dev.patika.veterinary.Management.System.core.utilies.ResultHelper;
import dev.patika.veterinary.Management.System.dto.request.availableDate.AvailableDateRequest;
import dev.patika.veterinary.Management.System.entities.AvailableDate;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/v1/doctors/available-dates")
public class AvailableDateController {

    private final IAvailableDateService availableDateService;

    public AvailableDateController(IAvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }

    @PostMapping
    public ResultData<AvailableDate> addAvailableDate(@Valid @RequestBody AvailableDateRequest request) {
        try {
            AvailableDate saved = availableDateService.save(request.getDoctorId(), request.getAvailableDate());
            return ResultHelper.created(saved);
        } catch (IllegalStateException e) {
            // Aynı tarih zaten varsa fail olarak dön
            return ResultHelper.validateError(e.getMessage(), null);
        } catch (Exception e) {
            // Diğer tüm hatalarda da fail dön
            return ResultHelper.validateError("Bir hata oluştu: " + e.getMessage(), null);
        }
    }


    @GetMapping("/{doctorId}")
    public ResultData<List<AvailableDate>> getAvailableDates(@PathVariable Long doctorId) {
        return ResultHelper.success(availableDateService.getAllByDoctorId(doctorId));
    }


}
