package com.example.dscomputeredu.registration;

import com.example.dscomputeredu.model.CourseCompletionBO;
import com.example.dscomputeredu.model.RegistrationBO;
import com.example.dscomputeredu.model.StudentInfoBO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class RegIMPL implements RegDAO {
    final JdbcTemplate jdbcTemplate;
    Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    String isoFormat = sdf.format(new Date(currentTimestamp.getTime()));

    public RegIMPL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<RegistrationBO> getStudentDetails() {
        return jdbcTemplate.query
                ("select * from regusers",
                        new BeanPropertyRowMapper<>(RegistrationBO.class));
    }


    @Override
    public List<CourseCompletionBO> getCourseCompletionDetails() {
        return jdbcTemplate.query(
                "select * from CourseCompletionDetails", new BeanPropertyRowMapper<>(CourseCompletionBO.class));
    }

    @Override
    public List<StudentInfoBO> getStudentInfo(int reg_id) {
        return jdbcTemplate.query(
                "select * from studentinfo where reg_id=?", new BeanPropertyRowMapper<>(StudentInfoBO.class), reg_id
        );
    }

    @Override
    public List<RegistrationBO> insertDetails(RegistrationBO registrationBO) {
        List<RegistrationBO> inserteddata = new ArrayList<>();
String defaultpassword="asdfasdf";
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO regusers (" +
                                "first_name, last_name,gender, email, mobile, dob, " +
                                "education,father_name,course_name, address, city, state, pincode, registration_date, created_date, modified_date" +
                                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, registrationBO.getFirst_name());
                ps.setString(2, registrationBO.getLast_name());
                ps.setString(3, registrationBO.getGender());
                ps.setString(4, registrationBO.getEmail());
                ps.setString(5, registrationBO.getMobile());
                ps.setString(6, registrationBO.getDob());
                ps.setString(7, registrationBO.getEducation());
                ps.setString(8, registrationBO.getFather_name());
                ps.setString(9, registrationBO.getCourse_name());
                ps.setString(10, registrationBO.getAddress());
                ps.setString(11, registrationBO.getCity());
                ps.setString(12, registrationBO.getState());
                ps.setString(13, registrationBO.getPincode());
                ps.setString(14, isoFormat);
                ps.setString(15, isoFormat);
                ps.setString(16, isoFormat);
                return ps;
            }, keyHolder);

            String studentName = registrationBO.getFirst_name() + " " + registrationBO.getLast_name();
            String courseName = registrationBO.getCourse_name();
            String PhNo=registrationBO.getMobile();
            String email= registrationBO.getEmail();
            if (keyHolder.getKey() != null) {
                int regId = (int) keyHolder.getKey().longValue();
                registrationBO.setReg_id((int) regId);


                jdbcTemplate.update(connection -> {
                    PreparedStatement insertPs = connection.prepareStatement(
                            "INSERT INTO coursecompletiondetails (reg_id, student_name,course_name) VALUES (?, ?, ?)");

                    insertPs.setInt(1, regId);
                    insertPs.setString(2, studentName);
                    insertPs.setString(3, courseName);
                    return insertPs;
                });

                jdbcTemplate.update(connection -> {
                    PreparedStatement studentInfo = connection.prepareStatement(
                            "INSERT INTO studentinfo (reg_id, name,email,password,mobile,coursename,flag,registration_date)" +
                                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

                    studentInfo.setInt(1, regId);
                    studentInfo.setString(2, studentName);
                    studentInfo.setString(3, email);
                    studentInfo.setString(4,defaultpassword);
                    studentInfo.setString(5,PhNo);
                    studentInfo.setString(6,courseName);
                    studentInfo.setString(7,"A");
                    studentInfo.setString(8, isoFormat);
                    return studentInfo;
                });

                 inserteddata.add(registrationBO);
            }
        } catch (Exception e) {

            e.printStackTrace(); // This is a basic example. You may want to handle it differently.
        }
        return inserteddata;
    }

}

