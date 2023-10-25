package com.example.dscomputeredu.registrationtest.dao;

import com.example.dscomputeredu.registrationtest.model.CourseCompletionBO;
import com.example.dscomputeredu.registrationtest.model.LoginBO;
import com.example.dscomputeredu.registrationtest.model.RegistrationBO;
import com.example.dscomputeredu.registrationtest.model.getResponseBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Repository
public class RegistrationIMPL implements RegistrationDAO {
    Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    String isoFormat = sdf.format(new Date(currentTimestamp.getTime()));
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<RegistrationBO> getall() {
        return jdbcTemplate.query
                ("select * from registration",
                        new BeanPropertyRowMapper<RegistrationBO>(RegistrationBO.class));
    }

    @Override
    public List<RegistrationBO> getbyregid(int reg_id) {
        return jdbcTemplate.query
                ("select * from registration where reg_id=?",
                        new BeanPropertyRowMapper<RegistrationBO>(RegistrationBO.class), reg_id);
    }


    @Override
    public List<RegistrationBO> insert(RegistrationBO registrationBO) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO registration (" +
                            "first_name, last_name, email, mobile, dob, " +
                            "education, address, city, state, pincode, registration_date, gender, created_date, modified_date," +
                            "father_name,course_name"+
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
        int userId = (int) keys.get("reg_id");

        String studentName = registrationBO.getFirst_name() + " " + registrationBO.getLast_name();
        String courseName = registrationBO.getCourse_name();

        // Update the registration date in the dslogin table (PostgreSQL syntax)
        int rowsUpdatedSecondTable = jdbcTemplate.update(
                "INSERT INTO dslogin (reg_id, registration_date) VALUES (?, ?::date) " +
                        "ON CONFLICT (reg_id) DO UPDATE SET registration_date = to_date(?, 'DD-MM-YYYY')",
                userId, isoFormat, isoFormat);


//coursecompletion table
        int rowsUpdatedFirstTable = jdbcTemplate.update(
                "INSERT INTO coursecompletion (reg_id) VALUES (?)",
                userId);
        jdbcTemplate.update(connection -> {
            PreparedStatement updatePs = connection.prepareStatement(
                    "UPDATE coursecompletion SET student_name = ?, course_name = ? WHERE reg_id = ?");
            updatePs.setString(1, studentName);
            updatePs.setString(2, courseName);
            updatePs.setLong(3, userId);
            return updatePs;
        });
        return null;
    }

    @Override
    public List<CourseCompletionBO> getallcoursecompletion() {
        return jdbcTemplate.query(
                "select * from coursecompletion",
                new BeanPropertyRowMapper<CourseCompletionBO>(CourseCompletionBO.class)
        );
    }

    @Override
    public List<CourseCompletionBO> getbycourseregid(int regId) {
        return jdbcTemplate.query
                ("select * from coursecompletion where reg_id=?",
                        new BeanPropertyRowMapper<CourseCompletionBO>(CourseCompletionBO.class), regId);
    }
    @Override
    public LoginBO getLoginDetails(int regID) {
        return jdbcTemplate.queryForObject(
                "select * from dslogin where reg_id=? ",
                new BeanPropertyRowMapper<>(LoginBO.class),regID
        );
    }
    @Override
    public List<LoginBO> getAllLogin() {
        return jdbcTemplate.query(
                "select * from dslogin",new BeanPropertyRowMapper<LoginBO>(LoginBO.class)
        );
    }

    @Override
    public List<LoginBO> postlogin() {
        return null;
    }
}
