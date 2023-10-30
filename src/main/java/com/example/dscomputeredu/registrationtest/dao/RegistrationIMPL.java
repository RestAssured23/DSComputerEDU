package com.example.dscomputeredu.registrationtest.dao;

import com.example.dscomputeredu.registrationtest.model.CourseCompletionBO;
import com.example.dscomputeredu.registrationtest.model.LoginBO;
import com.example.dscomputeredu.registrationtest.model.RegistrationBO;
import javassist.NotFoundException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class RegistrationIMPL implements RegistrationDAO {

    Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    String isoFormat = sdf.format(new Date(currentTimestamp.getTime()));
    final
    JdbcTemplate jdbcTemplate;
    int userId;

    public RegistrationIMPL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RegistrationBO> getall() {
        return jdbcTemplate.query
                ("select * from registration",
                        new BeanPropertyRowMapper<>(RegistrationBO.class));
    }

 /*   @Override
    public List<RegistrationBO> getbyregid(int reg_id) {
        return jdbcTemplate.query
                ("select * from registration where reg_id=?",
                        new BeanPropertyRowMapper<>(RegistrationBO.class), reg_id);
    }*/

    @Override
    public List<RegistrationBO> getbyregid(int reg_id) throws NotFoundException {
        List<RegistrationBO> registrationList = jdbcTemplate.query
                ("select * from registration where reg_id=?",
                        new BeanPropertyRowMapper<>(RegistrationBO.class), reg_id);
        if (registrationList.isEmpty()) {
            throw new NotFoundException("No records found for reg_id: " + reg_id);
        }

        return registrationList;
    }

    @Override
    public List<RegistrationBO> insert(RegistrationBO registrationBO) {
        List<RegistrationBO> inserteddata =new ArrayList<>();
        try {
           /* // Validate mobile number
            String mobilePattern = "\\d{10}"; // Assuming mobile number should be 10 digits
            String mobile = registrationBO.getMobile();
            if (!mobile.matches(mobilePattern)) {
                throw new IllegalArgumentException("Invalid mobile number format");
            }
*/
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO registration (" +
                                "first_name, last_name, email, mobile, dob, " +
                                "education, address, city, state, pincode, registration_date, gender, created_date, modified_date," +
                                "father_name,course_name" +
                                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, registrationBO.getFirst_name());
                ps.setString(2, registrationBO.getLast_name());
                ps.setString(3, registrationBO.getEmail());
                ps.setString(4, registrationBO.getMobile());
                ps.setString(5, registrationBO.getDob());
                ps.setString(6, registrationBO.getEducation());
                ps.setString(7, registrationBO.getAddress());
                ps.setString(8, registrationBO.getCity());
                ps.setString(9, registrationBO.getState());
                ps.setString(10, registrationBO.getPincode());
                ps.setString(11, isoFormat);
                ps.setString(12, registrationBO.getGender());
                ps.setString(13, isoFormat);
                ps.setString(14, isoFormat);
                ps.setString(15, registrationBO.getFather_name());
                ps.setString(16, registrationBO.getCourse_name());
                return ps;
            }, keyHolder);

            Map<String, Object> keys = keyHolder.getKeys();
            assert keys != null;
            userId = (int) keys.get("reg_id");

            //inserteddata.add(keys);

            String studentName = registrationBO.getFirst_name() + " " + registrationBO.getLast_name();
            String courseName = registrationBO.getCourse_name();

            // Insert the registration date in the dslogin table
            jdbcTemplate.update(connection -> {
                PreparedStatement insertPs = connection.prepareStatement(
                        "INSERT INTO dslogin (reg_id, registration_date) VALUES (?, ?)");

                insertPs.setInt(1, userId);
                insertPs.setString(2, isoFormat);
                return insertPs;
            });
            inserteddata.add(registrationBO);
            //coursecompletion table reg_id insert
            jdbcTemplate.update(
                    "INSERT INTO coursecompletion (reg_id) VALUES (?)",
                    userId);
//Update coursecompletion student first and last name course name
            jdbcTemplate.update(connection -> {
                PreparedStatement updatePs = connection.prepareStatement(
                        "UPDATE coursecompletion SET student_name = ?, course_name = ? WHERE reg_id = ?");
                updatePs.setString(1, studentName);
                updatePs.setString(2, courseName);
                updatePs.setLong(3, userId);
                return updatePs;
            });
        } catch (Exception e) {

            e.printStackTrace(); // This is a basic example. You may want to handle it differently.
        }
        return inserteddata;
    }

    @Override
    public List<CourseCompletionBO> getallcoursecompletion() {
        return jdbcTemplate.query(
                "select * from coursecompletion",
                new BeanPropertyRowMapper<>(CourseCompletionBO.class)
        );
    }

    @Override
    public List<CourseCompletionBO> getbycourseregid(int regId) {
        return jdbcTemplate.query
                ("select * from coursecompletion where reg_id=?",
                        new BeanPropertyRowMapper<>(CourseCompletionBO.class), regId);
    }

    @Override
    public LoginBO getLoginDetails(int regID) {
        return jdbcTemplate.queryForObject(
                "select * from dslogin where reg_id=? ",
                new BeanPropertyRowMapper<>(LoginBO.class), regID
        );
    }
 /*   @Override
    public LoginBO getAllLogin() {
        return jdbcTemplate.query(
                "select * from dslogin", new BeanPropertyRowMapper<LoginBO>(LoginBO.class)
        );*/

    @Override
    public List<LoginBO> getAllLogin() {
        return jdbcTemplate.query(
                "select * from dslogin", new BeanPropertyRowMapper<LoginBO>(LoginBO.class)
        );
    }

    /*   @Override
       public List<LoginBO> createLogin(LoginBO loginBO) {
        *//*   try {*//*
            jdbcTemplate.update(connection -> {
                PreparedStatement updatePs = connection.prepareStatement(
                        "UPDATE dslogin SET email = ?, password = ?,acc_createddate=? WHERE reg_id = ?");
                updatePs.setString(1, loginBO.getEmail());
                updatePs.setString(2, loginBO.getPassword());
                updatePs.setString(3, isoFormat);
                updatePs.setInt(4, loginBO.getReg_id()); // Assuming reg_id is an integer
                return updatePs;
            });
      *//*  }catch(Exception e){
            e.printStackTrace();
        }*//*
        return null;
    }*/
    @Override
    public List<LoginBO> createLogin(LoginBO loginBO) {
        List<LoginBO> updatedLoginBOs = new ArrayList<>(); // Initialize the list

        if (loginBO.getEmail() != null && !loginBO.getEmail().isEmpty() && loginBO.getPassword() != null && !loginBO.getPassword().isEmpty()) {
            try {
                jdbcTemplate.update(connection -> {
                    PreparedStatement updatePs = connection.prepareStatement(
                            "UPDATE dslogin SET email = ?, password = ?, acc_createddate = ? WHERE reg_id = ?");
                    updatePs.setString(1, loginBO.getEmail());
                    updatePs.setString(2, loginBO.getPassword());
                    updatePs.setString(3, isoFormat);
                    updatePs.setInt(4, loginBO.getReg_id()); // Assuming reg_id is an integer
                    return updatePs;
                });
                // Add the successfully updated LoginBO to the list
                updatedLoginBOs.add(loginBO);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            updatedLoginBOs.add(new LoginBO("DB update failed", null, null, 0)); // Create a LoginBO with the failure message
        }

        return updatedLoginBOs; // Return the list with updated or error messages
    }
}


