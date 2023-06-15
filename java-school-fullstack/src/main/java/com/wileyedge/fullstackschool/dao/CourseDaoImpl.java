package com.wileyedge.fullstackschool.dao;

import com.wileyedge.fullstackschool.dao.mappers.CourseMapper;
import com.wileyedge.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    private final JdbcTemplate jdbcTemplate;

    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course createNewCourse(Course course) {
    	String sql = "INSERT INTO course (courseCode, courseDesc, teacherId) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, course.getCourseName(), course.getCourseDesc(), course.getTeacherId());
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
    	String sql = "SELECT * FROM course";
        return jdbcTemplate.query(sql, new CourseMapper());
    }

    @Override
    public Course findCourseById(int id) {
    	String sql = "SELECT * FROM course WHERE cid = ?";
        return jdbcTemplate.queryForObject(sql, new CourseMapper(), id);
    }

    @Override
    public void updateCourse(Course course) {
    	Course existingCourse = this.findCourseById(course.getCourseId());

        if (existingCourse != null) {
            String updateSql = "UPDATE course SET courseCode = ?, courseDesc = ?, teacherId = ? WHERE cid = ?";
            jdbcTemplate.update(updateSql, course.getCourseName(), course.getCourseDesc(), course.getTeacherId(), course.getCourseId());
        }
    }

    @Override
    public void deleteCourse(int id) {
    	String sql = "DELETE FROM course WHERE cid = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteAllStudentsFromCourse(int courseId) {
    	String sql = "DELETE FROM course_student WHERE course_id = ?";
        jdbcTemplate.update(sql, courseId);
    }
}
