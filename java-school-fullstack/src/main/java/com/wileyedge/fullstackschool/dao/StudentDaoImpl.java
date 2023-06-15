package com.wileyedge.fullstackschool.dao;

import com.wileyedge.fullstackschool.dao.mappers.StudentMapper;
import com.wileyedge.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Student createNewStudent(Student student) {
    	String sql = "INSERT INTO student (fName, lName) VALUES (?, ?)";
        jdbcTemplate.update(sql, student.getStudentFirstName(), student.getStudentLastName());
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
    	String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, new StudentMapper());
    }

    @Override
    public Student findStudentById(int id) {
    	String sql = "SELECT * FROM student WHERE sid = ?";
        return jdbcTemplate.queryForObject(sql, new StudentMapper(), id);
    }

    @Override
    public void updateStudent(Student student) {
    	String sql = "UPDATE student SET fName = ?, lName = ? WHERE sid = ?";
        jdbcTemplate.update(sql, student.getStudentFirstName(), student.getStudentLastName(), student.getStudentId());
    }

    @Override
    public void deleteStudent(int id) {
    	String sql = "DELETE FROM student WHERE sid = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
    	String sql = "SELECT COUNT(*) FROM course_student WHERE student_id = ? AND course_id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, studentId, courseId);

        if (count == 0) {
            String insertSql = "INSERT INTO course_student (student_id, course_id) VALUES (?, ?)";
            jdbcTemplate.update(insertSql, studentId, courseId);
        }
    }

    @Override
    public void deleteStudentFromCourse(int studentId, int courseId) {
    	 String sql = "DELETE FROM course_student WHERE student_id = ? AND course_id = ?";
         jdbcTemplate.update(sql, studentId, courseId);
    }
}
