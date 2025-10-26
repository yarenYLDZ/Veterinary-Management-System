package dev.patika.veterinary.Management.System.api;

import dev.patika.veterinary.Management.System.business.abstracts.IAnimalService;
import dev.patika.veterinary.Management.System.business.abstracts.IAppointmentService;
import dev.patika.veterinary.Management.System.business.abstracts.IDoctorService;
import dev.patika.veterinary.Management.System.business.abstracts.IAvailableDateService;
import dev.patika.veterinary.Management.System.core.exception.ValidationException;
import dev.patika.veterinary.Management.System.core.result.ResultData;
import dev.patika.veterinary.Management.System.core.utilies.ResultHelper;
import dev.patika.veterinary.Management.System.dto.request.appointment.AppointmentFilterByAnimalRequest;
import dev.patika.veterinary.Management.System.dto.request.appointment.AppointmentFilterByDrRequest;
import dev.patika.veterinary.Management.System.dto.request.appointment.AppointmentRequest;
import dev.patika.veterinary.Management.System.dto.response.appointment.AppointmentByAnimalResponse;
import dev.patika.veterinary.Management.System.entities.Animal;
import dev.patika.veterinary.Management.System.entities.Appointment;
import dev.patika.veterinary.Management.System.entities.AvailableDate;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {

    private final IDoctorService doctorService;
    private final IAnimalService animalService;
    private final IAppointmentService appointmentService;
    private final IAvailableDateService availableDateService; // 🔹 eklendi

    public AppointmentController(IDoctorService doctorService,
                                 IAnimalService animalService,
                                 IAppointmentService appointmentService,
                                 IAvailableDateService availableDateService) { // 🔹 constructor
        this.doctorService = doctorService;
        this.animalService = animalService;
        this.appointmentService = appointmentService;
        this.availableDateService = availableDateService; // 🔹 assign
    }

    // ✅ Doktorun müsait günlerini çekmek için
    @PostMapping("/by-doctor")
    public ResultData<List<AvailableDate>> getAvailableDatesByDoctor(
            @RequestBody AppointmentFilterByDrRequest request) {

        List<AvailableDate> dates = availableDateService.getAvailableDatesByDoctorAndDateRange(
                request.getDoctorId(),
                request.getStartDate(),
                request.getEndDate()
        );

        return ResultHelper.success(dates);
    }

    @GetMapping("/by-animal-range")
    public ResultData<List<AppointmentByAnimalResponse>> getAppointmentsByAnimalAndDateRange(
            @RequestBody AppointmentFilterByAnimalRequest request) {

        List<Appointment> appointments = appointmentService.getAllByAnimalIdAndDateRange(
                request.getAnimalId(),
                request.getStartDate().atStartOfDay(),
                request.getEndDate().atTime(23, 59, 59)
        );

        List<AppointmentByAnimalResponse> responseList = appointments.stream()
                .map(a -> {
                    AppointmentByAnimalResponse res = new AppointmentByAnimalResponse();
                    res.setAppointmentId(a.getId());
                    res.setAppointmentDateTime(a.getAppointmentDateTime());
                    res.setDoctorName(a.getDoctor().getName());
                    return res;
                })
                .toList();

        return ResultHelper.success(responseList);
    }





    // Mevcut appointment ekleme endpointi
    @PostMapping
    public ResultData<Appointment> addAppointment(@Valid @RequestBody AppointmentRequest req) {
        if (req.getDoctorId() == null) {
            throw new ValidationException("Appointment", "Doktor bilgisi boş olamaz.");
        }
        if (req.getAnimalId() == null) {
            throw new ValidationException("Appointment", "Hayvan bilgisi boş olamaz.");
        }

        Appointment saved = appointmentService.save(
                req.getDoctorId(),
                req.getAnimalId(),
                req.getAppointmentDateTime()
        );
        return ResultHelper.created(saved);
    }
}
