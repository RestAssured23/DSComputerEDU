package com.example.dscomputeredu.registrationtest.controller;

import com.example.dscomputeredu.registrationtest.dao.RegistrationDAO;
import com.example.dscomputeredu.registrationtest.model.*;
import com.example.dscomputeredu.registrationtest.responsehandler.CommonResponse;
import com.example.dscomputeredu.registrationtest.responsehandler.getResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class RegistrationController {
    private final RegistrationDAO registrationDAO;
    private final CommonResponse commonResponse;
    private final getResponse getResponse;

    public RegistrationController(RegistrationDAO registrationDAO, CommonResponse commonResponse,com.example.dscomputeredu.registrationtest.responsehandler.getResponse getResponse) {
        this.registrationDAO = registrationDAO;
        this.commonResponse = commonResponse;
        this.getResponse = getResponse;
    }

    @GetMapping("/reg/all")
    public GetCustomResponseBO<List<RegistrationBO>> getAllStudents() {
        return commonResponse.createCustomResponse("StudentDetails", registrationDAO.getall());
    }
    @GetMapping("/reg/regid")
    public GetCustomResponseBO<List<RegistrationBO>> getStudentByRegId(@RequestParam int reg_id) {
        return commonResponse.createCustomResponse("StudentDetails",registrationDAO.getbyregid(reg_id));
    }

    @PostMapping("/reg/save")
    public GetCustomResponseBO<List<RegistrationBO>> saveNewRegistration(@RequestBody RegistrationBO registrationBO) {
        return commonResponse.createCustomResponse("StudentDetailsInsert", registrationDAO.insert(registrationBO));
    }


    @GetMapping("/reg/completion/all")
    public GetCustomResponseBO<List<CourseCompletionBO>> getAllCourseCompletions() {
        return commonResponse.createCustomResponse("Course Completion Details", registrationDAO.getallcoursecompletion());
    }

    @GetMapping("/reg/completion/regid")
    public GetCustomResponseBO<List<CourseCompletionBO>> getCourseCompletionByRegId(@RequestParam int regId) {
        return commonResponse.createCustomResponse("Course Completion Details",registrationDAO.getbycourseregid(regId));
    }

    @GetMapping("/reg/logindetails")
    public getResponseBO<LoginBO> getLoginDetails(@RequestParam int regID) {
        LoginBO loginDetails = registrationDAO.getLoginDetails(regID);
        return getResponse.createGetResponse("Login Details", loginDetails);
    }

    @GetMapping("/reg/logindetails/all")
    public GetCustomResponseBO<List<LoginBO>> getlogindetails(){
        return commonResponse.createCustomResponse("Login Details",registrationDAO.getAllLogin());
    }

    @PutMapping("/reg/logindetails")
    public GetCustomResponseBO<List<LoginBO>> insertLogin(@RequestBody LoginBO loginBO){
        return commonResponse.createCustomResponse("Login Details",registrationDAO.createLogin(loginBO));
    }

}