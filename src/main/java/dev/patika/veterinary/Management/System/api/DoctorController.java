package dev.patika.veterinary.Management.System.api;


import dev.patika.veterinary.Management.System.business.abstracts.IDoctorService;
import dev.patika.veterinary.Management.System.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinary.Management.System.core.result.ResultData;
import dev.patika.veterinary.Management.System.core.utilies.ResultHelper;
import dev.patika.veterinary.Management.System.dto.request.customer.CustomerSaveRequest;
import dev.patika.veterinary.Management.System.dto.request.doctor.DoctorSaveRequest;
import dev.patika.veterinary.Management.System.dto.response.customer.CustomerResponse;
import dev.patika.veterinary.Management.System.dto.response.doctor.DoctorResponse;
import dev.patika.veterinary.Management.System.entities.Customer;
import dev.patika.veterinary.Management.System.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {

    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    public DoctorController(IDoctorService doctorService, IModelMapperService modelMapperService) {
        this.doctorService = doctorService;
        this.modelMapper = modelMapperService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest){
        Doctor saveDoctor = this.modelMapper.forRequest().map(doctorSaveRequest, Doctor.class);
        this.doctorService.save(saveDoctor);
        return ResultHelper.created((this.modelMapper.forResponse().map(saveDoctor, DoctorResponse.class)));


    }

}
