package com.example.dscomputeredu.registrationtest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseCompletionBO {
    public int reg_id;
    public String student_name;
    public String course_name;
    public String grade;
    public String start_date;
    public String end_date;
}
