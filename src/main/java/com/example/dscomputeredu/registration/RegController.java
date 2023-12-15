package com.example.dscomputeredu.registration;

import com.example.dscomputeredu.model.CourseCompletionBO;
import com.example.dscomputeredu.model.GetCustomResponseBO;
import com.example.dscomputeredu.model.RegistrationBO;
import com.example.dscomputeredu.model.StudentInfoBO;
import com.example.dscomputeredu.responsehandler.CommonResponse;
import com.example.dscomputeredu.responsehandler.getListResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dscomputer")
public class RegController {
    private final RegDAO regDAO;
    private final CommonResponse commonResponse;
    private final getListResponse getListResponse;


    public RegController(RegDAO regDAO, CommonResponse commonResponse, getListResponse getListResponse) {
        this.regDAO = regDAO;
        this.commonResponse = commonResponse;
        this.getListResponse = getListResponse;
    }


   @GetMapping("/registration")
    public GetCustomResponseBO<List<RegistrationBO>> getRegDetails() {
        return commonResponse.createCustomResponse("StudentDetails", regDAO.getStudentDetails());
    }
    @PostMapping("/registration")
    public GetCustomResponseBO<List<RegistrationBO>> saveReg(@RequestBody RegistrationBO registrationBO) {
        return commonResponse.createCustomResponse("StudentDetailsInsert", regDAO.insertDetails(registrationBO));
    }

    @GetMapping("/completiondetails")
    public GetCustomResponseBO<List<CourseCompletionBO>> getCompleteionDetails() {
        return commonResponse.createCustomResponse("StudentDetails", regDAO.getCourseCompletionDetails());
    }
    @GetMapping("/studentinfo")
    public GetCustomResponseBO<List<StudentInfoBO>> studentInfo(@RequestParam int reg_id){
        return commonResponse.createCustomResponse("StudentInformation", regDAO.getStudentInfo(reg_id));
    }
}
