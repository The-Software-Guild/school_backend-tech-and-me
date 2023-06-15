package com.wileyedge.fullstackschool.dao;

import com.wileyedge.fullstackschool.dao.mappers.TeacherMapper;
import com.wileyedge.fullstackschool.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TeacherDaoImpl implements TeacherDao {

    private final JdbcTemplate jdbcTemplate;

    public TeacherDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Teacher createNewTeacher(Teacher teacher) {
    	String sql = "INSERT INTO teacher (tFName, tLName, dept) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, teacher.getTeacherFName(), teacher.getTeacherLName(), teacher.getDept());
        return teacher;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        String sql = "SELECT * FROM teacher";
        return jdbcTemplate.query(sql, new TeacherMapper());
    }

    @Override
    public Teacher findTeacherById(int id) {
    	String sql = "SELECT * FROM teacher WHERE tid = ?";
        return jdbcTemplate.queryForObject(sql, new TeacherMapper(), id);
    }

    @Override
    public void updateTeacher(Teacher t) {
    	String sql = "UPDATE teacher SET tFName = ?, tLName = ?, dept = ? WHERE tid = ?";
        jdbcTemplate.update(sql, t.getTeacherFName(), t.getTeacherLName(), t.getDept(), t.getTeacherId());
    }

    @Override
    public void deleteTeacher(int id) {
    	String sql = "DELETE FROM teacher WHERE tid = ?";
        jdbcTemplate.update(sql, id);
    }
}
