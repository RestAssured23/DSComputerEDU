package com.example.dscomputeredu.registrationtest.controller;

import com.example.dscomputeredu.registrationtest.dao.RegistrationDAO;
import com.example.dscomputeredu.registrationtest.model.CourseCompletionBO;
import com.example.dscomputeredu.registrationtest.model.RegistrationBO;
import com.example.dscomputeredu.registrationtest.responsehandler.CommonResponse;
import com.example.dscomputeredu.registrationtest.responsehandler.CustomResponseBO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class RegistrationController {
    private final RegistrationDAO registrationDAO;
    private final CommonResponse commonResponse;

    public RegistrationController(RegistrationDAO registrationDAO, CommonResponse commonResponse) {
        this.registrationDAO = registrationDAO;
        this.commonResponse = commonResponse;
    }

    @GetMapping("/reg/all")
    public CustomResponseBO<List<RegistrationBO>> getAllStudents() {
        return commonResponse.createCustomResponse("StudentDetails", registrationDAO.getall());
    }
    @GetMapping("/reg/regid")
    public CustomResponseBO<List<RegistrationBO>> getStudentByRegId(@RequestParam int reg_id) {
        return commonResponse.createCustomResponse("StudentDetails",registrationDAO.getbyregid(reg_id));
    }

    @PostMapping("/reg/save")
    public CustomResponseBO<List<RegistrationBO>> saveNewRegistration(@RequestBody RegistrationBO registrationBO) {
        return commonResponse.createCustomResponse("StudentDetailsInsert", registrationDAO.insert(registrationBO));
    }

    @GetMapping("/reg/completion/all")
    public CustomResponseBO<List<CourseCompletionBO>> getAllCourseCompletions() {
        return commonResponse.createCustomResponse("Course Completion Details", registrationDAO.getallcoursecompletion());
    }

    @GetMapping("/reg/completion/regid")
    public CustomResponseBO<List<CourseCompletionBO>> getCourseCompletionByRegId(@RequestParam int regId) {
        return commonResponse.createCustomResponse("Course Completion Details",registrationDAO.getbycourseregid(regId));
    }
}