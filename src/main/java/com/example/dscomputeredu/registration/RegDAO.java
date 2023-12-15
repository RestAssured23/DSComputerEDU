package com.example.dscomputeredu.registration;

import com.example.dscomputeredu.model.CourseCompletionBO;
import com.example.dscomputeredu.model.RegistrationBO;
import com.example.dscomputeredu.model.StudentInfoBO;

import java.util.List;

public interface RegDAO {
    List<RegistrationBO> getStudentDetails();

    List<CourseCompletionBO> getCourseCompletionDetails();

    List<StudentInfoBO> getStudentInfo(int reg_id);

    List<RegistrationBO> insertDetails(RegistrationBO registrationBO);
}
