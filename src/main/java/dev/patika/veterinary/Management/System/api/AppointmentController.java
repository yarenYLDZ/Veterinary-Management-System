package dev.patika.veterinary.Management.System.api;

import dev.patika.veterinary.Management.System.business.abstracts.IAnimalService;
import dev.patika.veterinary.Management.System.business.abstracts.IAppointmentService;
import dev.patika.veterinary.Management.System.business.abstracts.IDoctorService;
import dev.patika.veterinary.Management.System.core.exception.ValidationException;
import dev.patika.veterinary.Management.System.core.result.ResultData;
import dev.patika.veterinary.Management.System.core.utilies.ResultHelper;
import dev.patika.veterinary.Management.System.dto.request.appointment.AppointmentRequest;
import dev.patika.veterinary.Management.System.entities.Animal;
import dev.patika.veterinary.Management.System.entities.Appointment;
import dev.patika.veterinary.Management.System.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {

    private final IDoctorService doctorService;
    private final IAnimalService animalService;
    private final IAppointmentService appointmentService;

    public AppointmentController(IDoctorService doctorService, IAnimalService animalService, IAppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.animalService = animalService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/by-doctor")
    public ResultData<List<Appointment>> getAppointmentsByDoctor(
            @RequestParam Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResultHelper.success(appointmentService.getAllByDoctorIdAndDateRange(doctorId, start, end));
    }

    @GetMapping("/by-animal")
    public ResultData<List<Appointment>> getAppointmentsByAnimal(
            @RequestParam Long animalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResultHelper.success(appointmentService.getAllByAnimalIdAndDateRange(animalId, start, end));
    }

    @PostMapping
    public ResultData<Appointment> addAppointment(@Valid @RequestBody AppointmentRequest req) {
        // 🧠 Buraya ValidationException kontrolü eklendi
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
